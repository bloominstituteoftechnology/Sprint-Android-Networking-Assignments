package com.example.networkingassignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.networkingassignment3.model.Employee
import kotlinx.android.synthetic.main.activity_get.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetActivity : AppCompatActivity(), Callback<List<Employee>> {
lateinit var employeeService:JsonPlaceHolderAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)
        employeeService= JsonPlaceHolderAPI.Factory.create()
        val type = intent.getStringExtra("get")
        if (type == "simple") {
            title = "GET - Simple Request"
            getEmployees()
        } else if (type == "path") {
            title = "GET - Path Parameter: EmployeeId - 2"
            getEmployeesbyId("2")
        }
        else{
            title = "GET - Query Parameter: Age - 45"
            getEmployeesForAge("45")
        }
    }

    private fun getEmployees(){
        employeeService.getEmployees().enqueue(this)
    }

    private fun getEmployeesbyId(employeeId: String){
        employeeService.getEmployeesById(employeeId).enqueue(this)
    }

    private fun getEmployeesForAge(age: String){
        employeeService.getEmployeesByAge(age).enqueue(this)
    }

    override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
        response.body()?.let {
            Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
            progressBar.isVisible=false
            result.text=it.toString()
        }

    }

    override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
        Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
    }
}

