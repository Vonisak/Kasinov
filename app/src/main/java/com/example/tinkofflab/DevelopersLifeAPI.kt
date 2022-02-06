package com.example.tinkofflab

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface DevelopersLifeAPI {
    @GET("{section}/{page}?json=true")
    fun getPosts(
        @Path("section") section: String,
        @Path("page") page: Int
    ) : Observable<Posts>
    @GET("random?json=true")
    fun getRandomPost() : Observable<Post>
}