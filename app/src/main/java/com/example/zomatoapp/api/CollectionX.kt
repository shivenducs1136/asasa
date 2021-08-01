package com.example.zomatoapp.api


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize



data class CollectionX(
    @SerializedName("collection_id")
    val collectionId: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("res_count")
    val resCount: Int?,
    @SerializedName("share_url")
    val shareUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?
)