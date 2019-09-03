package com.example.rescountry.interfaces



import com.example.rescountry.models.OceaniaCountry
import retrofit2.Call

import retrofit2.http.GET


//TODO 4 create an interface for API Url and returns the results for CountryList List model

interface OceaniaCountriesAPI {

    //TODO 5 get the last position of the url

    @GET("<https://restcountries.eu/#api-endpoints-all>")
    //then create a function to call the api from the web and populate the list
    fun getCountries(): Call<OceaniaCountry>
}


