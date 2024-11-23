package com.example.student_hacks.Chat_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.Custom_classes.User.User
import com.example.student_hacks.R
import com.example.student_hacks.Chat_activity.Chat_page_activity.ChatPageActivity
import com.example.student_hacks.friendAdapter.FriendAdapter

class ChatActivity : AppCompatActivity() {

    private lateinit var friendListRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        friendListRecyclerView = findViewById(R.id.ChatFriendRecycleView)
        val friendAdapter = FriendAdapter(this, Database.getAllMyFriend())

        friendListRecyclerView.adapter = friendAdapter

        friendAdapter.setOnClickListener(object:
            FriendAdapter.OnClickListener {
            override fun onClick(position: Int, user: User) {
                var intent = Intent(this@ChatActivity, ChatPageActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
                }
            }
        )

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        friendListRecyclerView.layoutManager = linearLayoutManager
    }
}