package com.lambdaschool.basicandroidnetworking.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lambdaschool.basicandroidnetworking.R
import com.lambdaschool.basicandroidnetworking.model.AdviceMsg
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: declare this activity handles Retrofit callbacks
class RetrofitActivity : AppCompatActivity(), Callback<AdviceMsg> {
    override fun onFailure(call: Call<AdviceMsg>, t: Throwable) {
        Toast.makeText(this, "what ???", Toast.LENGTH_LONG).show()
    }

    override fun onResponse(call: Call<AdviceMsg>, response: Response<AdviceMsg>) {
        adviceTextRetrofit.text = response.body()?.getAdvice() ?:  "the ccall failed "

    }

    companion object {
        private const val TAG = "RETROFIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        fetchNetworkAPIRetrofitButton.setOnClickListener {

            // TODO: Get advice without logging
            //get an instance of the retreiver object
            val retriever = AdviceRetriever()
            //returns a call and enque it
            retriever.getRandomAdvice().enqueue(this) //provide a callback


        }

        fetchNetworkAPIOkHttpButton.setOnClickListener {
            //TODO: Get advice with logging
        }
    }

    // TODO: Define callback for Retrofit onResponse

    // TODO: Define callback for Retrofit onFailure
}
