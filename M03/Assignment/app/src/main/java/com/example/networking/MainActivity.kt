package com.example.networking

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val toast = Toast.makeText(this, "No Network Connectivity", Toast.LENGTH_SHORT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        get_btn.setOnClickListener {
            if(isConnected()) {
                val intent = Intent(this, GetActivity::class.java)
                startActivity(intent)
            } else{
                toast.show()
            }
        }

        post_btn.setOnClickListener {
            if(isConnected()) {
                val intent = Intent(this, PostActivity::class.java)
                startActivity(intent)
            }else{
                toast.show()
            }
        }

        put_btn.setOnClickListener {
            if(isConnected()) {
                val intent = Intent(this, PutActivity::class.java)
                startActivity(intent)
            }else{
                toast.show()
            }
        }

        delete_btn.setOnClickListener {
            if(isConnected()) {
                val intent = Intent(this, DeleteActivity::class.java)
                startActivity(intent)
            } else{
                toast.show()
            }
        }
    }

    fun isConnected() : Boolean{
        val connectivityManager : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected()
    }
}
