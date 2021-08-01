package com.example.zomatoapp.UI

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.service.autofill.CharSequenceTransformation
import android.service.autofill.UserData
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapp.Adapter.FoodAdapter
import com.example.zomatoapp.Adapter.RestrauntCityAdapter
import com.example.zomatoapp.LocationCheck
import com.example.zomatoapp.R
import com.example.zomatoapp.Repository.Repository
import com.example.zomatoapp.ViewModel.MainViewModel
import com.example.zomatoapp.ViewModel.MainViewModelFactory
import com.example.zomatoapp.ViewModel.RestraunfSearchViewModelFactory
import com.example.zomatoapp.ViewModel.RestrauntSearchViewModel
import com.example.zomatoapp.api.food
import com.example.zomatoapp.databinding.FragmentHomeBinding
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.citysearchlistlayout.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {


    private var PERMISSION_ID = 52
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var latitude: Double = 28.704059
    private var longitude: Double = 77.10249

    private val foodAdapter: FoodAdapter by lazy { FoodAdapter(requireContext()) }
    private val restrauntadapter: RestrauntCityAdapter by lazy { RestrauntCityAdapter(requireContext()) }
    private lateinit var restrauntviewmodel: RestrauntSearchViewModel
    private lateinit var viewModel: MainViewModel
    private lateinit var repository: Repository


    private lateinit var binding: FragmentHomeBinding
    private var res = emptyList<food.Collection>()
    private lateinit var recyclerView: RecyclerView

    @RequiresApi(Build.VERSION_CODES.O_MR1)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this.requireContext())


        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O_MR1)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getLastLocation()
        // For Smooth Scrolling

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            val realSmoothScrollAnimation =
                ValueAnimator.ofInt(binding.HomeScroll.getScrollY(), 10)
            realSmoothScrollAnimation.duration = 1
            realSmoothScrollAnimation.addUpdateListener { animation ->
                val scrollTo = animation.animatedValue as Int
                binding.HomeScroll.scrollTo(0, scrollTo)
            }
            realSmoothScrollAnimation.start()
        } else {
            binding.HomeScroll.smoothScrollTo(0, 10)
        }


        //Below Line of codes assigns id of the respective recycler view to their respective variables.

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_recyclerView)
        val reestrecyclerview: RecyclerView = view.findViewById(R.id.rv_restrauntsearch)

        reestrecyclerview.isNestedScrollingEnabled = false
        val repository = Repository(this.requireContext())

        val vmf = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, vmf).get(MainViewModel::class.java)
        viewModel.getFood(latitude, longitude)

        val rvmf = RestraunfSearchViewModelFactory(repository)
        restrauntviewmodel = ViewModelProvider(this, rvmf).get(RestrauntSearchViewModel::class.java)
        restrauntviewmodel.getRestraunt(28.704059, 77.10249)

        binding.locationBtn.setOnClickListener {
            viewModel.getFood(latitude, longitude)
            restrauntviewmodel.getRestraunt(latitude, longitude)
        }


        Log.d("location", "latitude ${latitude}")
        Log.d("location", "longitude ${longitude}")



        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (it)
                progressbar.visibility = View.VISIBLE
            else
                progressbar.visibility = View.GONE
        })


        viewModel.food.observe(viewLifecycleOwner, Observer {

            if (it.isSuccessful)
                foodAdapter.setStateWiseTracker(it.body()?.collections!!)
            progressbar.visibility = View.GONE
            Log.d("BOLT", "success" + it.toString())
        })



        restrauntviewmodel.cityrestraunt.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                restrauntadapter.setStateRestrauntWiseTracker(it.body()?.restaurants!!)
                //     binding.yourLocationTxt.text =
                //      it.body()!!.restaurants?.get(0)?.restaurant?.location!!.city.toString()

                Log.d("bolt", "success" + it.toString())
            }

        })

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = foodAdapter

        reestrecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        reestrecyclerview.adapter = restrauntadapter


    }


    //Below Line of codes is for fetching the user Location in terms of Latitude and Longitude

    @RequiresApi(Build.VERSION_CODES.O_MR1)
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermission()) {
            if (LocationCheck.isLocationEnabled(this.requireContext())) {
                //  getLastLoc()
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        getNewLocation()
                    } else {
                        latitude = location.latitude
                        longitude = location.longitude


                        Log.d("BYE", "latitude ${latitude}")


                    }

                }
            } else {

                val builder: AlertDialog.Builder =
                    AlertDialog.Builder(this.requireContext())
                builder.setMessage("Please Enable your GPS")

                builder.setTitle(" GPS required ")

                builder.setCancelable(false)
                builder
                    .setPositiveButton(
                        "Yes",
                        DialogInterface.OnClickListener { dialog, which -> // When the user click yes button
                            // then app will close
                            if (LocationCheck.isLocationEnabled(this.requireContext())) {
                                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                                    var location: Location? = task.result
                                    if (location == null) {
                                        getNewLocation()
                                    } else {
                                        latitude = location.latitude
                                        longitude = location.longitude


                                        Log.d("BYE", "latitude ${latitude}")


                                    }

                                }
                                Toast.makeText(
                                    requireContext(),
                                    "Press on location button to Refresh",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                getLastLocation()
                            }
                        })
                builder
                    .setNegativeButton(
                        "No",
                        DialogInterface.OnClickListener { dialog, which -> // If user click no
                            Toast.makeText(
                                requireContext(),
                                "Please Enable GPS for Better Experience",
                                Toast.LENGTH_SHORT
                            ).show()
                        })
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
                Toast.makeText(this.requireContext(), "enable your location", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            RequestPermission()
        }
    }


    @SuppressLint("MissingPermission")
    private fun getNewLocation() {
        val locationRequest = LocationRequest.create()
        locationRequest.apply {
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            locationRequest.interval = 0
            locationRequest.fastestInterval = 0
            locationRequest.numUpdates = 2

            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest, locationCallback,
                Looper.getMainLooper()
            )

        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation = p0.lastLocation
            Log.d("locate", "latitude " + lastLocation.latitude)
            super.onLocationResult(p0)
        }
    }

    private fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this.requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this.requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun RequestPermission() {
        ActivityCompat.requestPermissions(
            this.requireActivity(),
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Debug", "You have the permission")
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}
