package com.lambdaschool.httpoperations.retrofit

import com.google.gson.Gson
import com.lambdaschool.httpoperations.model.Employee
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface JsonPlaceHolderApi {
    // TODO 2: Create API for different endpoints

    // create different endpoints
    @GET("employees/{id}")
    fun getEmployees(@Path("id") employeeId: String) : Call<List<Employee>>

    @GET("employees")
    fun getEmployees(): Call<List<Employee>>

    @GET("employees")
    fun getEmployeesByAge(@Query("age")employeeId: String) : Call<List<Employee>>

    //TODO 7

    @POST("employees")
    fun getEmployee(@Body employee: Employee):Call<Employee>

    @PUT ("employees")
    fun updateEmployee(@Body employee: Employee):Call<Employee>

    //TODO 7 delete

    @DELETE("employees/{id}")
    fun deleteEmployee(@Path("id") id: String) :Call<Void>


    //the idea behind factory is building pattern
    class Factory {
        companion object {
             val BASE_URL = "https://demo8143297.mockable.io/"
            //over here we are declaring variables to be able to access them anywhere
            val gson = Gson()
            //this is the logger for debugging purposes only
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
                level = HttpLoggingInterceptor.Level.BODY
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .retryOnConnectionFailure(false)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()
        }
        //over here we declare a function for create so we dont have to do it all over the place
        fun create(): JsonPlaceHolderApi{
            return  Retrofit.Builder()
                .baseUrl(JsonPlaceHolderApi.Factory.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(JsonPlaceHolderApi::class.java)
        }
        }
    }
