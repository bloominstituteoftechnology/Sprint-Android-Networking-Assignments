package com.example.basicandroidnetworkingcountriesinoceania.retrofit

import com.example.basicandroidnetworkingcountriesinoceania.model.OceaniaCountry


import retrofit2.Call
import retrofit2.http.GET

interface CountriesAPI {


    @GET("oceania")
    fun getCountries(): Call<List<OceaniaCountry>>
}