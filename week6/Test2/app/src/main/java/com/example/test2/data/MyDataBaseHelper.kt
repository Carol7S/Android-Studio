package com.example.test2.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

//context:上下文
//name:数据库名称
//version：版本号
class MyDataBaseHelper(val context: Context, name:String, version:Int): SQLiteOpenHelper(context,name,null,version) {
    // 创建车次表
    // val name:String, val startStation:String, val endStation:String, val startTime:String,
    // val endTime:String, var shangWu:Int, var firstNumber:Int, var secondNumber:Int, var wuZuo:Int
    val CREATE_TRAIN = " create table Train ( " +
            " id integer primary key autoincrement " +
            " , name text" +
            " , startStation text" +
            " , endStation text" +
            " , startTime text" +
            " , endTime text" +
            " , shangWu integer" +
            " , firstNumber integer" +
            " , secondNumber integer" +
            " , wuZuo integer )"

    //创建数据库 db：数据对象
    override fun onCreate(p0: SQLiteDatabase?) {
        if (p0 != null) {
            p0.execSQL(CREATE_TRAIN)
        }

        Toast.makeText(context, "创建成功", Toast.LENGTH_LONG).show()
    }

    //升级
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p0 != null) {
            p0.execSQL("drop table if exists Train")
        }
        onCreate(p0)
    }
}