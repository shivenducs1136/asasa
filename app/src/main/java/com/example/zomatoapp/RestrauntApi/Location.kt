package com.example.zomatoapp.RestrauntApi

data class Location(
    val address: String? = null,
    val city: String? = null,
    val city_id: Int? = null,
    val country_id: Int? = null,
    val latitude: String? = null,
    val locality: String? = null,
    val locality_verbose: String? = null,
    val longitude: String? = null,
    val zipcode: String? = null
)