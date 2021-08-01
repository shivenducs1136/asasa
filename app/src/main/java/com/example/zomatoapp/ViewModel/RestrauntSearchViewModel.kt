package com.example.zomatoapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zomatoapp.Repository.Repository
import com.example.zomatoapp.RestrauntApi.restraunt
import com.example.zomatoapp.cityapi.city
import kotlinx.coroutines.launch
import retrofit2.Response
// passing lattitude and longitude from GPS and getting the restraunt search data from respective api
class RestrauntSearchViewModel (private val repository: Repository): ViewModel() {

    var cityrestraunt: MutableLiveData<Response<restraunt>> =MutableLiveData()

    fun getRestraunt(lat:Double,lon:Double){

        viewModelScope.launch {
            val CityRestraunt = repository.getRestraunt(lat,lon)
            if (CityRestraunt.body()?.restaurants != null) {
                cityrestraunt.value = CityRestraunt
                Log.d("Hell", CityRestraunt.body().toString())
            } else {
                val CityRestraunt = repository.getRestraunt(28.704059, 77.10249)
                cityrestraunt.value = CityRestraunt
                Log.d("Hell", CityRestraunt.body().toString())
            }
        }
    }
}