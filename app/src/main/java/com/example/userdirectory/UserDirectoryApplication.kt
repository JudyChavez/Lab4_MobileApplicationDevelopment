package com.example.userdirectory


import android.app.Application
import com.example.userdirectory.data.AppContainer
import com.example.userdirectory.data.DefaultAppContainer




//This class inherits from the application object, so you need to add it to the class declaration.
class UserDirectoryApplication : Application() {
    lateinit var container: AppContainer //stores DefaultAppContainer object. variable initialized during onCreate() call.
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
