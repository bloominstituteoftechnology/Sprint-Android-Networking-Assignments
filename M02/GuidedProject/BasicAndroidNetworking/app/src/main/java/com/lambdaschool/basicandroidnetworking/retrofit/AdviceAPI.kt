package com.lambdaschool.basicandroidnetworking.retrofit

import android.telecom.Call

import com.lambdaschool.basicandroidnetworking.model.AdviceMsg

// TODO: Define AdviceAPI interface for Retrofit
// it sets up functions without implementation
interface AdviceAPI{

    fun randomAdvice(): Call<AdviceMsg>

}