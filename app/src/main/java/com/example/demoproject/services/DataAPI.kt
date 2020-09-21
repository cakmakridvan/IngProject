package com.example.demoproject.services

import com.example.demoproject.model.ModelData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DataAPI {

    @GET("{name}/repos")
    fun getBaseData(@Path("name") ad: String?): Single<List<ModelData>>
}