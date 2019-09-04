package com.example.httpoperation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.httpoperation.R
import com.example.httpoperation.model.Employee
import com.example.httpoperation.retrofit.Factory
import com.example.httpoperation.retrofit.JsonPlaceHolderAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PutActivity : AppCompatActivity(), Callback<Employee> {
    override fun onFailure(call: Call<Employee>, t: Throwable) {

    }

    override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
        response.body()?.let {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    lateinit var employeesService: JsonPlaceHolderAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)
        title = "Put Request: Update existing Employee Steve"

        // TODO: Create the API object
        employeesService = Factory.create()
        updateEmployee()
    }

    private fun updateEmployee(){
        // TODO: Write the call to update an employee
        val employee = Employee("Steve", 1, 25, "Principal Engineer")
        employeesService.updateEmployee(employee).enqueue(this)
    }

}