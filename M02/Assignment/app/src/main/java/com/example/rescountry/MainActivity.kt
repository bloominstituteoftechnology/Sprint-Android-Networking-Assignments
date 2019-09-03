package com.example.rescountry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rescountry.models.OceaniaCountry


import com.example.rescountry.retrofeit.OceaniaCountriesReceiver
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call


import retrofit2.Callback


import retrofit2.Response


class MainActivity : AppCompatActivity(),
    Callback<OceaniaCountry> {
    override fun onResponse(
        call: Call<OceaniaCountry>,
        response: Response<OceaniaCountry>
    ) {
        if (response.isSuccessful) {
            val oceaniaCountryList = response.body()
            countriesTextView.text = oceaniaCountryList.toString() // use "oceaniaCountryList" to populate this TextView
        } else {
            val response = "response not successful; ${response.errorBody().toString()}"

            Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFailure(call: Call<OceaniaCountry>, t: Throwable) {
        t.printStackTrace()
        val response = "faliure; ${t.printStackTrace()}"



        Toast.makeText(this@MainActivity, response, Toast.LENGTH_LONG ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fetchCountriesButton.setOnClickListener {
            OceaniaCountriesReceiver().getCountries().enqueue(this)
        }

    }


}
