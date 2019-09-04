package com.example.basicnetworking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import com.example.basicnetworking.model.OceaniaCountry
import com.example.basicnetworking.retrofit.OceaniaCountriesRetriever
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<List<OceaniaCountry>> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchCountriesButton.setOnClickListener {
            OceaniaCountriesRetriever().getOceaniaCountries().enqueue(this)
        }
    }
    override fun onResponse(call: Call<List<OceaniaCountry>>, response: Response<List<OceaniaCountry>>) {
        if (response.isSuccessful) {
            val oceaniaCountryList = response.body()
            // Loop through each country in the "oceaniaCountryList", add it to a StringBuilder, and then place results in the TextView
           var oceaniaCountries =""
            oceaniaCountryList?.forEach {
                oceaniaCountries += it.name+'\n'

            }
            countriesTextView.text = oceaniaCountries

            // quick way to make textview scrollable (when TextView is of FIXED SIZE)
            countriesTextView.movementMethod=ScrollingMovementMethod()
        } else {
            val response = "response not successful; ${response.errorBody().toString()}"
            Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
        }
    }
    override fun onFailure(call: Call<List<OceaniaCountry>>, t: Throwable) {
        t.printStackTrace()
        val response = "faliure; ${t.printStackTrace()}"
        Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
    }
}
