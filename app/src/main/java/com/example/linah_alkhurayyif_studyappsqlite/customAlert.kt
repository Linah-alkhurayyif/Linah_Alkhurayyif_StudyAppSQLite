package com.example.linah_alkhurayyif_studyappsqlite

import android.app.Activity
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.ditilsdialog.*

class customAlert(activity: Activity,title:String,details: String) {
  init {
      //Inflate the dialog as custom view
      val messageBoxView = LayoutInflater.from(activity).inflate(R.layout.ditilsdialog, null)
      //AlertDialogBuilder
      val messageBoxBuilder = androidx.appcompat.app.AlertDialog.Builder(activity).setView(messageBoxView)
      val  messageBoxInstance = messageBoxBuilder.show()
      //setting text values
      messageBoxInstance.tv_topic.text= title
      messageBoxInstance.description_tv.text= details
      messageBoxInstance.btn_Ok.setOnClickListener {

          messageBoxInstance.dismiss()
      }

  }

}