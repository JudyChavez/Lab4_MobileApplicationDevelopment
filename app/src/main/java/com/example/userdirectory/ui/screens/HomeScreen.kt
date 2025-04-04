package com.example.userdirectory.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.userdirectory.R
import com.example.userdirectory.network.Name
import com.example.userdirectory.network.Picture

import com.example.userdirectory.network.UserDirectory
import com.example.userdirectory.network.UserDirectoryResponse
import com.example.userdirectory.ui.UserTopAppBar
import com.example.userdirectory.ui.theme.UserDirectoryTheme



@Composable
fun HomeScreen(
    userUiState: UserUiState,
    retryAction: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    when (userUiState) {
        is UserUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is UserUiState.Success ->
            //ResultScreen(userUiState.users, modifier = modifier.fillMaxWidth())
            //PhotosGridScreen(userUiState.users/*users*/, modifier)
            UserListScreen(userUiState.users/*users*/, modifier)
        is UserUiState.Error -> ErrorScreen(retryAction, modifier.fillMaxSize())
    }
}

//composable function to display the loading animation
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

//composable function to display the error message
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}


/**
 * UserCard() – Displays an individual user’s profile picture name and email.
 * */
@Composable
fun UserCard(user: UserDirectory, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .background(color = Color.Blue), //color on card edges
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.padding_small))
    ) {
        Row(
            modifier = Modifier //new instance of Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            Column(
                modifier = modifier
                    .background(color = Color.Magenta) //color around picture
            ) {
                AsyncImage( //Use Coil for image loading.
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(user.picture.pictureUrl/*imgSrc*/)
                        .crossfade(true) //enables crossfade animation when the request completes successfully.
                        .build(),
                    error = painterResource(R.drawable.ic_broken_image), //sets image if loading fails.
                    placeholder = painterResource(R.drawable.loading_img), //sets image while loading.
                    contentDescription = stringResource(R.string.user_photo),
                    contentScale = ContentScale.Crop, //fill available space on screen (both horizontally and vertically).
                    modifier = modifier//modifier = Modifier.fillMaxWidth()
                )
            }
            Column(modifier = modifier) {
                Row(
                ) {
                    Text(
                        text = "${user.name.firstName} ${user.name.lastName}",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
                    )
                }
                Row() {
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
/**
 * UserListScreen() – Displays the list of user profiles.
 * */
@Composable
fun UserListScreen(
    users: List<UserDirectory>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(contentPadding = contentPadding) { //The contentWindowInsets value is passed to the LazyColumn as the contentPadding.
        items(users) { user ->
            UserCard(
                user = user,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
                    .background(color = Color.LightGray)
            )
        }
    }
}



/**
 * ResultScreen displaying number of photos retrieved.
 */
//ResultScreen has a simple Box layout that displays the value of userUiState in a Text composable.
@Composable
fun ResultScreen(
    users: /*String*/List<UserDirectory>,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = users.toString() //Text(text = users)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    UserDirectoryTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    UserDirectoryTheme {
        ErrorScreen( {} )
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    UserDirectoryTheme {
        val mockData = List(10) {
            UserDirectory(
                uuid = "$it",
                name = Name("$it", "$it"),
                email = "",
                picture = Picture("$it")
            )
        }
        UserListScreen(mockData, modifier = Modifier)
    }
}