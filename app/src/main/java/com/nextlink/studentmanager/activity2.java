package com.nextlink.studentmanager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class activity2 extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    String user_name_register,user_college_register,user_semester_register,user_branch_register,user_rollno_register,user_pass_register,user_email_register,user_website_register;
    EditText name_register,college_register,branch_register,semester_register,rollno_register,email_register,pass_register,website_register;
    Button reg;
    ProgressDialog prDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        name_register = findViewById(R.id.name);
        college_register = findViewById(R.id.college);
        semester_register = findViewById(R.id.semester);
        branch_register = findViewById(R.id.branch);
        rollno_register = findViewById(R.id.rollno);
        email_register = findViewById(R.id.email_id_register);
        pass_register = findViewById(R.id.passsword_register);
        website_register = findViewById(R.id.website);
        reg = findViewById(R.id.register);


        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void user_registration(View view) {
        if(name_register.getText().toString().trim().isEmpty()) {
            name_register.setError(("Please enter your name"));
        }
        else if(email_register.getText().toString().trim().isEmpty()) {
            email_register.setError(("Please enter your e-mail id"));
        }
        else if(pass_register.getText().toString().trim().isEmpty()) {
            pass_register.setError(("Please enter your password"));
        }
        else if(college_register.getText().toString().trim().isEmpty()) {
            college_register.setError(("Please enter your college name"));
        }
        else if(semester_register.getText().toString().trim().isEmpty()) {
            semester_register.setError(("Please enter your semester"));
        }
        else if(branch_register.getText().toString().trim().isEmpty()) {
            branch_register.setError(("Please enter your branch"));
        }
        else if(rollno_register.getText().toString().trim().isEmpty()) {
            rollno_register.setError(("Please enter your roll no"));
        }
        else if(website_register.getText().toString().trim().isEmpty()) {
            website_register.setError(("Please enter your college website"));
        }

        else {
            prDialog = new ProgressDialog(activity2.this);
            prDialog.setMessage("Registering user");
            prDialog.show();
            user_email_register = email_register.getText().toString().trim();
            user_pass_register = pass_register.getText().toString().trim();
            firebaseAuth.createUserWithEmailAndPassword(user_email_register,user_pass_register).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        sendUserData();
                        prDialog.dismiss();
                        Toast.makeText(activity2.this,"Registration successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity2.this,MainActivity.class);
                        startActivity(intent);

                    }
                    else {
                        prDialog.dismiss();
                        Toast.makeText(activity2.this,"Registration failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }



    public void sendUserData() {
        user_name_register = name_register.getText().toString().trim();
        user_college_register = college_register.getText().toString().trim();
        user_semester_register = semester_register.getText().toString().trim();
        user_branch_register = branch_register.getText().toString().trim();
        user_rollno_register = rollno_register.getText().toString().trim();
        user_website_register = website_register.getText().toString().trim();



        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());

        userProfile userprofile = new userProfile(user_name_register,user_college_register,user_semester_register,user_branch_register,user_rollno_register,user_website_register);

        myRef.child("data").setValue(userprofile);
    }
}
