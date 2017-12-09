package com.example.campuspin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class EmailPasswordActivity extends AppCompatActivity {
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLoginBtn;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    protected String email;
    public String username;
    private String password;
    boolean valid = false;
    public DatabaseReference myRef;
    public FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password);
        mAuth = FirebaseAuth.getInstance();
        mEmailField = (EditText) findViewById(R.id.emailField);
        mPasswordField =  (EditText) findViewById(R.id.passwordField);
        mLoginBtn = (Button)findViewById(R.id.loginBtn);
        myRef = database.getReference();
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
                if (valid){
                    myRef.child("currentUser").setValue("Anonymous");
                    Intent intent1 = new Intent(EmailPasswordActivity.this,MainActivity.class);
                    intent1.putExtra("username", username);
                    startActivity(intent1);
                } else {
                    Toast.makeText(EmailPasswordActivity.this, "Sign In Problem", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void signUp (View view){
        Intent intent2 = new Intent(this,  signUpActivity.class);
        //  intent2.putExtra(EXTRA_MESSAGE, username);
        startActivity(intent2);
    }

    private void startSignIn() {
        email = mEmailField.getText().toString();
        password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)) {
            Toast.makeText(EmailPasswordActivity.this, "Fields are empty.", Toast.LENGTH_LONG).show();
        } else {

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(EmailPasswordActivity.this, "Sign In Problem", Toast.LENGTH_LONG).show();
                    } else {

                        StringBuilder sb = new StringBuilder();
                        char[] array = email.toCharArray();
                        for (char c : array) {
                            if (c != '@') {
                                sb.append(c);
                            } else {
                                break;
                            }

                        }
                        username = sb.toString();
                        valid = true;
                    }
                }
            });
        }
    }
}