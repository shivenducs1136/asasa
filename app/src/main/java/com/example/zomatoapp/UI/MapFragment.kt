package com.example.zomatoapp.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isEmpty
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapp.Adapter.CitySearchAdapter
import com.example.zomatoapp.R
import com.example.zomatoapp.Repository.Repository
import com.example.zomatoapp.ViewModel.CityViewModel
import com.example.zomatoapp.ViewModel.CityViewModelFactory
import com.example.zomatoapp.ViewModel.MainViewModel
import com.example.zomatoapp.ViewModel.MainViewModelFactory
import com.example.zomatoapp.cityapi.city
import com.example.zomatoapp.databinding.FragmentMapBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : Fragment() {
    private val citySearchAdapter:CitySearchAdapter by lazy{CitySearchAdapter(requireContext())}
    private lateinit var cityViewModel: CityViewModel
    private lateinit var idviewModel: MainViewModel
    private lateinit var binding: FragmentMapBinding
    private var collectid:List<city.LocationSuggestion?> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cityrecyclerView: RecyclerView = view.findViewById(R.id.search_results_recview)
        val repository = Repository(this.requireContext())

        val cvmf = CityViewModelFactory(repository)

        val vmf= MainViewModelFactory(repository)

        if(binding.searchboxtxt.isEmpty()){
            binding.searchResultsRecview.visibility = View.GONE
            binding.restrauntSearchRecview.visibility = View.VISIBLE
        }
        else{
            binding.searchResultsRecview.visibility = View.VISIBLE
            binding.restrauntSearchRecview.visibility = View.GONE
        }

        cityViewModel = ViewModelProvider(this,cvmf).get(CityViewModel::class.java)
        idviewModel = ViewModelProvider(this,vmf).get(MainViewModel::class.java)

            binding.searchboxtxt.setOnQueryTextListener(
                object :SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        cityViewModel.getCity(query.toString())

                        return false
                    }
                    override fun onQueryTextChange(newText: String?): Boolean {
                        cityViewModel.getCity(newText.toString())
                        return false
                    }
                }
            )

        cityrecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cityrecyclerView.adapter = citySearchAdapter

        cityViewModel.cityfood.observe(viewLifecycleOwner, Observer {

            if (it.isSuccessful)
                citySearchAdapter.citysetStateWiseTracker(it.body()?.location_suggestions!!)

            Log.d("BOLTy","success"+it.toString())



        })



    }

}