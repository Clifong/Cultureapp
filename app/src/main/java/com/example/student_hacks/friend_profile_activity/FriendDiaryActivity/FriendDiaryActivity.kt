package com.example.student_hacks.friend_profile_activity.FriendDiaryActivity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_hacks.Cultural_diary_activity.CulturalDiaryActivity
import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.Custom_classes.Post_class.Post
import com.example.student_hacks.Custom_classes.User.User
import com.example.student_hacks.R
import com.example.student_hacks.friend_profile_activity.FriendDiaryOverview.FriendDiaryOverviewActivity
import com.example.student_hacks.postAdapter.friendPostAdapter.FriendPostAdapter
import layout.PostAdapter

class FriendDiaryActivity : AppCompatActivity() {

    private lateinit var friendDiaryRecyclerView: RecyclerView

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            var data = Intent()
            data.putExtra("user", intent.getSerializableExtra("user") as User)
            setResult(RESULT_OK, data)
            finish()
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_diary)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        val allDiary : ArrayList<Post> = Database.getAllFriendDiary()

        friendDiaryRecyclerView = findViewById(R.id.friendListRecycleView)
        val friendPostAdapter = FriendPostAdapter(this, allDiary)

        friendPostAdapter.setOnClickListener(object :
            FriendPostAdapter.OnClickListener {
            override fun onClick(position: Int, post: Post) {
                var intent = Intent(this@FriendDiaryActivity, FriendDiaryOverviewActivity::class.java)
                val extras = Bundle()
                extras.putString("postId", post.id)
                extras.putString("title", post.title)
                extras.putString("description", post.description)
                extras.putStringArrayList("likedBy", post.likedBy)
                intent.putExtras(extras)
                startActivity(intent)
            }
        }
        )
        friendDiaryRecyclerView.adapter = friendPostAdapter

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        friendDiaryRecyclerView.layoutManager = linearLayoutManager


    }
}