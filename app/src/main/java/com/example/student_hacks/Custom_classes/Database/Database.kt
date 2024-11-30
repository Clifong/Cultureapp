package com.example.student_hacks.Custom_classes.Database
import com.example.student_hacks.Custom_classes.Message_class.Message
import com.example.student_hacks.Custom_classes.Post_class.Post
import com.example.student_hacks.Custom_classes.User.User
import java.sql.Time
import java.util.Calendar

abstract class Database {

    companion object Db {
        lateinit var database : Database
        lateinit var user : User
        var allDiary : ArrayList<Post> = ArrayList()
        var allFriend : ArrayList<User> = ArrayList()
        var nonFriend : ArrayList<User> = ArrayList()
        var friendDiary : ArrayList<Post> = ArrayList()
        var myMessage : ArrayList<Message> = ArrayList()
        var otherPartyMessage : ArrayList<Message> = ArrayList()

        fun initDatabase(database: Database) {
            this.database = database
        }

        fun updateDiaryContent(postId: String, title: String, content: String, time: String, likedBy: ArrayList<String>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
            database.updateDiaryContent(
                postId,
                title,
                content,
                time,
                likedBy,
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

        fun setAllFriendDiary(friendALlDiaryId: ArrayList<String>) {
            database.setFriendDiary(friendALlDiaryId)
        }

        fun getAllFriendDiary() : ArrayList<Post> {
            return friendDiary
        }

        fun getAllMyFriend() : ArrayList<User> {
            return allFriend
        }

        fun getAllNonFriends() : ArrayList<User> {
            allFriend.clear()
            database.setAllFriend()
            return nonFriend
        }

        fun addFried(user: User, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
            database.addFriend(user, onSuccess, onFailure)
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

        fun updateProfile(username: String, age : Int, country: String, aboutMe: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
            database.updateProfle(
                username,
                age,
                country,
                aboutMe,
                onSuccess = {
                    onSuccess()
                },
                onFailure = {
                    e -> onFailure(e)
                }
            )
        }

        fun updateLikes(postId: String, likedBy: ArrayList<String>) {
            database.updateLikes(postId, likedBy)
        }

        fun getAllMessage(otherPartyId: String) : ArrayList<Message> {
            database.setMessage(otherPartyId)
            myMessage.addAll(otherPartyMessage)
            myMessage.sortBy {
                msg -> msg.time
            }
            return myMessage
        }

        fun addMessage(userId: String, content: String) {
            database.addMessage(userId, content, Calendar.getInstance().time.toString())
            getAllMessage(userId)
        }
    }

    abstract fun signIn(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)

    abstract fun signup(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)

    abstract fun setUser()

    abstract fun addFriend(user: User, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)

    abstract fun updateProfle(username: String, age : Int, country: String, aboutMe: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)

    abstract fun updateDiaryContent(postId: String, title: String, content: String, time: String, likedBy: ArrayList<String>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)

    abstract fun setAllDiary()

    abstract fun setFriendDiary(friendAllDiary: ArrayList<String>)

    abstract fun setAllFriend()

    abstract fun updateLikes(postId: String, likedBy: ArrayList<String>)

    abstract fun setMessage(otherPartyId: String)

    abstract fun addMessage(userId: String, content: String, time: String)
    

}