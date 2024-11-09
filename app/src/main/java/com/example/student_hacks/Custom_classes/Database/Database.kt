package com.example.student_hacks.Custom_classes.Database
import com.example.student_hacks.Custom_classes.Post_class.Post
import com.example.student_hacks.Custom_classes.User.User

abstract class Database {

    companion object Db {
        lateinit var database : Database
        lateinit var user : User
        var allDiary : ArrayList<Post> = ArrayList()

        fun initDatabase(database: Database) {
            this.database = database
        }

        fun updateDiaryContent(postId: String, title: String, content: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
            database.updateDiaryContent(
                postId,
                title,
                content,
                onSuccess = {
                    onSuccess()
                },
                onFailure = {
                    e -> onFailure(e)
                }
            )
        }

        fun getAllMyDiary() : ArrayList<Post> {
            return allDiary
        }

        fun signUp(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
            database.signup(email, password,
                onSuccess = {
                    onSuccess()
                },
                onFailure = {
                e -> onFailure(e)
            })
        }

        fun signIn(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
                database.signIn(email, password,
                    onSuccess = {
                        onSuccess()
                    },
                    onFailure = {
                    e -> onFailure(e)
                })
        }

        fun updateProfile(username: String, age : Int, country: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
            database.updateProfle(
                username,
                age,
                country,
                onSuccess = {
                    onSuccess()
                },
                onFailure = {
                    e -> onFailure(e)
                }
            )
        }
    }

    abstract fun signIn(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)

    abstract fun signup(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)

    abstract fun setUser()

    abstract fun updateProfle(username: String, age : Int, country: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)

    abstract fun updateDiaryContent(postId: String, title: String, content: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)

    abstract fun setAllDiary()

    

}