package com.example.tawaqapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText editTextTextEmailAddress, editTextTextPassword;
    Button loginBtn;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextTextEmailAddress=(EditText)findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword=(EditText)findViewById(R.id.editTextTextPassword);
        loginBtn=(Button)findViewById(R.id.loginBtn);
        fAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_EmailInp = editTextTextEmailAddress.getText().toString().trim();
                String password_Inp = editTextTextPassword.getText().toString().trim();

                if(TextUtils.isEmpty(user_EmailInp)){
                    editTextTextEmailAddress.setError("Email is Required. ");
                    return;
                }
                if(TextUtils.isEmpty(password_Inp)){
                    editTextTextPassword.setError("Password is Required. ");
                    return;
                }
                fAuth.signInWithEmailAndPassword(user_EmailInp, password_Inp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), home.class));
                        }else{
                            Toast.makeText(login.this, "Error! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    public void backToMain(View V){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}