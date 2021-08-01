package com.example.zomatoapp.RestrauntApi

data class UserRating(
    val aggregate_rating: String? = null,
    val rating_color: String? = null,
    val rating_obj: RatingObj? = null,
    val rating_text: String? = null,
    val votes: Int? = null
)