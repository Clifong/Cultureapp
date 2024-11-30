package com.example.student_hacks.Custom_classes.Specific

import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.Custom_classes.Exceptions.FailToAddDiaryException
import com.example.student_hacks.Custom_classes.Exceptions.FailToAddUserException
import com.example.student_hacks.Custom_classes.Exceptions.FailToUpdateProfileException
import com.example.student_hacks.Custom_classes.Exceptions.SignInFailException
import com.example.student_hacks.Custom_classes.Exceptions.SignUpFailException
import com.example.student_hacks.Custom_classes.Message_class.Message
import com.example.student_hacks.Custom_classes.Post_class.Post
import com.example.student_hacks.Custom_classes.User.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore

class FirebaseDB : Database() {

    //Authentication related
    private var auth: FirebaseAuth

    //Store data on a database
    private var db : FirebaseFirestore

    init {
        auth = Firebase.auth
        db = Firebase.firestore
    }

    override fun signIn(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            task ->
            if (!task.isSuccessful) {
                onFailure(SignInFailException())
            } else {
                setUser()
                onSuccess()
            }
        }
    }

    override fun signup(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            task ->
            if (!task.isSuccessful) {
                onFailure(SignUpFailException())
            } else {
                createUser()
                setUser()
                onSuccess()
            }
        }
    }

    override fun setUser(){
        val uid = auth.currentUser!!.uid
        db.collection("profile")
            .document(uid)
            .get()
            .addOnCompleteListener {
                    document ->
                if (document != null) {
                    val age = (document.result.get("age") as Long).toInt()
                    val username = document.result.get("username") as String
                    val country = document.result.get("country") as String
                    val aboutMe = document.result.get("aboutMe") as String
                    val friendList = document.result.get("friendList") as ArrayList<String>
                    val postList = document.result.get("postList") as ArrayList<String>
                    user = User(
                        id = uid,
                        age = age,
                        username = username,
                        friendList = friendList,
                        postList = postList,
                        country = country,
                        aboutMe = aboutMe
                    )
                    setAllFriend()
                    setAllDiary()
                }
            }
    }

    override fun updateProfle(username: String, age: Int, country: String, aboutMe: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val toUpdate = hashMapOf(
            "username" to username,
            "age" to age,
            "country" to country,
            "aboutMe" to aboutMe,
        )
        db.collection("profile")
            .document(user.id)
            .update(toUpdate as Map<String, Any>)
            .addOnCompleteListener {
                task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(FailToUpdateProfileException())
                }
            }
    }

    override fun updateDiaryContent(postId: String, title: String, content: String, time : String, likedBy: ArrayList<String>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val newPost = hashMapOf(
            "id" to auth.currentUser!!.uid,
            "title" to title,
            "content" to content,
            "time" to time,
            "likedBy" to likedBy,
        )
        if (postId.isEmpty()) {
            db.collection("post")
                .add(newPost)
                .addOnCompleteListener {
                    task ->
                    if (task.isSuccessful) {
                        setAllDiary()
                        onSuccess()
                    } else {
                        onFailure(FailToAddDiaryException())
                    }
                }
                .addOnSuccessListener {
                    documentReference ->
                        user.postList.add(documentReference.id)
                        updateUserPostList()
                }
        } else {
            db.collection("post")
                .document(postId)
                .update(newPost as Map<String, Any>)
                .addOnCompleteListener {
                        task ->
                    if (task.isSuccessful) {
                        setAllDiary()
                        onSuccess()
                    } else {
                        onFailure(FailToAddDiaryException())
                    }
                }
        }
    }

    override fun setAllDiary() {
        allDiary.clear()
        for (id in user.postList) {
            db.collection("post")
                .document(id)
                .get()
                .addOnSuccessListener {
                        document ->
                        allDiary.add(Post(
                            document.id,
                            document.data!!.get("title") as String,
                            document.data!!.get("content") as String,
                            document.data!!.get("time") as String,
                            document.data!!.get("likedBy") as ArrayList<String>
                        ))
                }
        }
    }

    fun updateUserPostList() {
        val newPostList = hashMapOf(
            "postList" to user.postList
        )
        db.collection("profile")
            .document(user.id)
            .update(newPostList as Map<String, Any>)
    }

    override fun addFriend(addUser: User, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        user.friendList.add(addUser.id)
        val newUserValue = hashMapOf(
            "friendList" to user.friendList,
        )
        db.collection("profile")
            .document(user.id)
            .update(newUserValue as Map<String, Any>)
            .addOnCompleteListener {
                task ->
                if (task.isSuccessful) {
                    setAllFriend()
                    onSuccess()
                } else {
                    onFailure(FailToAddUserException())
                }
            }
    }

    override fun setFriendDiary(friendAllDiary: ArrayList<String>) {
        friendDiary.clear()
        for (id in friendAllDiary) {
            db.collection("post")
                .document(id)
                .get()
                .addOnSuccessListener {
                        document ->
                    friendDiary.add(Post(
                        document.id,
                        document.data!!.get("title") as String,
                        document.data!!.get("content") as String,
                        document.data!!.get("time") as String,
                        document.data!!.get("likedBy") as ArrayList<String>
                    ))
                }
        }
    }

    override fun updateLikes(postId: String, likedBy: ArrayList<String>) {
        var updateHashmap = hashMapOf(
            "likedBy" to likedBy
        )
        db.collection("post")
            .document(postId)
            .update(updateHashmap as Map<String, Any>)
    }

    override fun addMessage(userId: String, content: String, time: String) {
        var collection = db.collection("message")
        var timeToMessage = mapOf<String, String>(time to content)
        var messageToAdd = hashMapOf(
            "myId" to user.id,
            "otherPartyId" to userId,
            "message" to timeToMessage
        )
        collection
            .document(user.id + userId)
            .set(messageToAdd, SetOptions.merge())
    }

    override fun setMessage(otherPartyId: String) {
        var collection = db.collection("message")
        collection
            .document(user.id + otherPartyId)
            .get()
            .addOnSuccessListener { result ->
                if (result["doc"] != null) {
                    myMessage.clear()
                    var messageMap = result.get("message") as Map<String, String>
                    for (entry in messageMap) {
                        myMessage.add(
                            Message(
                                myMessage = true,
                                text = entry.value!!,
                                time = entry.key!!
                            )
                        )
                    }
                }
            }

        collection
            .document(otherPartyId + user.id)
            .get()
            .addOnSuccessListener { result ->
                if (result["doc"] != null) {
                    otherPartyMessage.clear()
                    var messageMap = result.get("message") as Map<String, String>
                    for (entry in messageMap) {
                        otherPartyMessage.add(Message(
                            myMessage = false,
                            text = entry.value!!,
                            time = entry.key!!
                        ))
                    }
                }
            }

    }

    override fun setAllFriend() {
        var collection = db.collection("profile")
        allFriend.clear()
        nonFriend.clear()
        collection
            .get()
            .addOnSuccessListener {
                    result ->
                for (doc in result) {
                    var currentUser = User(
                        id = doc.id,
                        age = (doc.get("age") as Long).toInt(),
                        username = doc.get("username") as String,
                        country = doc.get("country") as String,
                        aboutMe = doc.get("aboutMe") as String,
                        friendList = doc.get("friendList") as ArrayList<String>,
                        postList = doc.get("postList") as ArrayList<String>,
                    )
                    if (user.friendList.contains(doc.id)) {
                        allFriend.add(currentUser)
                    } else {
                        if (doc.id != user.id) {
                            nonFriend.add(currentUser)
                        }
                    }
                }
            }
    }

    fun createUser() {
        val userValue = hashMapOf(
            "id" to auth.currentUser!!.uid,
            "username" to auth.currentUser!!.email,
            "country" to "Canada",
            "age" to 21,
            "aboutMe" to "Add more info about yourself!",
            "friendList" to listOf<String>(),
            "postList" to listOf<String>()
        )
        db.collection("profile")
            .document(auth.currentUser!!.uid)
            .set(userValue)
    }


}