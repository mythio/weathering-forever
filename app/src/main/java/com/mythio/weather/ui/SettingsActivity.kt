package com.mythio.weather.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.mythio.weather.BuildConfig
import com.mythio.weather.R
import com.mythio.weather.utils.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)

            val pref = context!!.applicationContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

            val unitPreference: ListPreference? = findPreference("app_unit")
            val themePreference: ListPreference? = findPreference("app_theme")
            val supportPreference: Preference? = findPreference("app_support")
            val licencePreference: Preference? = findPreference("app_licence")
            val versionPreference: Preference? = findPreference("app_version")

            versionPreference?.summary = BuildConfig.VERSION_NAME

            unitPreference?.setOnPreferenceChangeListener { _, newValue ->
                when (newValue.toString()) {
                    "unit_metric" ->
                        pref.edit()
                            .putInt(SHARED_PREF_KEY_UNIT, UNIT_METRIC)
                            .apply()
                    "unit_imperial" ->
                        pref.edit()
                            .putInt(SHARED_PREF_KEY_UNIT, UNIT_IMPERIAL)
                            .apply()
                }
                true
            }

            themePreference?.setOnPreferenceChangeListener { _, newValue ->
                when (newValue.toString()) {
                    "theme_light" -> {
                        pref.edit()
                            .putInt(SHARED_PREF_KEY_THEME, AppCompatDelegate.MODE_NIGHT_NO)
                            .apply()
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }

                    "theme_dark" -> {
                        pref.edit()
                            .putInt(SHARED_PREF_KEY_THEME, AppCompatDelegate.MODE_NIGHT_YES)
                            .apply()
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }

                    "theme_system" -> {
                        pref.edit()
                            .putInt(SHARED_PREF_KEY_THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                            .apply()
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                }
                true
            }

            supportPreference?.setOnPreferenceClickListener {
                val emailIntent = Intent(Intent.ACTION_SENDTO)
                emailIntent.data = Uri.parse("mailto:mythio.2909@gmail.com")
                startActivity(Intent.createChooser(emailIntent, "Support us!"))
                true
            }
        }
    }
}