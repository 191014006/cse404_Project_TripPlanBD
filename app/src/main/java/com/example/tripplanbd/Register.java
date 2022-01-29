package com.example.tripplanbd;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    private EditText nameEdit,emailEt,phoneEdit,passwordEdit,confirmPassWordEdit;
    private Button register;
    private TextView login;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth =FirebaseAuth.getInstance();

        nameEdit = findViewById(R.id.nameEt);
        emailEt = findViewById(R.id.emailEt);
        phoneEdit= findViewById(R.id.numberEt);
        passwordEdit = findViewById(R.id.passwordEt);
        confirmPassWordEdit = findViewById(R.id.ConfirmPasswordEt);
        register = findViewById(R.id.registerBtn);
        login = findViewById(R.id.Signin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Register.this,LoginActivity.class);
                startActivity(in);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                String email = emailEt.getText().toString();
                String phone = phoneEdit.getText().toString();
                String pass = passwordEdit.getText().toString();
                String confirmpass = confirmPassWordEdit.getText().toString();

                if (name.isEmpty())
                {
                    nameEdit.setError("please write your name");
                    return;
                } if (email.isEmpty()){
                    emailEt.setError("wrong email");
                    return;
                } if (phone.isEmpty()){
                    phoneEdit.setError("give your contact");
                    return;
                } if (pass.isEmpty()){
                    passwordEdit.setError("write password");
                    return;
                } if (confirmpass.isEmpty() || !pass.equals(confirmpass)){
                    confirmPassWordEdit.setError("password dont match");
                    return;
                }

                createAccount(email,pass);
            }
        });
    }
        private void createAccount(String email,String password){
            auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                FirebaseUser user = auth.getCurrentUser();
                                updateUi(user,email);
                                Toast.makeText(Register.this, "Register Successfull", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Register.this,"Register Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        private void updateUi(FirebaseUser user, String email)
        {
            HashMap<String,Object>map=new HashMap<>();
            map.put("name",nameEdit.getText().toString());
            map.put("email",email);
            map.put("phone",phoneEdit.getText().toString());

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User");
            reference.child(user.getUid())
                    .setValue(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                startActivity(new Intent(Register.this,LoginActivity.class));
                                finish();
                                Toast.makeText(Register.this, "Register Successfull", Toast.LENGTH_SHORT).show();
                            } else
                            {
                                Toast.makeText(Register.this,"Register Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }

}