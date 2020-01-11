package com.nextlink.studentmanager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nextlink.studentmanager.Attendence.Attendence_manage;
import com.nextlink.studentmanager.model.Week;
import com.nextlink.studentmanager.utils.DbHelper;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class home extends AppCompatActivity {
   // Button but1,but2,but3,but4;
    public static ArrayList<Week> myList;
   // public static ArrayList<Week> hlist = new ArrayList<>();
    public static List<String> list = new ArrayList<>();
    public  static CharSequence time;
    public static String x;
    public static  ArrayList<attmodel> subname;
    TextView user;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

         user = findViewById(R.id.user_name);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid()).child("data");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userProfile userprofile = dataSnapshot.getValue(userProfile.class);
                user.setText(" " + userprofile.getName_register());
           }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(home.this,"Cannot connect to server",Toast.LENGTH_SHORT).show();
            }
        });

        //for logout(done by vikas)
      //  firebaseAuth = FirebaseAuth.getInstance();

    }

    public void user_prof(View view) {
        Intent intent = new Intent(this,user_info.class);
        startActivity(intent);
    }

    public void time_table(View view) {
        DbHelper db;
        db = new DbHelper(getApplication());
        int count = db.subject_count();
        if(count == 0) {
            //disable
            Toast.makeText(this,"Enter all the subjects first",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this,app_bar.class);
            startActivity(intent);
        }
    }

    public void attendance(View view) {
        DbHelper db;
        int i=0;
        int j=0;

        Date date = new Date();
        time = DateFormat.format("EEEE", date.getTime());
        db = new DbHelper(getApplication());
        myList =db.getWeek(time.toString());


        while(i<myList.size()){
            Log.i("TAG",String.valueOf(myList.get(i).getId()));
            list.add(myList.get(i).toString());
            i++;
        }
        Intent intent = new Intent(this,Attendence_manage.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)list);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);

    }

    public void todo(View view) {
        Intent intent = new Intent(this, to_do.class);
        startActivity(intent);
    }

    public void subject(View view) {
        Intent intent = new Intent(this, Add_Subject.class);
        startActivity(intent);
    }

    public void website(View view) {
        Intent intent = new Intent(this, cllg_website.class);
        startActivity(intent);
    }



    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                firebaseAuth.signOut();
                finish();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;

//            case R.id.about:
//                Toast.makeText(this,"About",Toast.LENGTH_SHORT).show();
//                break;

            case R.id.help:
                Intent intent1 = new Intent(this,Demo1_duplicate.class);
                startActivity(intent1);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
