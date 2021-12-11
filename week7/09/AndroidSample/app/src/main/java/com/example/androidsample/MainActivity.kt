package com.example.androidsample

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import com.example.androidsample.databinding.ActivityMainBinding
import com.example.recyclerviewjsj191.MyDatabaseHelper
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.CacheResponse
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dbHelper: MyDatabaseHelper

    val handler = object:Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                1 ->{
                    val tvTest = binding.tvTest
                    tvTest.text = "msg: ${msg.what}, $msg.arg1}, ${msg.arg2}, ${msg.obj}"
                }

            }
        }
    }

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = MyDatabaseHelper(this, "BookStore.db", 3)
        val btnSqliteCreate = binding.btnSqliteCreate
        btnSqliteCreate.setOnClickListener {
            dbHelper.writableDatabase
        }

        // sqlite: insert row
        val btnSqliteAdd = binding.btnSqliteAdd
        btnSqliteAdd.setOnClickListener {
            val db = dbHelper.writableDatabase
            val value = ContentValues()
            value.put("name", "han shu")
            value.put("author", "Ban Gu")
            value.put("price", 150)
            value.put("pages", 1000)
            val result = db.insert("Book", null, value)
            Toast.makeText(applicationContext, "result is:$result", Toast.LENGTH_LONG).show()

            val value_category = ContentValues()
            value_category.put("category_name", "computer")
            value_category.put("category_code", "0002")
            val result_category = db.insert("Category", null, value_category)


        }

        // sqlite: update
        val btnSqliteUpdate = binding.btnSqliteUpdate
        btnSqliteUpdate.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            // values.put("author", "ban gu, ban zhao")
            values.put("price", 180)
            val result = db.update("Book", values,
                "price < ? and pages >= ? ",
                arrayOf("160", "1000"))
            Toast.makeText(applicationContext, "result is:$result", Toast.LENGTH_LONG).show()
        }

        // sqlite：Del
        val btnSqliteDel = binding.btnSqliteDel
        btnSqliteDel.setOnClickListener {
            val db = dbHelper.writableDatabase
            val result = db.delete("Book", "id= ?", arrayOf("3"))
            Toast.makeText(applicationContext, "result is:$result", Toast.LENGTH_LONG).show()
        }

        // qlite: search
        val btnSqliteSearch = binding.btnSqliteSearch
        btnSqliteSearch.setOnClickListener {
            val db = dbHelper.readableDatabase
            val cursor = db.query("Book", null, "pages >= ? and pages <= ?",
                arrayOf("600", "3000"), null, null, null)
            val builder = StringBuilder()
            if(cursor.moveToFirst()){
                do{
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    builder.append("$name, $author, $price, $pages\n")
                }while(cursor.moveToNext())
            }
            Toast.makeText(applicationContext, builder.toString(), Toast.LENGTH_LONG).show()
            val tvTest = binding.tvTest
            tvTest.setText(builder.toString())
        }

        // Thread: start
        val btnThreadStart = binding.btnThreadStrat
        btnThreadStart.setOnClickListener {
//            Thread{
//                Log.d("thread", "start thread")
//            }.start()
            thread{
                Log.d("thread", "start thread")
            }
        }

        // Thread: send message
        val btnThreadMsg = binding.btnThreadMsg
        btnThreadMsg.setOnClickListener {
            val msg = Message()
            msg.what = 1
            msg.arg1 = 100
            msg.arg2 = 200
            msg.obj = "Hello, world"
            handler.sendMessage(msg)
        }

        // Thread: run on ui
        val btnThreadUI = binding.btnThreadUI
        btnThreadUI.setOnClickListener {
            thread {
                runOnUiThread{
                    val tvTest = binding.tvTest
                    tvTest.text = "thread"
                }


            }
        }

        // Network: HttpUrlConnection
        val btnHttpUrl = binding.btnHttpUrl
        btnHttpUrl.setOnClickListener {
            sendRequestHttpUrl()
        }

        // HttpUrlConnection: GetAll
        val btnHttpGetAll = binding.btnHttpGetAll
        btnHttpGetAll.setOnClickListener {
            GetAllByHttpUrl()
        }

        // HUrlConnection: GetByRange
        val btnHttpGetByPages = binding.btnHttpGetByPages
        btnHttpGetByPages.setOnClickListener {
            GetPagesRangesByHttpUrl()
        }

        // HttpUrlConnection: AddBook
        val btnHttpAdd = binding.btnHttpAdd
        btnHttpAdd.setOnClickListener {
            AddBookByHttpUrl()
        }


    }

    // send request by httpurlconnection
    private fun sendRequestHttpUrl(){
        thread {
            var connection : HttpURLConnection? = null
            try{
                val response = StringBuilder()
                // val url = URL("http://10.0.2.2:8080/AndroidServer/test.jsp")
                // val url = URL("http://10.0.2.2:8080/AndroidServer/read_all_book.jsp")
                val url = URL("http://10.0.2.2:8080/AndroidServer/read_book_by_pages_range.jsp?min_pages=1000&max_pages=2000")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream

                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            }
            catch (e:Exception){
                e.printStackTrace()
            }
            finally {
                connection?.disconnect()
            }
        }
    }

    private fun GetAllByHttpUrl(){
        thread {
            var connection : HttpURLConnection? = null
            try{
                val response = StringBuilder()
                // val url = URL("http://10.0.2.2:8080/AndroidServer/test.jsp")
                val url = URL("http://10.0.2.2:8080/AndroidServer/read_all_book.jsp")
                // val url = URL("http://10.0.2.2:8080/AndroidServer/read_book_by_pages_range.jsp?min_pages=1000&max_pages=2000")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream

                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            }
            catch (e:Exception){
                e.printStackTrace()
            }
            finally {
                connection?.disconnect()
            }
        }
    }

    private fun GetPagesRangesByHttpUrl(){
        thread {
            var connection : HttpURLConnection? = null
            try{
                val response = StringBuilder()
                // val url = URL("http://10.0.2.2:8080/AndroidServer/test.jsp")
                val url = URL("http://10.0.2.2:8080/AndroidServer/read_book_by_pages_range.jsp")
                // val url = URL("http://10.0.2.2:8080/AndroidServer/read_book_by_pages_range.jsp?min_pages=1000&max_pages=2000")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                connection.requestMethod = "POST"
                val output = DataOutputStream(connection.outputStream)
                output.writeBytes("min_pages=1000&max_pages=2000")
                val input = connection.inputStream

                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            }
            catch (e:Exception){
                e.printStackTrace()
            }
            finally {
                connection?.disconnect()
            }
        }
    }

    private fun AddBookByHttpUrl(){
        thread {
            var connection : HttpURLConnection? = null
            try{
                val response = StringBuilder()
                // val url = URL("http://10.0.2.2:8080/AndroidServer/test.jsp")
                val url = URL("http://10.0.2.2:8080/AndroidServer/add_book.jsp")
//                val url = URL("http://10.0.2.2:8080/AndroidServer/add_book.jsp" +
//                        "?name=新唐书&author=欧阳修&pages=1400&price=118")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                connection.requestMethod = "POST"
                connection.setRequestProperty("Accept-Charset", "UTF-8")
                connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=utf-8")
                val output = DataOutputStream(connection.outputStream)
                val paraStr :String = "name=新唐书&author=欧阳修&pages=1400&price=118"
                output.write(paraStr.toByteArray())
                val input = connection.inputStream

                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            }
            catch (e:Exception){
                e.printStackTrace()
            }
            finally {
                connection?.disconnect()
            }
        }
    }

    private fun showResponse(response: String){
        runOnUiThread{
            val tvResult = binding.tvResult
            tvResult.text = response
        }
    }
}