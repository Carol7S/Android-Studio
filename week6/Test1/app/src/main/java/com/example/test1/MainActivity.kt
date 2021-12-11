package com.example.test1

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test1.data.MyDataBaseHelper
import com.example.test1.databinding.ActivityMainBinding
import java.lang.StringBuilder
import java.net.ContentHandler

class MainActivity : AppCompatActivity() {
    private val dbHelper = MyDataBaseHelper(this,"Book",2)

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //数据库创建
        binding.btnCreateDB.setOnClickListener(){
            dbHelper.writableDatabase
        }

        //添加数据
        binding.btnInsert.setOnClickListener(){
            //定位数据库
            val db = dbHelper.writableDatabase
            //准备数据
            val values = ContentValues()
//            values.put("category_name","史书")
//            values.put("category_code","0101")
//            //写入
//            db.insert("Category",null,values)
//            Toast.makeText(this,"写入完成",Toast.LENGTH_LONG).show()
            values.clear()
            values.apply {
                put("name","史记")
                put("author","司马迁")
                put("price",60)
                put("pages",800)
            }
            var num = db.insert("Book", null, values)

            values.clear()
            values.apply {
                put("name","汉书")
                put("author","班固")
                put("price",160)
                put("pages",1600)
            }
            num = db.insert("Book", null, values)

            values.clear()
            values.apply {
                put("name","三国志")
                put("author","陈寿")
                put("price",50)
                put("pages",300)
            }
            num = db.insert("Book", null, values)

            Toast.makeText(this,"写入完成, num=${num}",Toast.LENGTH_LONG).show()
        }

        binding.btnUpdate.setOnClickListener(){
            // update book
            // set author = ""
            // where id = ?
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            //values.put("author", "班固，班昭")
            //val num = db.update("Book",values,"id = ?", arrayOf("2"))
            values.put("author","班彪，班固，班昭")
            values.put("price", 300)
            val num = db.update("Book",values,"pages>=? and pages<=?", arrayOf("50","200"))
            Toast.makeText(this, "修改完成 num=${num}", Toast.LENGTH_LONG).show()
        }

        binding.btnDelete.setOnClickListener(){
            val db = dbHelper.writableDatabase
            val num = db.delete("Book", "id=?", arrayOf("2"))
            Toast.makeText(this,"删除完成, num=${num}", Toast.LENGTH_LONG).show()
        }

        binding.btnSelectAll.setOnClickListener(){
            val db = dbHelper.readableDatabase
            val cursor = db.query("Book",null,null,null,null,null,null,null)
            val builder = parseCursor(cursor)

            Toast.makeText(this,"book\n${builder.toString()}",Toast.LENGTH_LONG).show()
        }

        binding.btnSelectIf.setOnClickListener(){
            val db = dbHelper.readableDatabase
//            val cursor = db.query("Book",null,"pages>=?", arrayOf("100"),null,null,null)
            val cursor = db.query("Book",null,"pages >= ?", arrayOf("500"),null,null,"price")

            val builder = parseCursor(cursor)
            Toast.makeText(this,"book\n${builder.toString()}",Toast.LENGTH_LONG).show()
        }
    }

    private fun parseCursor(cursor:Cursor):StringBuilder{
        val builder = StringBuilder()
        if(cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val author = cursor.getString(cursor.getColumnIndex("author"))
                val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                val price = cursor.getDouble(cursor.getColumnIndex("price"))
                builder.append("$id--$name--$author--$pages--$price\n")
            }while (cursor.moveToNext())
        }
        return builder
    }
}