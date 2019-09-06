package com.example.networkingassignment3

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_get.setOnClickListener {
            if (!isNetworkConnected()) {
                Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show()
            } else
            startActivity(Intent(this@MainActivity, GetPickerActivity::class.java))
        }
        btn_post.setOnClickListener {
            if (!isNetworkConnected()) {
                Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(this@MainActivity, PostActivity::class.java))
        }
        btn_put.setOnClickListener {
            if (!isNetworkConnected()) {
                Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(this@MainActivity, PutActivity::class.java))
        }
        btn_delete.setOnClickListener {
            if (!isNetworkConnected()) {
                Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(this@MainActivity, DeleteActivity::class.java))
        }
    }
    private fun isNetworkConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}
