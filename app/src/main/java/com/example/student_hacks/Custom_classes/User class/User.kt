package com.example.student_hacks.Custom_classes.User

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val username: String,
) {

}

