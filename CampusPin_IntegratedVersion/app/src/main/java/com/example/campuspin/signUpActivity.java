package com.example.campuspin;

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

public class signUpActivity extends AppCompatActivity {
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mCreateAccountBtn;
    protected String email;
    private String password;
    boolean valid = false;
    private FirebaseAuth mAuth;
    public static final String EXTRA_MESSAGE = "USERNAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mAuth = FirebaseAuth.getInstance();
        mEmailField = (EditText) findViewById(R.id.emailFieldSignup);
        mCreateAccountBtn = (Button) findViewById(R.id.CreateAccountBtn);
        mPasswordField =  (EditText) findViewById(R.id.passwordFieldSignup);
        mCreateAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();

            }
        });

    }
    private void createAccount() {
        email = mEmailField.getText().toString();
        password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)) {
            Toast.makeText(signUpActivity.this, "Fields are empty.", Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(signUpActivity.this, "Registered Successfully.", Toast.LENGTH_LONG).show();
                    Intent intent2 = new Intent(signUpActivity.this,  MainActivity.class);
                    String username;
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
                    intent2.putExtra(EXTRA_MESSAGE, username);
                    startActivity(intent2);

                } else {
                    Toast.makeText(signUpActivity.this, "Registered failed, please try again.....", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
