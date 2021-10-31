package com.example.linah_alkhurayyif_studyappsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_study_materials.*

class AddStudyMaterials : AppCompatActivity() {
    private lateinit var db: DatabaseHandler
    lateinit var tableName:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_study_materials)
        db = DatabaseHandler(this)
        tableName = intent.extras?.getString("tableName")!!
        save.setOnClickListener {
            addNote()
            if(tableName == "kotlinTable"){
                val intent = Intent(this, KotlinReview()::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, AndroidReview()::class.java)
                startActivity(intent)
            }
        }
        back.setOnClickListener {
            if(tableName == "kotlinTable"){
                val intent = Intent(this, KotlinReview()::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, AndroidReview()::class.java)
                startActivity(intent)
            }
        }
    }
    private fun addNote(){
        if(topic_et.text.isEmpty() || description_et.text.isEmpty()){
            Toast.makeText(this, "Error topic/description is empty!!", Toast.LENGTH_LONG).show()
        }else{
            db.addToDB(topic_et.text.toString(),description_et.text.toString(),tableName)
            topic_et.text.clear()
            description_et.text.clear()
            Toast.makeText(this, "Note topic and description successfully!!", Toast.LENGTH_LONG).show()
        }

    }
}