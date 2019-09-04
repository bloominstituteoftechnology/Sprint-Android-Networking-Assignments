package com.example.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<OceaniaCountryList> {

    lateinit var oceaniaListAdapter: OceaniaListAdapter
    var oceaniaList : OceaniaCountryList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetch_countries_btn.setOnClickListener {
           OceaniaCountriesRetriever().getOceaniaCountries().enqueue(this)
        }
    }

    override fun onFailure(call: Call<OceaniaCountryList>, t: Throwable) {
        t.printStackTrace()
        val response = "faliure; ${t.printStackTrace()}"
        Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
    }

    //callback for when we do enqueue
    override fun onResponse(call: Call<OceaniaCountryList>, response: Response<OceaniaCountryList>) {
        if (response.isSuccessful) {
            oceaniaList = response.body()
            val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

            oceaniaList?.let{
                oceaniaListAdapter = OceaniaListAdapter(it)
                oceania_list.apply{
                    setHasFixedSize(true)
                    layoutManager = manager
                    adapter = oceaniaListAdapter
                }
            }

        } else {
            val response = "response not successful; ${response.errorBody().toString()}"
            Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
        }
    }
}
