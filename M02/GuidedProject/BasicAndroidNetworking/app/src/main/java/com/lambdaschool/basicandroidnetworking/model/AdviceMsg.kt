package com.lambdaschool.basicandroidnetworking.model

// TODO: Define AdviceMsg and Slip classes

//!!! this is a model to define and reference our Json file

data class Slip(val advice: String?, val slip_id: String?)

data class AdviceMsg(val slip:Slip? ){

    // TODO 6 get advice off the slip by creating a fun

    fun getAdvice(): String?{
        return slip?.advice
    }

}
