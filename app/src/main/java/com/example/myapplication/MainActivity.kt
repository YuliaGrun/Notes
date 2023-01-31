package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private companion object{
        private const val CHANNEL_ID = "CHANNEL_ID"
    }
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
                    createNotification()
                    showNotification()
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
    private fun showNotification(){
        val notif = NotificationCompat.Builder(this, "$CHANNEL_ID")
            .setContentTitle("Sample Title")
            .setContentText("This is sample body notif")
            .setSmallIcon(R.drawable.ic_receive)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        NotificationManagerCompat.from(this).notify(0, notif)

    }
    private fun createNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "MyNotification"
            val descriptionText = "My notification channel description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("$CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.params_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings -> {

            }
            R.id.head_search -> {
                Toast.makeText(this, "Поиск", Toast.LENGTH_SHORT ).show()
            }
            R.id.head_sort -> {
                Toast.makeText(this, "Sort", Toast.LENGTH_SHORT ).show()
            }
        }
        return super.onOptionsItemSelected(item);
    }
    fun getArrayNotes(): Array<Note> {
        return arrayOf(
            Note("note1", "Some text1", "01.01.2023"),
            Note("note2", "Some text2", "02.01.2023"),
            Note("note3", "Some text3", "03.01.2023"),
        )
    }

}
