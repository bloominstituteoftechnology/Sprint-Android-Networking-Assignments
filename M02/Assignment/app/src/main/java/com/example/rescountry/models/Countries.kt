package com.example.rescountry.models

//TODO 1 create countries model

// TODO 2 create an initial val for country and make an empty list for countries


data class OceaniaCountryList(val country: List<OceaniaCountry>) {

        // TODO 3 create an OceaniaCountry class to hold country information
        data class OceaniaCountry(
            val alpha2code: String,
            val capital: String,
            val subregion: String
        )


    }

