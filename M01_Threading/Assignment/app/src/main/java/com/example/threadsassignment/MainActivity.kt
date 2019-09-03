package com.example.threadsassignment

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     MyAsyncTask().execute()

    }

    override fun onStop() {
        super.onStop()
        MyAsyncTask().cancel(true)
    }

    fun primes(): Sequence<Long> {
        var i = 0L
        return sequence {
            generateSequence { i++ }
                .filter { n -> n > 1 && ((2 until n).none { i -> n % i == 0L }) }
                .forEach { yield(it) }
        }
    }
    fun toggleProgressBar(visible:Boolean){

        if(visible)
        {
            progress_bar.visibility = View.VISIBLE
        txt_primes.visibility = View.INVISIBLE
        }
        else {
            progress_bar.visibility = View.INVISIBLE
            txt_primes.visibility = View.VISIBLE
        }

    }
    private inner class MyAsyncTask : AsyncTask<Unit, Int, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            toggleProgressBar(true)
        }
        override fun doInBackground(vararg p0: Unit?): String {
            val primeNumbers = primes().take(16000).joinToString(", ")
            return primeNumbers


        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            toggleProgressBar(false)
            txt_primes.text = "Primes: $result"

        }
        override fun onCancelled() {
            super.onCancelled()
            println("BG Task Cancelled")
        }
    }
}
