package com.example.student_hacks.Custom_classes.User

class User : java.io.Serializable {

    var id: String = ""
    var age: Int = 21
    var username: String = ""
    var aboutMe : String = ""
    var country : String = ""
    var friendList: ArrayList<String> = ArrayList<String>()
    var postList: ArrayList<String> = ArrayList<String>()

    constructor(id: String,
                age: Int,
                username: String,
                aboutMe: String,
                country: String,
                friendList: ArrayList<String>,
                postList: ArrayList<String>) {
        this.id = id
        this.age = age
        this.username = username
        this.aboutMe = aboutMe
        this.country = country
        this.friendList = friendList
        this.postList = postList
    }

}

