package com.example.countries

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//purpose of this class is basically to setup the base url of the retrofit class and the conversion factory
class OceaniaCountriesRetriever {

    companion object{
        private const val TAG = "RETRIEVER"
        internal const val BASE_URL = "https://restcountries.eu/"
    }

    fun getOceaniaCountries(): Call<OceaniaCountryList> {

        val gson = GsonBuilder() //gson is basically a java library that can convert json into an equivalent java object (so we dont have to parse json into our model objects)
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val countriesAPI = retrofit.create(CountriesAPI::class.java)

        return countriesAPI.getCountries() //this will return a call object that we can execute that should give us our list of oceanic countries
    }
}