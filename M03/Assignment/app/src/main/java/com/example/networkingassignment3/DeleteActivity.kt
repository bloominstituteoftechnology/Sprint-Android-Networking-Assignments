package com.example.networkingassignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_get.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteActivity : AppCompatActivity(), Callback<Void> {
    lateinit var employeeService:JsonPlaceHolderAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)
        employeeService= JsonPlaceHolderAPI.Factory.create()
        deleteEmployee("1")
    }
    fun deleteEmployee(employeeId:String){
        employeeService.deleteEmployeeById(employeeId).enqueue(this)
    }
    override fun onResponse(call: Call<Void>, response: Response<Void>) {
        if(response.isSuccessful)
            Toast.makeText(this,"Successfully Deleted", Toast.LENGTH_SHORT).show()
        progressBar.isVisible=false
        result.text="Deleted Successfully"
        }

    override fun onFailure(call: Call<Void>, t: Throwable) {
        Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
    }
}
