package com.example.test1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.SDKInitializer
import com.baidu.mapapi.map.MapStatusUpdate
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.model.LatLng
import com.example.test1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  lateinit  var locationClient:LocationClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SDKInitializer.initialize(applicationContext) // 必须在这个位置初始化
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 获取权限
        getPermission()

        // 初始化定位客户端
        initLoaction()

        // permission error
        val btnCall = binding.btnCall
        btnCall.setOnClickListener {
            try{
                val intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse("tel:10086")
                startActivity(intent)
            }
            catch (e: SecurityException){
                e.printStackTrace()
            }
        }

        // check permission
        val btnPermission = binding.btnPermission
        btnPermission.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ){
                // 检测到权限尚未授权, 请求授权
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CALL_PHONE), 1)
            }
            else{
                call()
            }
        }

        // map scal
        val btnScale = binding.btnScale
        btnScale.setOnClickListener {
            val mapView = binding.mapView
            val map = mapView.map
            val update : MapStatusUpdate = MapStatusUpdateFactory.zoomTo(14.5f)
            map.animateMapStatus(update)
        }

        // map: mov
        val btnMove = binding.btnMove
        btnMove.setOnClickListener {
            val mapView = binding.mapView
            val map = mapView.map
            val latLng: LatLng = LatLng(30.23, 119.72)
            val update : MapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng)
            map.animateMapStatus(update)
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        val mapView = binding.mapView
        mapView.onDestroy()

        // 销毁定位客户端
        locationClient.stop()
    }

    override fun onPause() {
        super.onPause()
        val mapView = binding.mapView
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        val mapView = binding.mapView
        mapView.onResume()
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if(grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call()
                }
                else{
                    Toast.makeText(this, "权限未授权", Toast.LENGTH_LONG).show()
                }
            }

            2->{
                if(grantResults.isNotEmpty() ){
                    // 授权列表不为空
                    for(result in grantResults){
                        if(result != PackageManager.PERMISSION_GRANTED){
                            // 只有有一个未授权，则授权失败
                            val tvResult = binding.tvResult
                            tvResult.setText("授权失败")
                            return
                        }
                    }
                    // 执行到这一步，则成功
                    val tvResult = binding.tvResult
                    tvResult.setText("授权成功")

                }
            }
        }
    }

    private fun initLoaction(){
        // 初始化定位客户端
        locationClient = LocationClient(applicationContext)
        //通过LocationClientOption设置LocationClient相关参数
        val option = LocationClientOption()
        option.isOpenGps = true // 打开gps
        option.setCoorType("bd09ll") // 设置坐标类型
        option.setScanSpan(1000)

        //设置locationClientOption
        locationClient.locOption = option

        locationClient.registerLocationListener(MyLocationListener())
        locationClient.start()
    }

    // 必须加上inner后，方可访问外界的变量
    private inner  class MyLocationListener : BDLocationListener{
        // 接收位置信息
        override fun onReceiveLocation(position: BDLocation?) {
            val builder = StringBuilder()
            builder.append("经度: ${position?.longitude}--纬度: ${position?.latitude}")
            Log.d("baidui", builder.toString())
            val tvResult = binding.tvResult
            tvResult.text = builder.toString()
        }
    }

    private fun call(){
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:10086")
        startActivity(intent)
    }


    // 获取权限
    private fun getPermission(){
        // 定义一个权限列表；只要有一个权限未通过，则权限不对
        val permissionList = ArrayList<String>()
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ){
            // 未授权，则加入到未授权列表中
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ){
            // 未授权，则加入到未授权列表中
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            // 未授权，则加入到未授权列表中
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }


        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            // 未授权，则加入到未授权列表中
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        // 如果权限列表不为空，则需要授权
        if( !permissionList.isEmpty() ){
            ActivityCompat.requestPermissions(this, permissionList.toTypedArray(), 2)
        }
        else{
            val tvResult = binding.tvResult
            tvResult.text = "权限通过"
        }

    }
}