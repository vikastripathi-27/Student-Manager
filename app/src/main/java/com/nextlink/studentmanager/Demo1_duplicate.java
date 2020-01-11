package com.nextlink.studentmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Demo1_duplicate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1_duplicate);
    }

    public void demo1_duplicate(View view) {
        Intent intent = new Intent(Demo1_duplicate.this,Demo2.class);
        startActivity(intent);
        finish();
    }
}
