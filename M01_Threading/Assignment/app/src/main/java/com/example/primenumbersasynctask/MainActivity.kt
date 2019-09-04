package com.example.primenumbersasynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.max = 1

        button.setOnClickListener {
            progressBar.progress = 0
            progressBar.visibility = View.VISIBLE
            MyAsyncTask().execute()
        }


    }
    inner class MyAsyncTask: AsyncTask<String, Int, String>(){

        private var result: String = " "

        override fun onPostExecute(result: String?) {
            progressBar.visibility = View.GONE
            textView.text = result

            button.text = "RESTART"

        }

        override fun doInBackground(vararg p0: String?): String {
            fun primes(): Sequence<Long> {
                var i = 0L
                return sequence {
                    generateSequence { i++ }
                        .filter { n -> n > 1 && ((2 until n).none { i -> n % i == 0L }) }
                        .forEach { yield(it) }
                }

            }

            val primeNumbers = primes().take(160).joinToString(", ")
            return primeNumbers
        }

        override fun onCancelled() {

        }

        override fun onPreExecute() {
            textView.text = "Task Starting"
        }
    }

}