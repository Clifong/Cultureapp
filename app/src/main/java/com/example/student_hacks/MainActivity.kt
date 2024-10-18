package com.example.student_hacks

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.student_hacks.Login_activity.EmailLoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var emailLoginButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailLoginButton = findViewById(R.id.login_via_email_button)
        emailLoginButton.setOnClickListener {
            val intent = Intent(this, EmailLoginActivity::class.java)
            startActivity(intent)
        }
    }
}