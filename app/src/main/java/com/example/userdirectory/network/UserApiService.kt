package com.example.userdirectory.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

////constant for the base url
//private const val BASE_URL =
//    "https://android-kotlin-fun-mars-server.appspot.com/photos"
//    //"https://randomuser.me/api/?results=20"
//
////Retrofit builder to build and create a Retrofit object.
////Retrofit needs a base URI and a converter factory to build a web services API.
////The converter tells Retrofit what to do with the data it gets back from the web service and return as a String.
//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())/*ScalarsConverterFactory.create()*/)
//    .baseUrl(BASE_URL)
//    .build()

//defines how Retrofit talks to the web server using HTTP requests.
interface UserApiService {
    @GET(value = "api/?results=20"/*"photos"*/) //When the getPhotos() method is invoked, Retrofit appends the endpoint photos to the base URL
    suspend fun getDirectory(): UserDirectoryResponse/*List<UserDirectory>*///String //get the response string from the web service.
}

////object declaration, to initialize the Retrofit service. This is a public object singleton.
//object UserApi {
//    //"lazy initialization" is when object creation is purposely delayed,
//        // until you actually need that object
//    val retrofitService : UserApiService by lazy {
//        retrofit.create(UserApiService::class.java)
//    }
//}
//
////Each time your app calls MarsApi.retrofitService,
//    // the caller accesses the same singleton Retrofit object that implements MarsApiService,
//    // which is created on the first access.