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

class PostActivity : AppCompatActivity(), Callback<Employee> {
    override fun onFailure(call: Call<Employee>, t: Throwable) {
    }

    override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
        response.body()?.let{
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    lateinit var employeesService: JsonPlaceHolderAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)
        title = "Post Request: New Employee David"

        // TODO: create the API object
        employeesService = Factory.create()
        addnewEmployee()
    }

    private fun addnewEmployee(){
        // TODO: Write the call to add a new employee
        val employee = Employee("David", 7, 30, "Intern")
        employeesService.addNewEmployee(employee).enqueue(this)
    }
}
