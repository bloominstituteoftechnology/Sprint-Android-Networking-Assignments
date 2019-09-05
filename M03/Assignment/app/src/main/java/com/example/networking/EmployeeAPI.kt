package com.example.networking

import com.example.networking.model.Employee
import retrofit2.Call
import retrofit2.http.*

interface EmployeeAPI {

    @GET("employees")
    fun getEmployees(): Call<List<Employee>>

    @GET("employees/{id}")
    fun getEmployeesById(@Path("id")employeeId:String): Call<List<Employee>>

    @GET("employees")
    fun getEmployeesByAge(@Query("age")employeeAge:String): Call<List<Employee>>

    @POST("employees")
    fun addNewEmployee(@Body employee:Employee): Call<Employee>

    @PUT("employees")
    fun updateEmployee(@Body employee:Employee): Call<Employee>

    @DELETE("employees/{id}")
    fun deleteEmployeeById(@Path("id")id:String): Call<Void>

}