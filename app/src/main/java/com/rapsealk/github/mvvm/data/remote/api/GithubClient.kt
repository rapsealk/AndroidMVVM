package com.rapsealk.github.mvvm.data.remote.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rapsealk on 2019-10-26..
 */
class GithubClient {

    companion object {
        private val client by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        fun getInstance() = client.create(GithubApi::class.java)
    }
}