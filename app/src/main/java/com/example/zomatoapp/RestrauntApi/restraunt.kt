package com.example.zomatoapp.RestrauntApi

data class restraunt(
    val restaurants: List<Restaurant>? = null,
    val results_found: Int? = null,
    val results_shown: Int? = null,
    val results_start: Int? = null
)