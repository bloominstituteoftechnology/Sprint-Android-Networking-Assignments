package com.example.httprequests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_get_picker.*

class GetPickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_picker)

        simplegetButton.setOnClickListener {
            val intent = Intent(this, GetActivity::class.java)
            startActivity(intent)
        }
        pathParamsButton.setOnClickListener {
            val intent = Intent(this, GetActivity::class.java)
            startActivity(intent)
        }
        queryButton.setOnClickListener {
            val intent = Intent(this, GetActivity::class.java)
            startActivity(intent)
        }

    }
}
