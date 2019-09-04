package com.example.basicandroidnetworkingcountriesinoceania.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.basicandroidnetworkingcountriesinoceania.R
import com.example.basicandroidnetworkingcountriesinoceania.model.OceaniaCountry
import com.example.basicandroidnetworkingcountriesinoceania.retrofit.OceaniaCountriesRetriever
import retrofit2.Callback
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<List<OceaniaCountry>> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fetchCountriesButton.setOnClickListener {
            OceaniaCountriesRetriever().getOceaniaCountries().enqueue(this)
        }
    }

    override fun onFailure(call: Call<List<OceaniaCountry>>, t: Throwable) {
        t.printStackTrace()
        val response = "failure: ${t.printStackTrace()}"
        Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()

    }

    override fun onResponse(
        call: Call<List<OceaniaCountry>>,
        response: Response<List<OceaniaCountry>>
    ) {
        if (response.isSuccessful) {
            val oceaniaCountryList = response.body()
            for (i in 0 until oceaniaCountryList!!.size) {
                countriesTextView.append(oceaniaCountryList[i].subregion.toString()+", ")

            }// use "oceaniaCountryList" to populate this TextView
        } else {
            val response = "response not successful: ${response.errorBody().toString()}"
            Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
        }

    }


}
