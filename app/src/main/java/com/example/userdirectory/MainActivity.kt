package com.example.userdirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.userdirectory.ui.UserDirectoryApp
import com.example.userdirectory.ui.theme.UserDirectoryTheme

import androidx.compose.material3.Surface

//The only task for this activity is to load the ViewModel and display the UserDirectoryApp composable.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserDirectoryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    UserDirectoryApp()
                }
            }
        }
    }
}





