package com.example.student_hacks.Custom_classes.Post_class

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: String,
    val title: String,
    val description: String
) {

}