package com.example.httprequests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.httprequests.api.JsonPlaceHolderAPI
import com.example.httprequests.models.Employee
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetActivity : AppCompatActivity(), Callback<List<Employee>> {
    override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
        Toast.makeText(this@GetActivity, "Failure, change the code!", Toast.LENGTH_LONG).show()
    }

    override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
        Toast.makeText(this@GetActivity, "Success, you re on the right track!", Toast.LENGTH_LONG).show()
    }


    lateinit var employeeService: JsonPlaceHolderAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)


        // TODO 3: Create the api (retrofit)
        val gson = Gson()
        employeeService = Retrofit.Builder()
            .baseUrl(JsonPlaceHolderAPI.Factory.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JsonPlaceHolderAPI::class.java)

        val type = intent.getStringExtra("get")
        if (type == "simple") {
            title = "GET - Simple Request"
            getEmployees()

     } else if (type == "path") {
           title = "GET - Path Parameter: EmployeeId - 1"
         getEmployeesById("1")
      } else {
          title = "GET - Query Parameter: Age - 55"
  //          getEmployees("55")
       }



    }
    fun getEmployeesById(employeeId: String){
        employeeService.getEmployeesById(employeeId).enqueue(this)
    }


    fun getEmployees() {
            employeeService.getEmployees().enqueue(object: Callback<List<Employee>>{
                override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                    Toast.makeText(this@GetActivity, "Failure, change the code!", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<List<Employee>>,
                    response: Response<List<Employee>>
                ) {
                    //rather than doing null check can do let
                    response.body().let {
                        Toast.makeText(this@GetActivity, it.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                }
            } )
        }
    }
