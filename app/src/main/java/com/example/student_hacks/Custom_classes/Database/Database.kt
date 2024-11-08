package com.example.student_hacks.Custom_classes.Database

import com.google.firebase.database.core.Context

abstract class Database {

    companion object Db {
        lateinit var database : Database

        fun initDatabase(database: Database) {
            this.database = database
        }

        fun getCredential(id: Int) {
            database.getCredentialDb(id)
        }

        fun getFriendList(id: Int) {
            database.getFriendListDb(id)
        }
        
        fun signUp(email: String, password: String) {
            database.signupDb(email, password)
        }
        
        fun signIn(email: String, password: String) {
            database.signInDb(email, password)
        }
    }

    abstract fun signInDb(email: String, password: String)

    abstract fun signupDb(email: String, password: String)

    abstract fun getFriendListDb(id: Int)

    abstract fun getCredentialDb(id: Int)
    

}