package com.example.mainacitivity.model

data class OceaniaCountryList(// define "OceaniaCountry" class soon...
    val country: List<OceaniaCountry>

    )


data class OceaniaCountry (
    val alpha2code: String,
    val capital: String,
    val subRegion: String,
    val name: String
    // other fields here
)