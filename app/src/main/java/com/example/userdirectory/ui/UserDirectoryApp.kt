package com.example.userdirectory.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.userdirectory.R
import com.example.userdirectory.ui.screens.HomeScreen
import com.example.userdirectory.ui.screens.UserViewModel


import androidx.lifecycle.viewmodel.compose.viewModel

//displays the contents on the screen, such as the top app bar and the HomeScreen composable.
//this composable displays the data received from the Users backend server.
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
/**UserDirectoryApp() – The entry point of your app.*/
@Composable
fun UserDirectoryApp() {
    //val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { UserTopAppBar() }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize() //surface to take entire screen.
                .padding(innerPadding)
        ) {
            val userViewModel: UserViewModel =
                viewModel(factory = UserViewModel.Factory)
            HomeScreen(
                userUiState = userViewModel.userUiState,
                retryAction = userViewModel::getUserDirectory,
                contentPadding = innerPadding
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}