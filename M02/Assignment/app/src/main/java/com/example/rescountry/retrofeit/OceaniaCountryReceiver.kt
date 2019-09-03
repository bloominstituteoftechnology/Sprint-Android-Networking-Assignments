package com.example.rescountry.retrofeit

import com.example.rescountry.interfaces.OceaniaCountriesAPI
import com.example.rescountry.models.OceaniaCountry
import com.example.rescountry.models.OceaniaCountryList


import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OceaniaCountriesReceiver {

       val BASE_URL = "https://restcountries.eu/rest/v2/regionalbloc/"



    // TODO 7 create a fun to parse the list
    fun getCountries(): Call<List<OceaniaCountryList>> {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val countriesAPI = retrofit.create(OceaniaCountriesAPI::class.java)

        return countriesAPI.getCountries()


    }
}