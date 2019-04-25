package com.e.academics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView tvsignup,tvforgotpass;
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private Button btnLogIn;
    private ProgressDialog progressDialog;

    private static final String TAG = "FAXELOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);

        btnLogIn = (Button) findViewById(R.id.btnLogin);

        tvsignup = (TextView) findViewById(R.id.tvSignup);
        tvforgotpass = (TextView) findViewById(R.id.tvForgotpass);
        inputEmail = (EditText)findViewById(R.id.edtemaillogin);
        inputPassword = (EditText) findViewById(R.id.edtpasslogin);
        auth = FirebaseAuth.getInstance();

        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Sign_up.class);
                startActivity(intent);
            }
        });

        tvforgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), forgot_password.class);
                startActivity(intent);
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("Authenticating...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();



                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();

                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        String error = task.getException().getMessage();
                                        Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(MainActivity.this, nav_menu.class);
                                    startActivity(intent);
                                    finish();
                                }

                            }
                        });

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser !=null)

        {
            updateUI();
        }
    }

    private void updateUI() {

        Intent intent = new Intent(MainActivity.this, nav_menu.class);
        startActivity(intent);
        finish();

    }
}
