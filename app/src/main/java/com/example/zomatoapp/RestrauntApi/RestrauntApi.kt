package com.example.zomatoapp.RestrauntApi

import com.google.gson.annotations.SerializedName

class RestrauntApi (
    @SerializedName("RestaurantX")
    val collections: List<RestaurantX?>? = null,
) {
        data class RestaurantX(
            @SerializedName("R")
            val r: R,
            @SerializedName("all_reviews")
            val all_reviews: AllReviews? = null,
            @SerializedName("all_reviews_count")
            val all_reviews_count: Int? = null,
            @SerializedName("apikey")
            val apikey: String? = null,
            @SerializedName("average_cost_for_two")
            val average_cost_for_two: Int? = null,
            @SerializedName("book_again_url")
            val book_again_url: String? = null,
            @SerializedName("book_form_web_view_url")
            val book_form_web_view_url: String? = null,
            @SerializedName("cuisines")
            val cuisines: String? = null,
            @SerializedName("currency")
            val currency: String? = null,
            @SerializedName("deeplink")
            val deeplink: String? = null,
            @SerializedName("establishment")
            val establishment: List<String>? = null,
            @SerializedName("establishment_types")
            val establishment_types: List<Any>? = null,
            @SerializedName("events_url")
            val events_url: String? = null,
            @SerializedName("featured_image")
            val featured_image: String? = null,
            @SerializedName("has_online_delivery")
            val has_online_delivery: Int? = null,
            @SerializedName("has_table_booking")
            val has_table_booking: Int? = null,
            @SerializedName("highlights")
            val highlights: List<String>? = null,
            @SerializedName("id")
            val id: String? = null,
            @SerializedName("include_bogo_offers")
            val include_bogo_offers: Boolean? = null,
            @SerializedName("is_book_form_web_view")
            val is_book_form_web_view: Int? = null,
            @SerializedName("is_delivering_now")
            val is_delivering_now: Int? = null,
            @SerializedName("is_table_reservation_supported")
            val is_table_reservation_supported: Int? = null,
            @SerializedName("is_zomato_book_res")
            val is_zomato_book_res: Int? = null,
            @SerializedName("location")
            val location: Location? = null,
            @SerializedName("medio_provider")
            val medio_provider: Boolean? = null,
            @SerializedName("menu_url")
            val menu_url: String? = null,
            @SerializedName("mezzo_provider")
            val mezzo_provider: String? = null,
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("offers")
            val offers: List<Any>? = null,
            @SerializedName("opentable_support")
            val opentable_support: Int? = null,
            @SerializedName("order_deeplink")
            val order_deeplink: String? = null,
            @SerializedName("order_url")
            val order_url: String? = null,
            @SerializedName("phone_numbers")
            val phone_numbers: String? = null,
            @SerializedName("photo_count")
            val photo_count: Int? = null,
            @SerializedName("photos_url")
            val photos_url: String? = null,
            @SerializedName("price_range")
            val price_range: Int? = null,
            @SerializedName("store_type")
            val store_type: String? = null,
            @SerializedName("switch_to_order_menu")
            val switch_to_order_menu: Int? = null,
            @SerializedName("thumb")
            val thumb: String? = null,
            @SerializedName("timings")
            val timings: String? = null,
            @SerializedName("url")
            val url: String? = null,
            @SerializedName("user_rating")
            val user_rating: UserRating? = null
        )
    }

