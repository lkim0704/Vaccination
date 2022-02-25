package com.example.diseasesh

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Covid19Interface {
    @GET("vaccine/coverage/countries")
    fun getVaccinations(@Query("lastdays") lastDays : Int) : Call<List<Vaccination>>
}