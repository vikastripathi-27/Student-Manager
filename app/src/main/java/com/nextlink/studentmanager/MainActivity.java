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
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    EditText email_id,password;
    Button sign_in,sign_up;
    String user_email_id,user_password;
    private FirebaseAuth firebaseAuth;
    ProgressDialog prDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email_id = findViewById(R.id.email_sign_in);
        password = findViewById(R.id.passsword_sign_in);
        sign_in = findViewById(R.id.sign_in);
        sign_up = findViewById(R.id.sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null) {
            finish();
            Intent intent = new Intent(this,home.class);
            startActivity(intent);
        }
    }

    public void login(View view) {

        if(email_id.getText().toString().trim().isEmpty()) {
            email_id.setError(("Please enter your e-mail id"));
        }
        else if(password.getText().toString().trim().isEmpty()) {
            password.setError(("Please enter your password"));
        }

        else {
            prDialog = new ProgressDialog(MainActivity.this);
            prDialog.setMessage("Validating credentials");
            prDialog.show();
            user_email_id = email_id.getText().toString().trim();
            user_password = password.getText().toString().trim();
            firebaseAuth.signInWithEmailAndPassword(user_email_id,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        prDialog.dismiss();
                        Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,home.class);
                        startActivity(intent);
                    }
                    else {
                        prDialog.dismiss();
                        Toast.makeText(MainActivity.this,"Please enter valid credentials",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    public void register (View view) {
        Intent intent = new Intent(this,activity2.class);
        startActivity(intent);
    }

}
