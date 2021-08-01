package com.example.zomatoapp

import com.example.zomatoapp.RestrauntApi.restraunt
import com.example.zomatoapp.api.food
import com.example.zomatoapp.cityapi.city
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


//In this interface we are making Requests


interface ApiRequest {
    companion object{
        const val userapi:String="f5cd87e4deca15ff2a49d74ce3328e1b"
    }
    @GET("/api/v2.1/collections")
    suspend fun getFood(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Header(  "user-key")user_key:String= userapi,
    ) : Response<food>

     @GET("/api/v2.1/collections")
     suspend fun getCityid(
         @Query("city_id") city_id:String,
         @Header(  "user-key")user_key:String= userapi,

         ) :Response<food>


    @GET("/api/v2.1/cities")
     suspend fun getcity(
        @Query("q") q : String,
        @Header(  "user-key")user_key:String= userapi,
     ) : Response<city>

     @GET("/api/v2.1/search")
     suspend fun getRestraunt(
         @Query("lat") lat: Double,
         @Query("lon") lon: Double,
         @Header(  "user-key")user_key:String= userapi,
     ) : Response<restraunt>
}