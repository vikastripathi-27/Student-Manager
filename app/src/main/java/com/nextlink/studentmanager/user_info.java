package com.nextlink.studentmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nextlink.studentmanager.utils.DbHelper;

public class user_info extends AppCompatActivity {

    TextView name_disp,branch_disp,college_disp,semester_disp,rollno_disp,web_disp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        name_disp =  findViewById(R.id.name_user);
        college_disp = findViewById(R.id.college_user);
        semester_disp = findViewById(R.id.semester_user);
        branch_disp = findViewById(R.id.branch_user);
        rollno_disp = findViewById(R.id.rollno_user);
        web_disp = findViewById(R.id.cllg_web);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid()).child("data");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userProfile userprofile = dataSnapshot.getValue(userProfile.class);
                name_disp.setText(userprofile.getName_register());
                college_disp.setText(userprofile.getCollege_register());
                branch_disp.setText(userprofile.getBranch_register());
                semester_disp.setText(userprofile.getSemester_register());
                rollno_disp.setText(userprofile.getRollno_register());
                web_disp.setText(userprofile.getWebsite_register());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(user_info.this,"Cannot connect to server",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_profile:
                Intent intent = new Intent(this,edit_user_profile.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);

    }

}
