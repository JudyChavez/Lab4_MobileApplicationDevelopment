package com.example.userdirectory.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
//import com.example.marsphotos.MarsPhotosApplication
//import com.example.marsphotos.data.MarsPhotosRepository
//import com.example.marsphotos.data.NetworkMarsPhotosRepository
////import com.example.marsphotos.network.MarsApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

import androidx.lifecycle.viewmodel.initializer

//import com.example.marsphotos.network.MarsPhoto

//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
//import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
//import androidx.lifecycle.viewmodel.viewModelFactory
//import com.example.marsphotos.MarsPhotosApplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userdirectory.UserDirectoryApplication
import com.example.userdirectory.data.NetworkUserDirectoryRepository
import com.example.userdirectory.data.UserDirectoryRepository
import com.example.userdirectory.network.UserApiService
import kotlinx.coroutines.launch

import androidx.lifecycle.viewmodel.initializer

import com.example.userdirectory.network.UserDirectory


sealed interface UserUiState {
    data class Success(val users: /*String*/List<UserDirectory>) : UserUiState //In order to store the data, add a constructor parameter to the Success data class.
    object Error : UserUiState //Change the data class to Object to create the objects for the web responses.
    object Loading : UserUiState
}

class UserViewModel(
    private val userDirectoryRepository: UserDirectoryRepository //The value for the constructor parameter comes from the application container because the app is now using dependency injection.
) : ViewModel() {
//    var userUiState: String by mutableStateOf("")
    var userUiState: UserUiState by mutableStateOf(UserUiState.Loading)  //MarsUiState.Loading is default value.
        private set ////Make the setter private to protect writes to the userUiState.


    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getUserDirectory()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getUserDirectory() {
        viewModelScope.launch {
            userUiState = UserUiState.Loading
            userUiState = try {
                //val listResult = UserApi.retrofitService.getUserDirectory()
                //val userDirectoryRepository = NetworkUserDirectoryRepository()
                //val listResult = userDirectoryRepository.getUserDirectory()
                UserUiState.Success(userDirectoryRepository.getUserDirectory())
                //UserUiState.Success(userDirectoryRepository.getUserDirectory())
//                UserUiState.Success(
//                    "Success: ${listResult.size} Mars photos retrieved"
//                )

            } catch (e: IOException) {
                UserUiState.Error
            } catch (e: HttpException) {
                UserUiState.Error
            }
        }
    }

    //helps us by having a single instance of an object that is used by everyone without needing to create a new instance
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as UserDirectoryApplication)
                val userDirectoryRepository = application.container.userDirectoryRepository
                UserViewModel(userDirectoryRepository = userDirectoryRepository)
            }
        }
    }
}



