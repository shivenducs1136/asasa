package com.example.zomatoapp.UI

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.zomatoapp.ContinueWithGoogle
import com.example.zomatoapp.Firebase.User
import com.example.zomatoapp.R
import com.example.zomatoapp.databinding.FragmentProfileBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var signInAccount: GoogleSignInAccount = GoogleSignIn.getLastSignedInAccount(this.requireContext())
        if(signInAccount!=null){
            binding.profileName.text = signInAccount.displayName
            Picasso.with(context)
                .load(signInAccount.photoUrl)
                .into(binding.circularAccDp)
            binding.profileEmail.text = signInAccount.email

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(signInAccount.displayName,signInAccount.familyName,signInAccount.email,signInAccount.photoUrl.toString(),signInAccount.account.toString())
            databaseReference.child(signInAccount.displayName).setValue(User)
        }

        binding.pofileLogoutBtn.setOnClickListener {

            FirebaseAuth.getInstance().signOut()
            val i = Intent(this.requireActivity(), ContinueWithGoogle::class.java)
            startActivity(i)
            activity?.finish()


        }
        binding.profileShareBtn.setOnClickListener {

            val shareIntent = Intent(Intent.ACTION_SEND)

            shareIntent.type = "text/plain"

            shareIntent.putExtra(Intent.EXTRA_SUBJECT, " Zomato Task ")

            val app_url = "Tomazo App\n Team : \n This App is made By Shivendu (1st year) and Arsh(2nd year) \n Below is the App Link\n https://drive.google.com/drive/u/1/folders/14f3Q6urMXfqGNCiIzKi3GUe68E5h6n2k "

            shareIntent.putExtra(Intent.EXTRA_TEXT, app_url)

            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        binding.meBtn.setOnClickListener {
            Snackbar.make(this.requireView()," Made By Shivendu(1) and Arsh(2) more features upcomming. Stay Tuned :) ",Snackbar.LENGTH_SHORT).show()
        }



    }



}