package com.example.student_hacks.Login_activity

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.student_hacks.MainActivity
import com.example.student_hacks.Main_page_activity.MainPageActivity
import com.example.student_hacks.R
import com.google.android.material.snackbar.Snackbar

class EmailLoginActivity : AppCompatActivity() {

    private lateinit var loginButton : Button
    private lateinit var registerHereText : TextView
    private lateinit var emailLoginEditText: EditText
    private lateinit var passwordLoginEditText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)

        loginButton = findViewById(R.id.logn_button)
        emailLoginEditText = findViewById(R.id.emailLoginEditText)
        passwordLoginEditText = findViewById(R.id.passwordLoginEdittext)

        var spanText = SpannableString("Register here!")
        spanText.setSpan(UnderlineSpan(), 0, spanText.length, 0)
        registerHereText = findViewById(R.id.registerHereText)
        registerHereText.text = spanText

        registerHereText.setOnClickListener() {
            var intent = Intent(this, EmailRegisterActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener() {
            if (emailLoginEditText.text.length == 0 || passwordLoginEditText.text.length == 0) {
                //var snackbarPopup = Snackbar.make(this, "Empty email or psasword", Snackbar.LENGTH_SHORT)

            }
            var intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
        }
    }
}