package com.nextlink.studentmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nextlink.studentmanager.Attendence.Attendence_manage;
import com.nextlink.studentmanager.utils.DbHelper;

import java.util.List;

public class Timeline_details extends AppCompatActivity {

    List<timeline_model> detailList;

    RecyclerView recyclerView;

    DbHelper myDb = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_details);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_timeline);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        timeline_adapter adapter = new timeline_adapter(this, Attendence_manage.subname);

        recyclerView.setAdapter(adapter);
        ImageView img=findViewById(R.id.imageView5);
        if(adapter.getItemCount()==0){
            img.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline_clear, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
                myDb.clear_timeline();
                Toast.makeText(this,"Cleared ",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,home.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
