package com.example.tinkofflab

import com.google.gson.annotations.SerializedName

data class Posts(
    @SerializedName("result")
    val posts: List<Post>,
    @SerializedName("totalCount")
    val postsCount: Int
)