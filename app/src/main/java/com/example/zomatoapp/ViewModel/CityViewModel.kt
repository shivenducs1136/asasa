package com.example.zomatoapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zomatoapp.Repository.Repository
import com.example.zomatoapp.cityapi.LocationSuggestion
import com.example.zomatoapp.cityapi.city
import kotlinx.coroutines.launch
import retrofit2.Response

// Entering City name in the map fragment search view and getting the respective City ID for further use.
class CityViewModel(private val repository: Repository): ViewModel() {

    var cityshowProgress: LiveData<Boolean>
    var cityfood: MutableLiveData<Response<city>>

    init {
        this.cityshowProgress=repository.cityshowprogress
        this.cityfood= MutableLiveData()
    }

    fun getCity(city:String){
        viewModelScope.launch {
            val Cityfood=repository.getCity(city)
            cityfood.value=Cityfood
            Log.d("HelloCity",Cityfood.body().toString())
        }
    }
}