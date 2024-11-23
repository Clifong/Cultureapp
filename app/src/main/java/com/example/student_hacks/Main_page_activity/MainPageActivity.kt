package com.example.student_hacks.Main_page_activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_hacks.Cultural_diary_activity.All_my_diary
import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.Custom_classes.User.User
import com.example.student_hacks.Language_exchange_activity.LanguageExchangeActivity
import com.example.student_hacks.R
import com.example.student_hacks.Chat_activity.ChatActivity
import com.example.student_hacks.friendAdapter.FriendAdapter
import com.example.student_hacks.friend_profile_activity.FriendProfileActivity
import com.example.student_hacks.profile_activity.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainPageActivity : AppCompatActivity() {

    private lateinit var bottomMenu : BottomNavigationView
    private lateinit var friendListRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        bottomMenu = findViewById(R.id.main_page_bottom_menu_bar)

        friendListRecyclerView = findViewById(R.id.friendListRecycleView)
        val friendAdapter = FriendAdapter(this, Database.getAllMyFriend())

        friendListRecyclerView.adapter = friendAdapter

        friendAdapter.setOnClickListener(object:
            FriendAdapter.OnClickListener {
            override fun onClick(position: Int, user: User) {
                var intent = Intent(this@MainPageActivity, FriendProfileActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }
            }
        )

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        friendListRecyclerView.layoutManager = linearLayoutManager

        bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    var intent = Intent(this, MainPageActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.cultural_diary -> {
                    var intent = Intent(this, All_my_diary::class.java)
                    startActivity(intent)
                    true
                }
                R.id.language_exchange -> {
                    var intent = Intent(this, LanguageExchangeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.profile -> {
                    var intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.chat -> {
                    var intent = Intent(this, ChatActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }
}