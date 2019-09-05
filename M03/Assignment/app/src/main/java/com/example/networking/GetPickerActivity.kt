package com.example.networking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_get_picker.*

class GetPickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_picker)

        plain_get_btn.setOnClickListener {
            val intent = Intent(this, GetActivity::class.java)
            intent.putExtra("GET", "PLAIN")
            startActivity(intent)
        }

        query_get_btn.setOnClickListener {
            val intent = Intent(this, GetActivity::class.java)
            intent.putExtra("GET", "QUERY")
            startActivity(intent)
        }

        path_get_btn.setOnClickListener {
            val intent = Intent(this, GetActivity::class.java)
            intent.putExtra("GET", "PATH")
            startActivity(intent)
        }
    }
}
