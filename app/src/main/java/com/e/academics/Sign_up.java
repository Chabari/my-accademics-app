package com.e.academics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Sign_up extends AppCompatActivity {
    private EditText inputEmail, inputPassword,inputCPassword;
    private Button btnSignUp;
    private TextView Already;
    private FirebaseAuth auth;
    private ProgressBar ProgressBar;
    private ProgressDialog progressDialog;

    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        btnSignUp = (Button) findViewById(R.id.btnSign_up);
        Already = (TextView) findViewById(R.id.tvAlready);
        inputEmail = (EditText) findViewById(R.id.edtEmailCreation);
        inputPassword = (EditText) findViewById(R.id.edtPasswordCreation);
        inputCPassword = (EditText)findViewById(R.id.edtPasswordCCreation);
        ProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        Already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String CPassword = inputCPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    //Toast.makeText(getApplicationContext(), "Enter email address...!", Toast.LENGTH_SHORT).show();
                    inputEmail.setError("Enter email address...!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    //Toast.makeText(getApplicationContext(), "Enter password...!", Toast.LENGTH_SHORT).show();
                    inputPassword.setError("Enter password...!");
                    return;
                }

                if (password.length() < 6) {
                    //Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters...!", Toast.LENGTH_SHORT).show();
                    inputPassword.setError("Password too short, enter minimum 6 characters...!");
                    return;
                }
                if(!password.equals(CPassword)){
                    Toast.makeText(getApplicationContext(), "Passwords don't match...!", Toast.LENGTH_SHORT).show();
                    inputPassword.setError("Passwords don't match...!");
                    inputCPassword.setError("Passwords don't match...!");
                    return;
                }


                ProgressBar.setVisibility(View.VISIBLE);

                progressDialog.setMessage("Creating Account...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Sign_up.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    Toast.makeText(Sign_up.this, "Account Created", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Sign_up.this,MainActivity.class));
                                }else {
                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_SHORT).show();
                                }

                                ProgressBar.setVisibility(view.GONE);
                                progressDialog.dismiss();

                            }
                        });

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        ProgressBar.setVisibility(View.GONE);
    }

    }
