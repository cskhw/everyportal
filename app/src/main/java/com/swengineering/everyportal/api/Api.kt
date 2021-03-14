package com.swengineering.everyportal.api

import com.google.gson.GsonBuilder
import com.swengineering.everyportal.api.service.Auth
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    private lateinit var retrofit: Retrofit

    private var baseUrl: String = "http://localhost"
    private var authToken: String? = null

    // create service
    lateinit var auth: Auth
    private val signIn get() = authToken != null
    fun update(
        baseUrl: String? = null,
        authToken: String? = null
    ) {
        Api.baseUrl = baseUrl ?: Api.baseUrl
        Api.authToken = authToken ?: Api.authToken
        val retrofitBuilder = Retrofit.Builder()
        val clientBuilder = OkHttpClient.Builder()
        if (signIn) clientBuilder.addInterceptor(AuthorizationHeaderInterceptor(Api.authToken!!))
        val gsonBuilder = GsonBuilder()
            .setLenient()

        retrofitBuilder.baseUrl(Api.baseUrl)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
        retrofit = retrofitBuilder.build()

        // register service
        auth = retrofit.create(Auth::class.java)
    }

    init {
        update()
    }

    private class AuthorizationHeaderInterceptor(private val authToken: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()

            requestBuilder.removeHeader("authorization").addHeader(
                "authorization",
                authToken
            )

            return chain.proceed(requestBuilder.build())
        }
    }

}