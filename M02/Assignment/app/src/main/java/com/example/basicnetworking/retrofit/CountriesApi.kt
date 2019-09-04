package com.example.basicnetworking.retrofit

import com.example.basicnetworking.model.OceaniaCountry
import retrofit2.Call
import retrofit2.http.GET

interface CountriesApi {
    @GET("Oceania")
    fun getCountries(): Call<List<OceaniaCountry>>
}