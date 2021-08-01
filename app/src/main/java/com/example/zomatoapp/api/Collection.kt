package com.example.zomatoapp.api


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class Collection(
    @SerializedName("collection")
    val collection: CollectionX
)