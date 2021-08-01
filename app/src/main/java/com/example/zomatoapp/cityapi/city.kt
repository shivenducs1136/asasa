package com.example.zomatoapp.cityapi

import com.example.zomatoapp.api.food
import com.google.gson.annotations.SerializedName

data class city(
    @SerializedName("location_suggestions")
    val location_suggestions: List<LocationSuggestion>? = null,

){

    data class LocationSuggestion (
        @SerializedName("id")
        val id:Int?= null,
        @SerializedName("name")
        val name:String? =null,
        @SerializedName("country_flag_url")
        val country_flag_url: String? = null,
        @SerializedName ("country_id")
        val country_id:Int?= null ,

    )
}
