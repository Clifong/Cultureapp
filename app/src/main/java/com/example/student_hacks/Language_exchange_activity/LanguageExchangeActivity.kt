package com.example.student_hacks.Language_exchange_activity

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.text.SpannableString
import android.text.method.ScrollingMovementMethod
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.Custom_classes.User.User
import com.example.student_hacks.R

class LanguageExchangeActivity : AppCompatActivity() {

    private lateinit var profileDescriptionTextView : TextView
    private lateinit var profileUsernameTextView : TextView
    private lateinit var refreshProfileButton: Button
    private lateinit var aboutMeTextView : TextView
    private lateinit var allNonFriend: ArrayList<User>
    private lateinit var addFriendButton: Button

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_exchange)

        allNonFriend = Database.getAllNonFriends()

        profileDescriptionTextView = findViewById(R.id.profileDescriptionTextView)
        profileDescriptionTextView.movementMethod = ScrollingMovementMethod()

        profileUsernameTextView = findViewById(R.id.languageExchangeUsernameTextField)
        refreshProfileButton = findViewById(R.id.languageExchangeRefreshProfileButton)
        aboutMeTextView = findViewById(R.id.AboutMeTextvView)
        val underlinedText = SpannableString("About me")
        underlinedText.setSpan(UnderlineSpan(), 0, underlinedText.length, 0)
        aboutMeTextView.text = underlinedText

        addFriendButton = findViewById(R.id.addFriendButtonLanguageExchange)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        refreshProfile()
        refreshProfileButton.setOnClickListener({
            refreshProfile()
        })

        addFriendButton.setOnClickListener({
            Database.addFried(
                allNonFriend[counter],
                onSuccess = {
                    Toast.makeText(this, "Added user successfully!", Toast.LENGTH_SHORT).show()
                    refreshProfile()
                },
                onFailure = {
                    e -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            )
        })
    }

    fun refreshProfile() {
        if (counter < allNonFriend.size) {
            addFriendButton.visibility = View.VISIBLE
            profileDescriptionTextView.setText(allNonFriend[counter].aboutMe)
            profileUsernameTextView.setText(allNonFriend[counter].username)
            counter += 1;
            counter %= allNonFriend.size
        } else {
            addFriendButton.visibility = View.INVISIBLE
            profileUsernameTextView.setText("")
            profileDescriptionTextView.setText("REFRESH FOR MORE USER")
        }
    }
}