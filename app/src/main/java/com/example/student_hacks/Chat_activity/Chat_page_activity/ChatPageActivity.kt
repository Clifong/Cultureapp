package com.example.student_hacks.Chat_activity.Chat_page_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_hacks.Chat_activity.MessageAdapter.MessageAdapter
import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.Custom_classes.User.User
import com.example.student_hacks.R

class ChatPageActivity : AppCompatActivity() {

    private lateinit var messageRecyclerView: RecyclerView
    //i added the following late innits
    private lateinit var typeMessageEditText: EditText
    private lateinit var sendMessageButton: Button
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat_page)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //added this line
        var otherUser : User = intent.getSerializableExtra("user") as User

        messageRecyclerView = findViewById(R.id.allMessageRecycleView)
        typeMessageEditText = findViewById(R.id.typeMessageEditText)
        sendMessageButton = findViewById(R.id.sendMessageButton)


        messageAdapter = MessageAdapter(this, Database.getAllMessage(otherUser.id))
        messageRecyclerView.adapter = messageAdapter
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        messageRecyclerView.layoutManager = linearLayoutManager

        sendMessageButton.setOnClickListener {
            val messageContent = typeMessageEditText.text.toString().trim()

            if (messageContent.isNotEmpty()) {
                // Add the new message to the database (mock example)
                Database.addMessage(otherUser.id, messageContent)
                messageAdapter.updateMessage(Database.getAllMessage(otherUser.id))

                // Scroll to the bottom
                messageRecyclerView.scrollToPosition(messageAdapter.itemCount - 1)

                // Clear the EditText
                typeMessageEditText.text.clear()
            }
        }
    }
}