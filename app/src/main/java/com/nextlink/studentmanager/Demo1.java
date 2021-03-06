package com.nextlink.studentmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Demo1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);

        SharedPreferences preferences = getSharedPreferences("PREFERENCE",MODE_PRIVATE);
        String first_time = preferences.getString("FirstTimeInstall","");

        if(first_time.equals("Yes")) {
            Intent intent = new Intent(Demo1.this,MainActivity.class);
            startActivity(intent);
        }
        else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();
        }
    }

    public void demo1(View view) {
        Intent intent = new Intent(Demo1.this,Demo2.class);
        startActivity(intent);
        finish();
    }
}
