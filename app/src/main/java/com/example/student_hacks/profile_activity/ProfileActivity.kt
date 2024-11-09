package com.example.student_hacks.profile_activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.R
import java.util.Locale

class ProfileActivity : AppCompatActivity() {

    private lateinit var ageSpinner : Spinner
    private lateinit var countrySpinner : Spinner
    private lateinit var usernameEditText : EditText
    private lateinit var saveProfileButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        usernameEditText = findViewById(R.id.profileUsernameEditText)
        usernameEditText.setText(Database.user.username)

        var locales = Locale.getAvailableLocales()
        var allCountries = ArrayList<String>()
        for (locale in locales) {
            allCountries.add(locale.displayCountry)
        }

        var allAge = ArrayList<Int>()
        for (i in 18..50) {
            allAge.add(i)
        }

        ageSpinner = findViewById(R.id.ageSpinner)
        ageSpinner.setSelection(ageSpinner.adapter.getItem(Database.user.age) as Int)

        countrySpinner = findViewById(R.id.countrySpinner)
//        countrySpinner.setSelection(countrySpinner.getSe)

        ageSpinner.adapter = ArrayAdapter(this, R.layout.spinner_text_view, allAge)
        countrySpinner.adapter = ArrayAdapter(this, R.layout.spinner_text_view, allCountries)

        saveProfileButton = findViewById(R.id.saveProfileButton)
        saveProfileButton.setOnClickListener({
            Database.updateProfile(
                username = usernameEditText.text.toString(),
                age = ageSpinner.selectedItem as Int,
                country = countrySpinner.selectedItem as String
            )
        })
    }
}