package com.example.zomatoapp.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapp.Adapter.SearchedCityAdapter
import com.example.zomatoapp.R
import com.example.zomatoapp.Repository.Repository
import com.example.zomatoapp.ViewModel.MainViewModel
import com.example.zomatoapp.ViewModel.MainViewModelFactory
import com.example.zomatoapp.databinding.FragmentSerachedCityResultsBinding
import kotlinx.android.synthetic.main.fragment_map.*


class SerachedCityResultsFragment : Fragment() {

    private lateinit var idviewModel: MainViewModel
    private val citySearchresultAdapter: SearchedCityAdapter by lazy{ SearchedCityAdapter(requireContext()) }
    private lateinit var binding: FragmentSerachedCityResultsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_serached_city_results,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bundleid = this.arguments
        var ids : String = ""
        if(bundleid != null){

            ids = bundleid.getString("id1").toString()
        }
        val cityrecyclerView: RecyclerView = view.findViewById(R.id.searched_food_rcview)

        val repository= Repository(this.requireContext())
        val vmf= MainViewModelFactory(repository)
        idviewModel = ViewModelProvider(this,vmf).get(MainViewModel::class.java)


        Log.d("showids", ids)

        idviewModel.getcityid(ids)
        cityrecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cityrecyclerView.adapter = citySearchresultAdapter
        idviewModel.cityshowProgress?.observe(viewLifecycleOwner, Observer {
            if (it)
                binding.searchcityprogressbar.visibility = View.VISIBLE
            else
                binding.searchcityprogressbar.visibility = View.GONE
        })
        idviewModel.city.observe(viewLifecycleOwner, Observer {

            if (it.isSuccessful)
                citySearchresultAdapter?.searchedsetStateWiseTracker(it.body()?.collections!!)
            binding.searchcityprogressbar.visibility= View.GONE
            Log.d("searchy","success"+it.toString())

        })
//        binding.citynametxtview.text =
        binding.searchbckbtn.setOnClickListener{
            binding.searchbckbtn.findNavController().navigate(R.id.action_serachedCityResultsFragment_to_mapFragment2)
        }
    }

}