package com.example.myapplication

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
}