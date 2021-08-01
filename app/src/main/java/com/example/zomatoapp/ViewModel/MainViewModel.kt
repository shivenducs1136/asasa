package com.example.zomatoapp.ViewModel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zomatoapp.Repository.Repository
import com.example.zomatoapp.api.CollectionX
import retrofit2.Response
import androidx.lifecycle.*
import com.example.zomatoapp.api.FoodApi
import com.example.zomatoapp.api.food
import kotlinx.coroutines.launch
import java.util.logging.Handler

//this view model has the collections and restraunt data
class MainViewModel(private val repository: Repository) : ViewModel() {

    var showProgress: LiveData<Boolean>
    var food: MutableLiveData<Response<food>> = MutableLiveData()
    var cityshowProgress: LiveData<Boolean>? = null
    var city: MutableLiveData<Response<food>> = MutableLiveData()

    init {

        this.showProgress = repository.showProgress
        // this.food = MutableLiveData()

        this.cityshowProgress = repository.cityshowprogress
        //   this.city= MutableLiveData()
    }


    fun getFood(lat: Double, lon: Double) {

        viewModelScope.launch {
            var food1 = repository.getPost(lat, lon)

            if (food1.body()?.collections != null) {
                food1 = repository.getPost(lat, lon)

                food.value = food1
                Log.d("HELLO", food1.body().toString())
            } else {
                food1 = repository.getPost(28.704059, 77.10249)
                food.value = food1

            }

        }
    }

    fun getcityid(cityid: String) {
        viewModelScope.launch {
            val id1 = repository.getCityid(cityid)
            if (id1.body()?.collections != null) {
                city.value = id1
                Log.d("cityidres", id1.body().toString())
            } else {
                val id1 = repository.getCityid("1")
                food.value = id1
                Log.d("cityidres", id1.body().toString())
            }
        }
    }
}
