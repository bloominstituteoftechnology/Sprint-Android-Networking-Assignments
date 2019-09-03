package com.example.mainacitivity.retrofit

import com.example.mainacitivity.model.OceaniaCountryList
import retrofit2.Call
import retrofit2.http.GET

interface CountriesAPI {

    @GET("oceania")
    fun getCountries(): Call<OceaniaCountryList>

}