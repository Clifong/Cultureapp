package com.example.student_hacks.profile_activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.student_hacks.R
import java.util.Locale

class ProfileActivity : AppCompatActivity() {

    private lateinit var ageSpinner : Spinner
    private lateinit var countrySpinner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

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
        countrySpinner = findViewById(R.id.countrySpinner)
        ageSpinner.adapter = ArrayAdapter(this, R.layout.spinner_text_view, allAge)
        countrySpinner.adapter = ArrayAdapter(this, R.layout.spinner_text_view, allCountries)
    }
}