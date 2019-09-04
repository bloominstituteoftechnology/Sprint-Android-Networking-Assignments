package com.lambdaschool.httpoperations

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.lambdaschool.httpoperations.model.Employee
import com.lambdaschool.httpoperations.retrofit.JsonPlaceHolderApi
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class HttpGetActivity : AppCompatActivity(), Callback<List<Employee>> {

    // extracted retrofit variable from retrofit API
    lateinit var employeeService: JsonPlaceHolderApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_get)

        // TODO 3: Create the api (retrofit)
        val gson = Gson()
        employeeService = Retrofit.Builder()
            .baseUrl(JsonPlaceHolderApi.Factory.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JsonPlaceHolderApi::class.java)


        val type = intent.getStringExtra("get")
        if (type == "simple") {
            title = "GET - Simple Request"
            getEmployees()
        } else if (type == "path") {
            title = "GET - Path Parameter: EmployeeId - 1"
            getEmployees("1")
        } else {
            title = "GET - Query Parameter: Age - 55"
            getEmployeesByAge("55")
        }
    }

    fun getEmployees() {
        // TODO 4: Write the call for getting all employees (SIMPLE REQUEST)

        fun getEmployees(employeeId: String) {
            // TODO: Write the call to get an employee by id
            employeeService.getEmployees(employeeId).enqueue(this)
        }

        fun getEmployeesByAge(age: String) {
            // TODO: Write the call to get an employee by age
            employeeService.getEmployeesByAge(age).enqueue(this)
        }



    }
    override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
        Toast.makeText(this@HttpGetActivity, "Failure", Toast.LENGTH_LONG).show()
    }

    override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
        Toast.makeText(this@HttpGetActivity, "Pass", Toast.LENGTH_LONG).show()
    }
}
