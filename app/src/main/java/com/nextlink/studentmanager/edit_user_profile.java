package com.nextlink.studentmanager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class edit_user_profile extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText profile_name,profile_cllg,profile_branch,profile_semester,profile_rollno,profile_website;
    String user_edit_name,user_edit_cllg,user_edit_branch,user_edit_sem,user_edit_rollno,user_edit_website;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        profile_name = findViewById(R.id.edit_profile_name);
        profile_cllg = findViewById(R.id.edit_profile_college);
        profile_branch = findViewById(R.id.edit_profile_branch);
        profile_semester = findViewById(R.id.edit_profile_sem);
        profile_rollno = findViewById(R.id.edit_profile_rollno);
        profile_website = findViewById(R.id.edit_profile_cllg_website);
        edit = findViewById(R.id.edit_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid()).child("data");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userProfile userprofile = dataSnapshot.getValue(userProfile.class);
                profile_name.setText(userprofile.getName_register());
                profile_cllg.setText(userprofile.getCollege_register());
                profile_branch.setText(userprofile.getBranch_register());
                profile_semester.setText(userprofile.getSemester_register());
                profile_rollno.setText(userprofile.getRollno_register());
                profile_website.setText(userprofile.getWebsite_register());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(edit_user_profile.this,"Cannot connect to server",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void edit_profile(View view) {

        if(profile_name.getText().toString().trim().isEmpty()) {
            profile_name.setError(("Please enter your name"));
        }
        else if(profile_cllg.getText().toString().trim().isEmpty()) {
            profile_cllg.setError(("Please enter your college name"));
        }
        else if(profile_semester.getText().toString().trim().isEmpty()) {
            profile_semester.setError(("Please enter your semester"));
        }
        else if(profile_branch.getText().toString().trim().isEmpty()) {
            profile_branch.setError(("Please enter your branch"));
        }
        else if(profile_rollno.getText().toString().trim().isEmpty()) {
            profile_rollno.setError(("Please enter your roll no"));
        }
        else if(profile_website.getText().toString().trim().isEmpty()) {
            profile_website.setError(("Please enter your college website"));
        }

        else {

            user_edit_name = profile_name.getText().toString().trim();
            user_edit_cllg = profile_cllg.getText().toString().trim();
            user_edit_branch = profile_branch.getText().toString().trim();
            user_edit_sem = profile_semester.getText().toString().trim();
            user_edit_rollno = profile_rollno.getText().toString().trim();
            user_edit_website = profile_website.getText().toString().trim();

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());

            userProfile userprofile = new userProfile(user_edit_name,user_edit_cllg,user_edit_sem,user_edit_branch,user_edit_rollno,user_edit_website);
            myRef.child("data").setValue(userprofile);
            Toast.makeText(this,"Changes applied",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,user_info.class);
            startActivity(intent);
            finish();
        }


    }


}
