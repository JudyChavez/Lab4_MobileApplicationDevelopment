package com.example.userdirectory.data

//import com.example.userdirectory.network.UserApi
import com.example.userdirectory.network.UserApiService
import com.example.userdirectory.network.UserDirectory

// Interface for the repository that will fetch user directory data
interface UserDirectoryRepository {
    suspend fun getUserDirectory(): List<UserDirectory>
}

// Network implementation of the repository
class NetworkUserDirectoryRepository(
    private val userApiService: UserApiService //constructor parameter
) : UserDirectoryRepository {
    // Implementation of the suspend function to get data
    //override suspend fun getUserDirectory(): List<UserDirectory> = userApiService.getDirectory() // Calls the getDirectory() suspend function from UserApiService

//    override suspend fun getUserDirectory(): List<UserDirectory> {
//        return try {
//            val response = userApiService.getDirectory() // Fetch user data from API
//            response.results // Return the list of users
//            //userApiService.getDirectory() // Call to fetch data from API
//        } catch (e: Exception) {
//            // Handle network errors such as IOException or HttpException
//            emptyList() // You can choose to return an empty list or rethrow the exception
//        }
//    }

    override suspend fun getUserDirectory(): List<UserDirectory> = userApiService.getDirectory().results
}

