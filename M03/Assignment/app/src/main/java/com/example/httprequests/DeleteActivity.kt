package com.example.httprequests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.httprequests.api.JsonPlaceHolderAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteActivity : AppCompatActivity(), Callback<Void> {
    override fun onFailure(call: Call<Void>, t: Throwable) {

    }

    override fun onResponse(call: Call<Void>, response: Response<Void>) {
        if (response.isSuccessful){
            Toast.makeText(this@DeleteActivity, "Deletion was Successful", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this@DeleteActivity, "Deletion was NOT Successful", Toast.LENGTH_LONG).show()

        }
    }

    lateinit var employeeService: JsonPlaceHolderAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
    title = "DELETE REQUEST"

        employeeService = JsonPlaceHolderAPI.Factory.create()
        deleteEmployee()


    }
    fun deleteEmployee(){
        employeeService.deleteEmployee("5").enqueue(this)
    }

}
