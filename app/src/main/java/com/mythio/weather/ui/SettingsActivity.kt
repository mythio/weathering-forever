package com.mythio.weather.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.mythio.weather.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)

            val unitPreference: ListPreference? = findPreference("app_unit")
            val themePreference: ListPreference? = findPreference("app_theme")
            val supportPreference: Preference? = findPreference("app_support")
            val licencePreference: Preference? = findPreference("app_licence")
            val versionPreference: Preference? = findPreference("app_version")

            unitPreference?.setOnPreferenceChangeListener { preference, newValue ->
                Log.d("TAG_TAG_TAG", newValue.toString())
                true
            }
        }
    }
}