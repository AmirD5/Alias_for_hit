package com.hit.aliasgameapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import java.util.Locale

object LocaleHelper {
    private const val PREFS_NAME = "language_prefs"
    private const val KEY_LANGUAGE = "selected_language"
    private const val DEFAULT_LANGUAGE = "en" // English as default

    /**
     * Set the app locale
     */
    fun setLocale(context: Context, languageCode: String): Context {
        saveLanguage(context, languageCode)
        return updateResources(context, languageCode)
    }

    /**
     * Get the saved language preference
     */
    fun getLanguage(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_LANGUAGE, DEFAULT_LANGUAGE) ?: DEFAULT_LANGUAGE
    }

    /**
     * Toggle between supported languages (English and Hebrew)
     */
    fun toggleLanguage(context: Context): String {
        val currentLanguage = getLanguage(context)
        val newLanguage = when (currentLanguage) {
            "en" -> "he" // Switch from English to Hebrew
            "he" -> "en" // Switch from Hebrew to English
            else -> "en" // Default to English
        }
        setLocale(context, newLanguage)
        return newLanguage
    }

    /**
     * Save language preference
     */
    private fun saveLanguage(context: Context, languageCode: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit {
            putString(KEY_LANGUAGE, languageCode)
        }
    }

    /**
     * Update the app resources with the selected locale
     */
    private fun updateResources(context: Context, languageCode: String): Context {
        val locale = Locale.forLanguageTag(languageCode)
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.setLocale(locale)

        return context.createConfigurationContext(config)
    }
}

