package com.example.countries

import retrofit2.Call
import retrofit2.http.GET

interface CountriesAPI {

    //Retrofit works by modeling over a base URL and by making interfaces return the entities from the REST endpoint.
    @GET("rest/v2/region/oceania")
    fun getCountries(): Call<OceaniaCountryList>
    //the interface will be instantiated through retrofit and will return a call to the api endpoint, that retrieves
    //the data from the endpoint when it is executed and also will fit the data to our oceania country model class
}