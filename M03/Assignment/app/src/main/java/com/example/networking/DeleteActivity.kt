package com.example.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.networking.model.Employee
import retrofit2.Call
import retrofit2.Response

class DeleteActivity : AppCompatActivity() {

    val e = EmployeeApiReceiver.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        e.deleteEmployeeById("1")
    }

    override fun onFailure(call: Call<Employee>, t: Throwable) {
        Toast.makeText(this, "FAIL", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
        Toast.makeText(this, response.body()?.name, Toast.LENGTH_SHORT).show()
    }
}
