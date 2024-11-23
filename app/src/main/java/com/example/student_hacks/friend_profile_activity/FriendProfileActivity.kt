package com.example.student_hacks.friend_profile_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.Custom_classes.User.User
import com.example.student_hacks.R
import com.example.student_hacks.friend_profile_activity.FriendDiaryActivity.FriendDiaryActivity

class FriendProfileActivity : AppCompatActivity() {

    private lateinit var friendUsernameTextView: TextView
    private lateinit var friendCountryTextView: TextView
    private lateinit var friendAgeTextView: TextView
    private lateinit var friendAboutMeTextView: TextView
    private lateinit var friendDiarySeeButton: Button
    private lateinit var user: User

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println(requestCode)
        println(resultCode)
        if (resultCode == RESULT_OK && data != null) {
            user = data.getSerializableExtra("user") as User
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_profile)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        friendUsernameTextView = findViewById(R.id.friendUsernameTextView)
        friendAgeTextView = findViewById(R.id.friendAgeTextView)
        friendCountryTextView = findViewById(R.id.friendCountryTextView)
        friendAboutMeTextView = findViewById(R.id.friendAboutMeTextView)
        friendDiarySeeButton = findViewById(R.id.friendDiaryButton)

        if (intent.hasExtra("user")) {
            user = intent.getSerializableExtra("user") as User
        }

        Database.setAllFriendDiary(user.postList)
        friendUsernameTextView.setText(user.username)
        friendAgeTextView.setText(user.age.toString())
        friendCountryTextView.setText(user.country)
        friendAboutMeTextView.setText(user.country)
        friendDiarySeeButton.setOnClickListener({
            var intent = Intent(this, FriendDiaryActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        })
    }
}