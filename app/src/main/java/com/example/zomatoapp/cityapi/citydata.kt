package com.example.zomatoapp.cityapi

data class citydata(
    val has_more: Int? = null,
    val has_total: Int? = null,
    val location_suggestions: List<LocationSuggestion>? = null,
    val status: String?=null,
    val user_has_addresses: Boolean?= null
)