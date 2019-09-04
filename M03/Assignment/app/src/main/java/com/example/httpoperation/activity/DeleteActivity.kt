package com.example.httpoperation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.httpoperation.R
import com.example.httpoperation.retrofit.Factory
import com.example.httpoperation.retrofit.JsonPlaceHolderAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteActivity : AppCompatActivity(), Callback<Void> {
    override fun onFailure(call: Call<Void>, t: Throwable) {
    }

    override fun onResponse(call: Call<Void>, response: Response<Void>) {
        if(response.isSuccessful){
            Toast.makeText(this,"Successfully deleted employee", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Failed to delete the employee", Toast.LENGTH_SHORT).show()
        }
    }

    lateinit var employeesService: JsonPlaceHolderAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)
        title = "Delete Request: Delete existing employee Steve, id 1"

        employeesService = Factory.create()
        deleteEmployee()
    }

    private fun deleteEmployee(){
        // TODO: delete the employee
        employeesService.deleteEmployeeById("1").enqueue(this)
    }
}
