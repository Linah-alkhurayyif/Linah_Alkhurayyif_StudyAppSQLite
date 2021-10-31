package com.example.linah_alkhurayyif_studyappsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Androidbutton.setOnClickListener{
            val intent = Intent(this, AndroidReview()::class.java)
            startActivity(intent)
        }
        Kotlinbutton.setOnClickListener{
            val intent = Intent(this, KotlinReview()::class.java)
            startActivity(intent)
        }
    }
}