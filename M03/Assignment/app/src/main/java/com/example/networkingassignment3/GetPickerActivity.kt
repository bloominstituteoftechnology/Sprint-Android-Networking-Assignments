package com.example.networkingassignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_get_picker.*

class GetPickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_picker)
        SimpleGetButton.setOnClickListener {
            val intent = Intent(this@GetPickerActivity, GetActivity::class.java)
            intent.putExtra("get", "simple")
            startActivity(intent)
        }

        PathGetButton.setOnClickListener {
            val intent = Intent(this@GetPickerActivity, GetActivity::class.java)
            intent.putExtra("get", "path")
            startActivity(intent)
        }

        QueryGetButton.setOnClickListener {
            val intent = Intent(this@GetPickerActivity, GetActivity::class.java)
            intent.putExtra("get", "query")
            startActivity(intent)
        }
    }
}
