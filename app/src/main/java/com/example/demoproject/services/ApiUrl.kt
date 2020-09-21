package com.example.demoproject.services

import com.example.demoproject.model.ModelData
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiUrl() {

    private val BASE_URL = "https://api.github.com/users/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DataAPI::class.java)

    fun getData(ad: String) : Single<List<ModelData>> {
        return api.getBaseData(ad)
    }
}