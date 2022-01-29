package com.example.tripplanbd;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEt,passEt;
    private Button signin;
    private TextView signup;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        emailEt = findViewById(R.id.emailEtLogin);
        passEt = findViewById(R.id.passwordEtLogin);
        signin = findViewById(R.id.login);
        signup = findViewById(R.id.SingUp);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this,Register.class);
                startActivity(in);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEt.getText().toString();
                String password = passEt.getText().toString();

                if (TextUtils.isEmpty(email)){
                    emailEt.setError("error");
                    return;
                } if (TextUtils.isEmpty(password)){
                    passEt.setError("Error");
                    return;
                }
                signIn(email,password);
            }
        });

    }

    private void signIn(String email, String password){
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Registered First"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}