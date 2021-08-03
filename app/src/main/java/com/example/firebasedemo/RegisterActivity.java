package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Button register;
    private TextView email;
    private TextView pass;
    private TextView confirmPass;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register=findViewById(R.id.registerBut);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        confirmPass=findViewById(R.id.editConfirm);

        auth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passText = pass.getText().toString();
                String confirmText = confirmPass.getText().toString();

                if (emailText.isEmpty()||passText.isEmpty()||confirmText.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Invalid Credentials",Toast.LENGTH_LONG).show();
                }else if(passText.length()<6){
                    Toast.makeText(RegisterActivity.this,"Password too short!",Toast.LENGTH_LONG).show();
                }else if(!passText.equals(confirmText)){
                    Toast.makeText(RegisterActivity.this,"Passwords doesn't match",Toast.LENGTH_LONG).show();
                }else{
                    registerUser(emailText,passText);
                }

            }
        });

    }

    private void registerUser(String emailText, String passText) {

        auth.createUserWithEmailAndPassword(emailText,passText).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Registration successful",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(RegisterActivity.this,"Registration not successful",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}