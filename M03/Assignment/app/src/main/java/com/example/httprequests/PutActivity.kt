package com.example.httprequests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.httprequests.api.JsonPlaceHolderAPI
import com.example.httprequests.models.Employee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PutActivity : AppCompatActivity(), Callback<Employee> {
    override fun onFailure(call: Call<Employee>, t: Throwable) {

    }

    override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
        response.body()?.let {
            Toast.makeText(this@PutActivity, it.toString(), Toast.LENGTH_LONG).show()
        }

    }


    lateinit var employeeServices: JsonPlaceHolderAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_put)
        title = "PUT REQUEST"
        employeeServices = JsonPlaceHolderAPI.Factory.create()
        updateEmployee()

    }
    private fun updateEmployee(){
        val employee = Employee("Brian", 12846, 48, "Instructor")
        employeeServices.updateEmployee(employee).enqueue(this)
    }
}
