package com.lambdaschool.httpoperations

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lambdaschool.httpoperations.model.Employee
import com.lambdaschool.httpoperations.retrofit.JsonPlaceHolderApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HttpPutActivity : AppCompatActivity(), Callback<Employee> {

    lateinit var employeeService: JsonPlaceHolderApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_get)
        title = "Put Request: Update existing Employee Steve"

        // TODO: Create the API object
        employeeService = JsonPlaceHolderApi.Factory.create()
        updateEmployee()
    }

    private fun updateEmployee(){
        // TODO: Write the call to update an employee
        employeeService.updateEmployee(employee).enqueue(this)
    }

    override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
        Toast.makeText(this@HttpGetActivity, "Failure", Toast.LENGTH_LONG).show()
    }

    override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
        Toast.makeText(this@HttpGetActivity, "Pass", Toast.LENGTH_LONG).show()
    }

}
