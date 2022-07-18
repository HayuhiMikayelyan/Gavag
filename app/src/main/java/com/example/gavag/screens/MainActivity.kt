package com.example.gavag.screens

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.gavag.R
import com.example.gavag.databinding.ActivityMainBinding
import com.example.gavag.databinding.FragmentHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var languageOrder = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocate()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (languageOrder == 1) {
            binding.languageIcon.setImageResource(R.drawable.united_kingdom)
        } else if (languageOrder == 2) {
            binding.languageIcon.setImageResource(R.drawable.russia)
        } else {
            binding.languageIcon.setImageResource(R.drawable.armenia)
        }

        binding.menuIcon.setOnClickListener {
            showPopup(it)
        }

        binding.languageIcon.setOnClickListener {
            if (languageOrder < 3) languageOrder++ else languageOrder = 1
            changeLanguage()
        }

    }

    fun showPopup(v: View) {
        val popup = PopupMenu(this, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_actions, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    try {
                        findNavController(R.id.fragmentContainerView).navigate(R.id.action_logFragment_to_homeFragment)
                    }catch (e:IllegalArgumentException){}
                }
                R.id.action_log -> {
                    try {
                        findNavController(R.id.fragmentContainerView).navigate(R.id.action_homeFragment_to_logFragment)
                    }catch (e:IllegalArgumentException){}
                }
            }
            true
        }
        popup.show()
    }

    fun changeLanguage() {
        if (languageOrder == 1) {
            setLocate("en")

            recreate()
        } else if (languageOrder == 2) {
            setLocate("ru")
            recreate()
        } else {
            setLocate("hy")
            recreate()

        }
    }

    fun setLocate(Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config,baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Language", Context.MODE_PRIVATE).edit()
        editor.putString("My_lang",Lang)
        editor.putInt("Language_order",languageOrder)
        editor.apply()
    }

    private fun loadLocate(){
        val sharedPreferences = getSharedPreferences("Language", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_lang","")
        languageOrder = sharedPreferences.getInt("Language_order",1)
        setLocate(language!!)
    }
}
