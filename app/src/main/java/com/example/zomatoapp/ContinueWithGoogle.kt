package com.example.zomatoapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.MainThread
import com.example.zomatoapp.UI.HomeFragment
import com.example.zomatoapp.UI.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.coroutines.coroutineContext

const val RC_SIGN_IN = 123
//This is for the Sign in Page
class ContinueWithGoogle : AppCompatActivity() {

    private var auth = FirebaseAuth.getInstance()
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var button: LinearLayout






    override fun onStart() {
        super.onStart()
        var user : FirebaseUser? = auth.currentUser
        if(user!=null){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_continue_with_google)

        button = findViewById(R.id.signin_btn)
        createRequest()

        button.setOnClickListener {
            signIn()
        }

    }


    private fun createRequest() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(ContentValues.TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(this,"Sign In Failed Please Try Again )", Toast.LENGTH_SHORT).show()
                // Google Sign In failed, update UI appropriately
                Log.w(ContentValues.TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "signInWithCredential:success")
                    val user = auth.currentUser
//                    Snackbar.make(,"Signin as ${GoogleSignIn.getLastSignedInAccount(this).displayName}",Snackbar.LENGTH_SHORT).show()
                    Toast.makeText(this,"Singin as ${GoogleSignIn.getLastSignedInAccount(this).displayName}", Toast.LENGTH_SHORT).show()

                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                } else {
//                    Snackbar.make(,"Sorry Signin Failed",Snackbar.LENGTH_SHORT).show()
//                    Toast.makeText(this,"Sorry Auth Failed", Toast.LENGTH_SHORT).show()
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)

                }
            }
    }

}