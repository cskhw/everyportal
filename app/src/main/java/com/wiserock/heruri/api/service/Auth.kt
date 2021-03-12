package com.wiserock.heruri.api.service

import com.wiserock.heruri.model.SignIn
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Auth {

    @POST("login/index.php")
    fun signIn(
        @Body signIn: SignIn
    ): Call<Void>
}