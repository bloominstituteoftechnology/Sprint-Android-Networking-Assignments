package com.example.networking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.networking.model.Employee
import kotlinx.android.synthetic.main.activity_get.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetActivity : AppCompatActivity(), Callback<List<Employee>> {

    val e: EmployeeAPI = EmployeeApiReceiver.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)


        when(intent.getStringExtra("GET")){

            "PLAIN"->{
                e.getEmployees().enqueue(this)
            }

            "QUERY"->{
                e.getEmployeesByAge("45").enqueue(this)
            }

            "PATH"->{
                e.getEmployeesById("2").enqueue(this)
            }
        }


    }

    override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
        Toast.makeText(this, "FAIL", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
        response.apply {
            get_text.text = body().toString()
        }
    }
}
