package com.example.httprequests.api

import com.example.httprequests.models.Employee
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface JsonPlaceHolderAPI {

    @GET("employees")
    fun getEmployees() : Call<List<Employee>>

    @GET("employees/{id}")
    fun getEmployeesById(@Path("id")employeeId: String) : Call<List<Employee>>

    @GET("employees")
    fun getEmployeeByAge(@Query("age")employeeId: String) : Call<List<Employee>>

    // TODO post
    @POST("employees")
    fun addNewEmployee(@Body employee: Employee) : Call<Employee>

    // TODO put
    @PUT("employees")
    fun updateEmployee(@Body employee: Employee) : Call<Employee>

    //TODO delete
    @DELETE("employees/{id}")
    fun deleteEmployee(@Path("id") id: String) : Call<Void>


    // TODO create factory class

    class Factory {

        companion object {

             const val BASE_URL = "https://demo8143297.mockable.io"


            // TODO add a logging interceptor
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


            fun create(): JsonPlaceHolderAPI {

                val logger = HttpLoggingInterceptor()
                logger.level = HttpLoggingInterceptor.Level.BASIC
                logger.level = HttpLoggingInterceptor.Level.BODY

                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .retryOnConnectionFailure(false)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build()

                val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                return retrofit.create(JsonPlaceHolderAPI::class.java)
            }
        }
    }
}