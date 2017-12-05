package com.example.prectrum.campuspin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "USERNAME";
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLoginBtn;
    private Button mSignUpBtn;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    protected String email;
    public String username;
    private String password;
    boolean valid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mEmailField = (EditText) findViewById(R.id.emailField);
        mSignUpBtn = (Button) findViewById(R.id.signUpBtn);
        mPasswordField =  (EditText) findViewById(R.id.passwordField);
        mLoginBtn = (Button)findViewById(R.id.loginBtn);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
                System.out.println("valid is : "+valid);
                if (valid){

                    Intent intent1 = new Intent(MainActivity.this,  AccountActitivity.class);
                    intent1.putExtra(EXTRA_MESSAGE, username);
                    startActivity(intent1);
                } else {
                    Toast.makeText(MainActivity.this, "Sign In Problem", Toast.LENGTH_LONG).show();
                }


            }
        });

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this,  TestActivity.class);
              //  intent2.putExtra(EXTRA_MESSAGE, username);
                startActivity(intent2);


            }
        });

    }


    private void startSignIn() {
        email = mEmailField.getText().toString();
        password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "Fields are empty.", Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //System.out.println("1111111111111111111");
                    if (!task.isSuccessful()) {
                        System.out.println("1111111111111111111");
                        Toast.makeText(MainActivity.this, "Sign In Problem", Toast.LENGTH_LONG).show();
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
            System.out.println("2222222222221");

        }
    }
}

/*
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               // System.out.println("I'm listener current user is  " + firebaseAuth.getCurrentUser().getEmail());
                if (firebaseAuth.getCurrentUser() != null) {

                      Intent intent1 = new Intent(MainActivity.this,  AccountActitivity.class);
                      intent1.putExtra(EXTRA_MESSAGE, username);
                    //  startActivity(intent1);
                } else {

                    System.out.println("I'm listener " + email);
                }
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
*/