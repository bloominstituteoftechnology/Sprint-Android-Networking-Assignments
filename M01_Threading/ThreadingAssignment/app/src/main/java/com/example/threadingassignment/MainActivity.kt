package com.example.threadingassignment


import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    lateinit var task: AsyncTask<Unit, Int, String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var progressBar = pBar


        // toggleVisibility(textView)
        //toggleVisibility(progressBar)






        class MyAsyncTask : AsyncTask<Unit, Int, String>() {

            override fun onPostExecute(result: String?) {
                toggleVisibility(progressBar)
                toggleVisibility(txt_primes)
                txt_primes.text = result
            }

            override fun doInBackground(vararg p0: Unit?): String {
                return primes().take(16000).joinToString(", ")
            }

            override fun onCancelled() {
                super.onCancelled()


                Log.i("OnCancelled", "OnCancelled Activated")

            }

            override fun onPreExecute() {
                super.onPreExecute()

                toggleVisibility(txt_primes)
                toggleVisibility(progressBar)
            }


        }

        task = MyAsyncTask()



        button.setOnClickListener {

            MyAsyncTask().execute()


        }

        onStop()

    }

    override fun onStop() {
        super.onStop()
        task.cancel(true)
        Log.i("Onstop", "Stopped Task")

    }



    fun primes(): Sequence<Long> {
        var i = 0L
        return sequence {
            generateSequence { i++ }
                .filter { n -> n > 1 && ((2 until n).none { i -> n % i == 0L }) }
                .forEach { yield(it) }
        }
    }


    fun toggleVisibility(view: View) {

        view.visibility = if (view.visibility == View.VISIBLE) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }


    }


}
