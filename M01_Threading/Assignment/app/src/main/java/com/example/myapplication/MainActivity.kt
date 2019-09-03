package com.example.myapplication

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        prime_number_text.visibility = View.INVISIBLE
//        val primeNumbers = primes().take(16000).joinToString(", ")
//        prime_number_text.text = "Primes: $primeNumbers"
//        prime_number_text.visibility = View.VISIBLE

        toggleProgressBar()
        val task = MyAsyncTask()
        task.execute()
    }

//    fun primes(): Sequence<Long> {
//        var i = 0L
//        return sequence {
//            generateSequence { i++ }
//                .filter { n -> n > 1 && ((2 until sqrt(n.toDouble()).toInt()).none { i -> n % i == 0L }) }
//                .forEach { yield(it) }
//        }
//    }

    fun toggleProgressBar() {
        if (progressBar.visibility == View.VISIBLE) {
            progressBar.visibility = View.INVISIBLE
        } else {
            progressBar.visibility = View.VISIBLE
        }
    }

    //params(pass into doInBackground), progress(use to update the progress), result (passed into the post execute from doInBackground)
    private class MyAsyncTask : AsyncTask<String, Int, String>() {

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            toggleProgressBar() //hiding progress bar
            prime_number_text.text = result
            prime_number_text.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg p: String?): String {
            var i = 0L
            return sequence {
                generateSequence { i++ }
                    .filter { n -> n > 1 && ((2 until sqrt(n.toDouble()).toInt()).none { i -> n % i == 0L }) }
                    .forEach { yield(it) }
                onProgressUpdate(n)
            }.take(1600).joinToString(", ")
        }

        override fun onCancelled(result: String?) {
            super.onCancelled(result)
        }

        override fun onCancelled() {
            super.onCancelled()
        }

        override fun onPreExecute() {
            super.onPreExecute()
            toggleProgressBar()
            prime_number_text.visibility = View.INVISIBLE
        }
    }
}