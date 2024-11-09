package com.example.student_hacks.Custom_classes.Database
import com.example.student_hacks.Custom_classes.User.User

abstract class Database {

    companion object Db {
        lateinit var database : Database
        lateinit var user : User

        fun initDatabase(database: Database) {
            this.database = database
        }

        fun signUp(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
            database.signupDb(email, password,
                onSuccess = {
                    onSuccess()
                },
                onFailure = {
                e -> onFailure(e)
            })
        }

        fun signIn(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
                database.signInDb(email, password,
                    onSuccess = {
                        database.setUser()
                        onSuccess()
                    },
                    onFailure = {
                    e -> onFailure(e)
                })
        }

        fun updateProfile(username: String, age : Int, country: String) {
            database.updateProfle(username, age, country)
        }
    }

    abstract fun signInDb(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)

    abstract fun signupDb(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)

    abstract fun setUser()

    abstract fun updateProfle(username: String, age : Int, country: String)

    

}