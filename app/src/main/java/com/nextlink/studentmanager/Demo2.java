package com.nextlink.studentmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Demo2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
    }

    public void demo2(View view) {
        Intent intent = new Intent(Demo2.this,Demo3.class);
        startActivity(intent);
        finish();
    }
}
