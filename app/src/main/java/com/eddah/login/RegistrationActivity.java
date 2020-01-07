package com.eddah.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    private EditText mFirstName, mLastName, mEmail, mPassword ,mConfirmPassword;
    private  Button Register;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_register);
        mFirstName = findViewById(R.id.mFirstName);
        mLastName = findViewById(R.id.mLastName);
        mEmail = findViewById(R.id.mEmail);
        mPassword = findViewById(R.id.mPassword);
        mConfirmPassword = findViewById(R.id.mConfirmPassword);


                Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();
                String email = mEmail.getText().toString();
                String  password = mPassword.getText().toString();
                String confirmPassword = mConfirmPassword.getText().toString();

                if (!firstName.isEmpty()){
                    if (!lastName.isEmpty()){
                        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                            if (!password.isEmpty() && password.length() >=6){
                                if (!confirmPassword.isEmpty() && confirmPassword.equals(password)){
                                    doRegister( firstName, lastName, email, password, confirmPassword);

                                }else{
                   mFirstName.setError("Required");
                   mFirstName.requestFocus();
                }
                }else {
                        mLastName.setError("Required");
                        mLastName.requestFocus();}
                    }else {
                        mEmail.setError("Invalid Email");
                        mEmail.requestFocus();}
                        }else{
                            mPassword.setError("Invalid Password");}
                            }else{
                                mConfirmPassword.setError("Password does not match");
                                mConfirmPassword.requestFocus();
                            }
                        }
                    });
                }

    private void doRegister(final String firstName, final String lastName, final String email, final String password, final String confirmPassword) {
         mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User( firstName,lastName,email,password,confirmPassword);
                         Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                              sendUserDetails(firstName, lastName, email, password, confirmPassword);
                        }else{
                            String error = task.getException().getMessage();
                            Toast.makeText(RegistrationActivity.this, "error" + error, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                 .addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         String failure = e.getMessage();
                         Toast.makeText(RegistrationActivity.this, "error" + failure, Toast.LENGTH_SHORT).show();
                     }
                 });


    }

//    private void sendUserDetails(String firstName, String lastName, String email, String password, String confirmPassword) {
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = firebaseDatabase.getReference().child("users");
        // posting firstName, lastName, email, password, confirmpassword in database
    }






