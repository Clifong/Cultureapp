package com.example.student_hacks.Chat_activity.Chat_page_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.Custom_classes.User.User
import com.example.student_hacks.Chat_activity.MessageAdapter.MessageAdapter
import com.example.student_hacks.R
import com.example.student_hacks.friendAdapter.FriendAdapter

class ChatPageActivity : AppCompatActivity() {

    private lateinit var messageRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat_page)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        messageRecyclerView = findViewById(R.id.allMessageRecycleView)
        val messageAdapter = MessageAdapter(this, Database.getAllMessage((intent.getSerializableExtra("user") as User).id))

        messageRecyclerView.adapter = messageAdapter

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        messageRecyclerView.layoutManager = linearLayoutManager
    }
}