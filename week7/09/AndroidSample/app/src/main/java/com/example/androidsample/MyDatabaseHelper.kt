package com.example.recyclerviewjsj191

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context:Context, name:String, version: Int)
    :SQLiteOpenHelper(context, name, null, version) {

    // 创建图书表
    val CREATE_BOOK = " create table Book ( " +
            " id integer primary key autoincrement " +
            " , author text" +
            " , price real" +
            " , pages integer" +
            " , name text )"

    //
    private val CREATE_CATEGORY = "create table Category( " +
            " id integer primary key autoincrement , " +
            " category_name text, " +
            " category_code text )"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_BOOK)
        db.execSQL(CREATE_CATEGORY)
        Toast.makeText(context, "create successed", Toast.LENGTH_LONG).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists Book")
        db.execSQL("drop table if exists Category")
        onCreate(db)
    }
}