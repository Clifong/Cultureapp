package com.example.student_hacks.Custom_classes.Database

abstract class Database {

    companion object Db {
        lateinit var database : Database

        fun setDatabase(database: Database) {
            this.database = database
        }

        fun getCredential(id: Int) {
            database.getCredential(id)
        }

        fun getFriendList(id: Int) {
            database.getFriendList(id)
        }
        
        fun signUp(email: String, password: String) {
            database.signup(email, password)
        }
        
        fun signIn(email: String, password: String) {
            database.signIn(email, password)
        }
    }

    abstract fun signIn(email: String, password: String)

    abstract fun signup(email: String, password: String)

    abstract fun getFriendList(id: Int)

    abstract fun getCredential(id: Int)
    

}