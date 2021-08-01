package com.example.zomatoapp.api

import com.google.gson.annotations.SerializedName

data class food(
    @SerializedName("collections")
    val collections: List<Collection?>? = null,
    @SerializedName("display_text")
    val displayText: String? = null,
    @SerializedName("has_more")
    val hasMore: Int? = null,
    @SerializedName("has_total")
    val hasTotal: Int? = null,
    @SerializedName("share_url")
    val shareUrl: String? = null,
    @SerializedName("user_has_addresses")
    val userHasAddresses: Boolean? = null
) {
    data class Collection(
        @SerializedName("collection")
        val collection: CollectionY? = null
    ) {
        data class CollectionY(
            @SerializedName("collection_id")
            val collectionId: Int? = null,
            @SerializedName("description")
            val description: String? = null,
            @SerializedName("image_url")
            val imageUrl: String? = null,
            @SerializedName("res_count")
            val resCount: Int? = null,
            @SerializedName("share_url")
            val shareUrl: String? = null,
            @SerializedName("title")
            val title: String? = null,
            @SerializedName("url")
            val url: String? = null
        )
    }
}
