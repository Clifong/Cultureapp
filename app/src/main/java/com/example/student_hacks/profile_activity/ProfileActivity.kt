package com.example.student_hacks.profile_activity

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.R
import java.util.Locale


class ProfileActivity : AppCompatActivity() {

    private lateinit var ageSpinner : Spinner
    private lateinit var countrySpinner : Spinner
    private lateinit var usernameEditText : EditText
    private lateinit var aboutMeEditText : EditText
    private lateinit var saveProfileButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        val sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", Database.user.username)
        val savedAge = sharedPreferences.getInt("age", Database.user.age)
        val savedCountry = sharedPreferences.getString("country", Database.user.country)


        usernameEditText = findViewById(R.id.profileUsernameEditText)
        usernameEditText.setText(savedUsername)

        ageSpinner = findViewById(R.id.ageSpinner)
        countrySpinner = findViewById(R.id.countrySpinner)


        val allCountries = Locale.getAvailableLocales().map { it.displayCountry }.distinct()
        val allAges = (18..50).toList()


        ageSpinner.adapter = ArrayAdapter(this, R.layout.spinner_text_view, allAges)
        countrySpinner.adapter = ArrayAdapter(this, R.layout.spinner_text_view, allCountries)

        ageSpinner.setSelection(allAges.indexOf(savedAge))
        countrySpinner.setSelection(allCountries.indexOf(savedCountry))

        saveProfileButton = findViewById(R.id.saveProfileButton)
        saveProfileButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val age = ageSpinner.selectedItem as Int
            val country = countrySpinner.selectedItem as String

            Database.updateProfile(username, age, country, onSuccess = {

                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.putInt("age", age)
                editor.putString("country", country)
                editor.apply()

                Toast.makeText(this, "Successfully updated profile!", Toast.LENGTH_SHORT).show()
            }, onFailure = { e ->
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            })
        }
    }
}
