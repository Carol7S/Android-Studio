package com.example.mysqltest2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.mysqltest2.dao.UserDao;
import com.example.mysqltest2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        UserDao userDao = new UserDao();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        ActivityMainBinding binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.getRoot());

        binding.sqlQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    userDao.select("sds_user");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            userDao.select("sds_user");
            //userDao.insert("sds_user","dorothy","admin");
            //userDao.delete("sds_user","dorothy");
            //userDao.update("sds_user","dorothy","123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}