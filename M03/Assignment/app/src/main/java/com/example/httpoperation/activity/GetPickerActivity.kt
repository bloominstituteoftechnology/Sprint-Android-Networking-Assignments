package com.example.httpoperation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.httpoperation.R
import kotlinx.android.synthetic.main.activity_get_picker.*

class GetPickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_picker)

        simple_get_call.setOnClickListener {
            val intent = Intent(this@GetPickerActivity, GetActivity::class.java)
            intent.putExtra("get", "simple")
            startActivity(intent)
        }

        path_get_call.setOnClickListener {
            val intent = Intent(this@GetPickerActivity, GetActivity::class.java)
            intent.putExtra("get", "path")
            startActivity(intent)
        }

        query_get_call.setOnClickListener {
            val intent = Intent(this@GetPickerActivity, GetActivity::class.java)
            intent.putExtra("get", "query")
            startActivity(intent)
        }
    }
}

