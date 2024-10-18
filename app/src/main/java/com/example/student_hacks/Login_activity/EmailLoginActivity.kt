package com.example.student_hacks.Login_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.student_hacks.MainActivity
import com.example.student_hacks.Main_page_activity.MainPageActivity
import com.example.student_hacks.R

class EmailLoginActivity : AppCompatActivity() {

    private lateinit var loginButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)

        loginButton = findViewById(R.id.logn_button)

        loginButton.setOnClickListener() {
            var intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
        }
    }
}