package com.example.student_hacks.Custom_classes.Database

import com.example.student_hacks.Custom_classes.Exceptions.DatabaseException

abstract class Database {

    companion object Db {
        lateinit var database : Database

        fun initDatabase(database: Database) {
            this.database = database
        }

        fun getCredential(id: Int) {
            try {
                database.getCredentialDb(id)
            } catch (e: Exception) {
                throw e
            }
        }

        fun getFriendList(id: Int) {
            try {
                database.getFriendListDb(id)
            } catch (e: Exception) {
                throw e
            }
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
                        onSuccess()
                    },
                    onFailure = {
                    e -> onFailure(e)
                })
        }
    }

    abstract fun signInDb(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)

    abstract fun signupDb(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)

    abstract fun getFriendListDb(id: Int)

    abstract fun getCredentialDb(id: Int)
    

}