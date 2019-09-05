package com.example.httprequests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.httprequests.api.JsonPlaceHolderAPI
import com.example.httprequests.models.Employee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO call back

class PostActivity : AppCompatActivity(), Callback<Employee> {
    override fun onFailure(call: Call<Employee>, t: Throwable) {

    }

    override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
        response.body()?.let {
            Toast.makeText(this@PostActivity, it.toString(), Toast.LENGTH_LONG).show()
        }

    }

    lateinit var employeeService: JsonPlaceHolderAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        title = "postRequest: New Employee David"


        // TODO build API
        employeeService = JsonPlaceHolderAPI.Factory.create()
            addNewEmployee()

    }
    fun addNewEmployee(){
        val employee = Employee("Brian", 12846, 40, "Instructor")

        employeeService.addNewEmployee(employee).enqueue(this)
    }
}
