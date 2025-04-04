package com.example.userdirectory.network

import android.icu.text.IDNA
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
//@Serializable
data class UserDirectory(
    @SerializedName("id")
    val id: String,
//    @SerializedName(value = "img_src")
//    val imgSrc: String
)
*/


data class UserDirectoryResponse(
    @SerializedName("results")
    val results: List<UserDirectory> // This will hold the list of users returned from the API
)

data class UserDirectory(
    @SerializedName("uuid")
    val uuid: String, // From login.uuid

    @SerializedName("name")
    val name: Name, // Nested object for name

    @SerializedName("email")
    val email: String,

    @SerializedName("picture")
    val picture: Picture // Nested object for picture
)

// Nested class for name
data class Name(
    @SerializedName("first")
    val firstName: String, // From name.first
    @SerializedName("last")
    val lastName: String // From name.last
)

// Nested class for picture
data class Picture(
    @SerializedName("large")
    val pictureUrl: String // From picture.large
)




//data class UserDirectory(
//    @SerializedName("results")
//    val results: List<User>
//)
//
//data class User(
//    @SerializedName("gender")
//    val gender: String,
//    @SerializedName("name")
//    val name: Name
//)
//
//data class Name(
//    @SerializedName("title")
//    val title: String,
//    @SerializedName("first")
//    val first: String,
//    @SerializedName("last")
//    val last: String,
//)



/*
@Serializable
data class UserDirectory(
    @SerialName("results")
    val results: List<UserItem>
)
@Serializable
data class UserItem(
    @SerialName(value = "login.uuid")
    val uuid: String,
    @SerialName(value = "name.first")
    val firstName: String,
    @SerialName(value = "name.last")
    val lastName: String,
    @SerialName(value = "picture.large")
    val picture: String
)
*/

/*
@Serializable
data class UserDirectory(
    @SerialName("results")
    val results: List<User>
)
@Serializable
data class User(
    @SerialName(value = "login.uuid")
    val uuid: Login,
    @SerialName(value = "name.first")
    val firstName: Name,
    @SerialName(value = "name.last")
    val lastName: Name,
    @SerialName(value = "picture.large")
    val picture: Picture
)
*/

/*
@Serializable
data class Login(
    @SerialName("uuid")
    val uuid: String  // Accessing the uuid inside the "login" object
)

@Serializable
data class Name(
    @SerialName("first")
    val firstName: String,  // Accessing the first name inside the "name" object

    @SerialName("last")
    val lastName: String  // Accessing the last name inside the "name" object
)

@Serializable
data class Picture(
    @SerialName("large")
    val large: String  // Accessing the large picture URL inside the "picture" object
)

@Serializable
data class Results(
    @SerialName("results.")
    val results: String
)
*/
