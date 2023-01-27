package com.example.myapplication

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_infocontainer, InfoNoteFragment()).commit()
        }
        else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NoteFragment()).commit()
        }
         val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
         val navigationView = findViewById<NavigationView>(R.id.nav_view)
         navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.about -> {
                    supportFragmentManager.beginTransaction().add(R.id.fragment_container,SettingsFragment()).addToBackStack("").commit()
                    drawer.close()
                    return@setNavigationItemSelectedListener true
                }
                R.id.exit -> {
                    finish()
                    return@setNavigationItemSelectedListener true
                }
            else -> return@setNavigationItemSelectedListener false
            }

        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.params_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings -> { Toast.makeText(this, "Какие-то настройки", Toast.LENGTH_SHORT ).show()}
            R.id.head_search -> {
                Toast.makeText(this, "Поиск", Toast.LENGTH_SHORT ).show()
            }
            R.id.head_sort -> {
                Toast.makeText(this, "Sort", Toast.LENGTH_SHORT ).show()
            }
        }
        return super.onOptionsItemSelected(item);
    }

}