package com.swapp.waraconvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pedro.library.AutoPermissions;
import com.swapp.waraconvapp.DB.Constant;
import com.swapp.waraconvapp.DB.DataBase;
import com.swapp.waraconvapp.DB.DatabaseHelper;
import com.swapp.waraconvapp.Input.SearchActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoPermissions.Companion.loadAllPermissions(this,101);

        Button button = findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });


        try{
            boolean result = isCheckDB(getApplicationContext());
            Log.d("WaraConv", "DB Check="+result);
            if(!result){
                copyDB(getApplicationContext());
            }
        }catch (Exception e){

        }

        Button exit_button = findViewById(R.id.exit_button);
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public boolean isCheckDB(Context context){
        String filepath = "/data/data/" + context.getPackageName() + "/databases/" + Constant.DATABASE_NAME;
        File file = new File(filepath);

        if(file.exists()){
            return true;
        }
        return false;
    }
    public void copyDB(Context context){
        AssetManager manager = context.getAssets();
        String folderpath = "/data/data/" + context.getPackageName() + "/databases";
        String filepath = "/data/data/" + context.getPackageName() + "/databases/" + Constant.DATABASE_NAME;

        File folder = new File(folderpath);
        File file = new File(filepath);


        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            InputStream is = manager.open(Constant.DATABASE_NAME);
            BufferedInputStream bis = new BufferedInputStream(is);

            if (folder.exists()) {

            }else{
                folder.mkdirs();
            }
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }

            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            int read = -1;
            byte[] buffer = new byte[1024];
            while ((read = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, read);
            }
            bos.flush();
            bos.close();
            fos.close();
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("ErrorMessage : ", e.getMessage());
        }
    }
}