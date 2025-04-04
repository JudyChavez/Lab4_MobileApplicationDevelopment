package com.example.userdirectory.data

import com.example.userdirectory.network.UserApiService
//import com.example.userdirectory.network.UserDirectory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AppContainer {
    val userDirectoryRepository: UserDirectoryRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl =
        "https://randomuser.me"

    //"https://android-kotlin-fun-mars-server.appspot.com"
        //"https://android-kotlin-fun-mars-server.appspot.com/photos"
    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    /*private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()*/
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }

    override val userDirectoryRepository: UserDirectoryRepository by lazy {
        NetworkUserDirectoryRepository(retrofitService)
    }
}