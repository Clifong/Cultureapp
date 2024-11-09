package com.example.student_hacks.Custom_classes.Specific

import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.Custom_classes.Exceptions.FailToAddDiaryException
import com.example.student_hacks.Custom_classes.Exceptions.FailToUpdateProfileException
import com.example.student_hacks.Custom_classes.Exceptions.SignInFailException
import com.example.student_hacks.Custom_classes.Exceptions.SignUpFailException
import com.example.student_hacks.Custom_classes.Post_class.Post
import com.example.student_hacks.Custom_classes.User.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
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
                    val friendList = document.result.get("friendList") as ArrayList<String>
                    val postList = document.result.get("postList") as ArrayList<String>
                    user = User(
                        id = uid,
                        age = age,
                        username = username,
                        friendList = friendList,
                        postList = postList,
                        country = country
                    )
                    setAllDiary()
                }
            }
    }

    override fun updateProfle(username: String, age: Int, country: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val toUpdate = hashMapOf(
            "username" to username,
            "age" to age,
            "country" to country
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

    override fun updateDiaryContent(postId: String, title: String, content: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val newPost = hashMapOf(
            "id" to auth.currentUser!!.uid,
            "title" to title,
            "content" to content
        )
        if (postId.isEmpty()) {
            db.collection("post")
                .add(newPost)
                .addOnCompleteListener {
                    task ->
                    if (task.isSuccessful) {
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
                            document.data!!.get("content") as String
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

    fun createUser() {
        val userValue = hashMapOf(
            "id" to auth.currentUser!!.uid,
            "username" to auth.currentUser!!.email,
            "country" to "Canada",
            "age" to 21,
            "friendList" to listOf<String>(),
            "postList" to listOf<String>()
        )
        db.collection("profile")
            .document(auth.currentUser!!.uid)
            .set(userValue)
    }


}