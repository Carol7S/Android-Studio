package com.example.test1.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

//context:上下文
//name:数据库名称
//version：版本号
class MyDataBaseHelper(val context:Context, name:String, version:Int): SQLiteOpenHelper(context,name,null,version){
    // 创建图书表
    val CREATE_BOOK = " create table Book ( " +
            " id integer primary key autoincrement " +
            " , author text" +
            " , price real" +
            " , pages integer" +
            " , name text )"

    //种类
    private val CREATE_CATEGORY = "create table Category( " +
            " id integer primary key autoincrement , " +
            " category_name text, " +
            " category_code text )"

    //创建数据库 db：数据对象
    override fun onCreate(p0: SQLiteDatabase?) {
        if (p0 != null) {
            p0.execSQL(CREATE_BOOK)
        }
        if (p0 != null) {
            p0.execSQL(CREATE_CATEGORY)
        }
        Toast.makeText(context,"创建成功",Toast.LENGTH_LONG).show()
    }
    //升级
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p0 != null) {
            p0.execSQL("drop table if exists Book")
        }
        if (p0 != null) {
            p0.execSQL("drop table if exists Category")
        }
        onCreate(p0)
    }

}