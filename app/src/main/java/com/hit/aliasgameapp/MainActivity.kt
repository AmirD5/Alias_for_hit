package com.hit.aliasgameapp  // Adjust if your package name differs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import com.hit.aliasgameapp.util.LocaleHelper

class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val languageCode = LocaleHelper.getLanguage(newBase)
        val context = LocaleHelper.setLocale(newBase, languageCode)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}