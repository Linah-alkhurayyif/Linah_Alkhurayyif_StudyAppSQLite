package com.example.linah_alkhurayyif_studyappsqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,"studyDataBase",null,1){
    var sqLiteDatabase: SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        if(db != null){
            db.execSQL("CREATE TABLE kotlinTable (id INTEGER PRIMARY KEY ,topics TEXT,descriptions TEXT)")
            db.execSQL("CREATE TABLE androidTable (id INTEGER PRIMARY KEY,topics TEXT,descriptions TEXT)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}
    fun addToDB(topics:String,descriptions:String,tableName:String): Long{
        val contentValues = ContentValues()
        contentValues.put("topics", topics)
        contentValues.put("descriptions", descriptions)
        val success = sqLiteDatabase.insert("$tableName", null, contentValues)
        return success
    }


    @SuppressLint("Range")
    fun viewData(tableName:String): ArrayList<StudyMaterials>{
        val studyMaterialsList: ArrayList<StudyMaterials> = ArrayList()
        val selectQuery = "SELECT * FROM $tableName"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var topics: String
        var descriptions: String

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                topics = cursor.getString(cursor.getColumnIndex("topics"))
                descriptions = cursor.getString(cursor.getColumnIndex("descriptions"))
                val studyMaterials = StudyMaterials(id,topics,descriptions)
                studyMaterialsList.add(studyMaterials)
            } while (cursor.moveToNext())
        }

        return studyMaterialsList
    }
    fun updateData(studyMaterials: StudyMaterials,tableName:String): Int {
        val contentValues = ContentValues()

        contentValues.put("topics", studyMaterials.topics)
        contentValues.put("descriptions", studyMaterials.descriptions)
        val success = sqLiteDatabase.update("$tableName", contentValues, "id = ${studyMaterials.id}", null)
        return success
    }
    fun deleteData(studyMaterials: StudyMaterials,tableName:String): Int{
        val contentValues = ContentValues()
        contentValues.put("topics", studyMaterials.topics)
        contentValues.put("descriptions", studyMaterials.descriptions)
        val success = sqLiteDatabase.delete("$tableName", "id = ${studyMaterials.id}", null)
        return success
    }
}