package com.example.tinkofflab

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("gifURL")
    val gifUrl: String,
    @SerializedName("description")
    val description: String
)