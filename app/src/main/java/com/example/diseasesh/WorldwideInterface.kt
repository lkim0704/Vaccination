package com.example.diseasesh

import retrofit2.Call
import retrofit2.http.GET

interface WorldwideInterface {
    @GET("all")
    fun getWorldwide() : Call<Worldwide>
}