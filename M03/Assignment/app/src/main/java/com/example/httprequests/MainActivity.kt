package com.example.httprequests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getButton.setOnClickListener {
            val intent = Intent(this, GetPickerActivity::class.java)
            startActivity(intent)

        }
        postButton.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }
        putButton.setOnClickListener {
            val intent = Intent(this, PutActivity::class.java)
            startActivity(intent)
        }
        deleteButton.setOnClickListener {
            val intent = Intent(this, DeleteActivity::class.java)
            startActivity(intent)
        }


    }
}
