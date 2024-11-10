package com.example.student_hacks.Custom_classes.User

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val age: Int,
    val username: String,
    val aboutMe : String,
    val country : String,
    val friendList: ArrayList<String>,
    val postList: ArrayList<String>,
) {

}

