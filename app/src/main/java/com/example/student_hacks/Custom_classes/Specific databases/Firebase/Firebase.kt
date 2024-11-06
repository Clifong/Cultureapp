package com.example.student_hacks.Custom_classes.Specific

import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.Custom_classes.Exceptions.SignInFailException
import com.example.student_hacks.Custom_classes.Exceptions.SignUpFailException
import com.example.student_hacks.Custom_classes.User.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class FirebaseDB : Database() {

    //Authentication related
    private var auth: FirebaseAuth

    //Store data on a database
    private var db : FirebaseFirestore

    private lateinit var user : User

    init {
        auth = Firebase.auth
        db = Firebase.firestore
        if (auth.currentUser != null) {
            user = User(
                id = auth.currentUser!!.uid,
                username = auth.currentUser!!.email!!
            )
        }
    }

    override fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            task ->
            if (!task.isSuccessful) {
                throw SignInFailException()
            }
        }
    }

    override fun signup(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            task ->
            if (!task.isSuccessful) {
                throw SignUpFailException()
            }
        }
    }

    override fun getFriendList(id: Int) {
        db.collection("profile")
            .document(id.toString())
            .get()
            .addOnCompleteListener {
                document ->
                if (document != null) {
                    //Get all friends data
                }
            }
    }

    override fun getCredential(id: Int) {

    }


}