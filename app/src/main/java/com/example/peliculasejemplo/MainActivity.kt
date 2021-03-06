package com.example.peliculasejemplo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar);
        setSupportActionBar(toolbar)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.homeFragment -> {
                var preferences = this.getSharedPreferences("login", Context.MODE_PRIVATE)
                var editor: SharedPreferences.Editor = preferences.edit()
                editor.putString("login", "false")
                editor.apply()
                val navController = findNavController(R.id.nav_host_fragment)
                item.onNavDestinationSelected(navController)
                true
            }
            R.id.userFragment -> {
                val navController = findNavController(R.id.nav_host_fragment)
                item.onNavDestinationSelected(navController)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}