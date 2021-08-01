package com.example.zomatoapp.cityapi

import com.google.gson.annotations.SerializedName

data class LocationSuggestion(
    val country_flag_url: String? = null,
    val country_id: Int? = null,
    val country_name: String? = null,
    val discovery_enabled: Int? = null,
    val has_go_out_tab: Int? = null,
    val has_new_ad_format: Int? = null,
    val id: Int? = null,
    val is_state: Int? = null,
    val name: String? = null,
    val should_experiment_with: Int? = null,
    val state_code: String? = null,
    val state_id: Int? = null,
    val state_name: String? = null
)