package com.example.linah_alkhurayyif_studyappsqlite

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_add_study_materials.*
import kotlinx.android.synthetic.main.activity_kotlin_review.*
import kotlinx.android.synthetic.main.alartdialog.*

class KotlinReview : AppCompatActivity() {
    private lateinit var db: DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_review)
        db = DatabaseHandler(this)

        KotlinAddbtn.setOnClickListener {

            val intent = Intent(this, AddStudyMaterials()::class.java)
            intent.putExtra("tableName", "kotlinTable")
            startActivity(intent)
        }
        RV_update()
        backKotlin.setOnClickListener {
            val intent = Intent(this, MainActivity()::class.java)
            startActivity(intent);
        }
    }
    private fun RV_update(){
        KotlinRecyclerView.adapter = StudyAdapter(this@KotlinReview,null,getItemsList(),"kotlinTable")
        KotlinRecyclerView.layoutManager=LinearLayoutManager(this)
    }
    private fun getItemsList(): ArrayList<StudyMaterials>{
        return db.viewData("kotlinTable")
    }
    fun delete_Data(item: StudyMaterials){
        db.deleteData(StudyMaterials(item.id,item.topics,item.descriptions),"kotlinTable")
        RV_update()
    }
    fun update_Data(item: StudyMaterials){
        db.updateData(StudyMaterials(item.id,item.topics,item.descriptions),"kotlinTable")
        RV_update()
    }

    fun raiseDialog(item: StudyMaterials){
        //Inflate the dialog as custom view
        val messageBoxView = LayoutInflater.from(this).inflate(R.layout.alartdialog, null)
        //AlertDialogBuilder
        val messageBoxBuilder = AlertDialog.Builder(this).setView(messageBoxView)
        val  messageBoxInstance = messageBoxBuilder.show()
        var topics=item.topics
        var descriptions= item.descriptions
        //setting text values
        messageBoxInstance.et_topics.hint= item.topics
        messageBoxInstance.et_descriptions.hint= item.descriptions
        messageBoxInstance.btn_save.setOnClickListener {
            if(messageBoxInstance.et_topics.text.isNotEmpty()){
                topics=messageBoxInstance.et_topics.text.toString()
            }
            if(messageBoxInstance.et_descriptions.text.isNotEmpty()){
                descriptions=messageBoxInstance.et_descriptions.text.toString()
            }
            update_Data(StudyMaterials(item.id, topics,descriptions))
            messageBoxInstance.dismiss()
        }
        //set Listener
        messageBoxView.setOnClickListener(){
            //close dialog
            messageBoxInstance.dismiss()
        }
    }
}