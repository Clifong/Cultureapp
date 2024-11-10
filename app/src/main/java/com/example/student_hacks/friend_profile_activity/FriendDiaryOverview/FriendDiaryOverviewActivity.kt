package com.example.student_hacks.friend_profile_activity.FriendDiaryOverview

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.R

class FriendDiaryOverviewActivity : AppCompatActivity() {

    private lateinit var friendDiaryTitleTextView: TextView
    private lateinit var friendDiaryContentTextView: TextView
    private lateinit var friendLikeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_diary_overview)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        friendDiaryTitleTextView = findViewById(R.id.friendDiaryTitleTextView)
        friendDiaryContentTextView = findViewById(R.id.friendDiaryContentTextView)
        friendDiaryContentTextView.movementMethod = ScrollingMovementMethod()

        var bundle = intent.extras!!
        friendDiaryTitleTextView.setText(bundle.getString("title"))
        friendDiaryContentTextView.setText(bundle.getString("description"))

        var likedBy = bundle.getStringArrayList("likedBy") as ArrayList<String>
        renderLikeButton(likedBy)

        friendLikeButton.setOnClickListener({
            if (likedBy.contains(Database.user.id)) {
                likedBy.remove(Database.user.id)
            } else {
                likedBy.add(Database.user.id)
            }
            renderLikeButton(likedBy)
            Database.updateLikes(bundle.getString("postId") as String, likedBy)
        })
    }

    fun renderLikeButton(likedBy: ArrayList<String>) {
        if (likedBy.contains(Database.user.id)) {
            friendLikeButton.setText("♡")
        } else {
            friendLikeButton.setText("♥")
        }
    }
}