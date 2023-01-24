package com.example.myapplication

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_infocontainer, InfoNoteFragment()).commit()
        }
        else{
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, NoteFragment()).commit()
        }


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.params_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings -> {supportFragmentManager.beginTransaction().replace(R.id.fragment_settings,SettingsFragment()).commit()}
            R.id.action_about -> {}
        }
        return super.onOptionsItemSelected(item);
    }

}