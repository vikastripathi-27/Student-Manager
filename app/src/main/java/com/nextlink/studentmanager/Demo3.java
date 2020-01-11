package com.nextlink.studentmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Demo3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);
    }

    public void demo3(View view) {
        Intent intent = new Intent(Demo3.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
