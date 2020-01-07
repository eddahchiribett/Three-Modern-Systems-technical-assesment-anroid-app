package com.eddah.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class LoginActivity extends AppCompatActivity {
    private User mUser;
    private EditText email, password;
    private Button login;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.msEmail);
        password = findViewById(R.id.msPassword);
        login = findViewById(R.id.nrShowInput);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        Intent intent = getIntent();
        if (intent.hasExtra("user")){
            mUser = intent.getParcelableExtra("user");
        }else {
            Toast.makeText(this, " Nothing Passed", Toast.LENGTH_SHORT).show();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  Email  = email.getText().toString();
                String  Password = password.getText().toString();
                dologin( Email, Password);
            }
        });


    }

    private void dologin( String Email, String Password) {
     mAuth.signInWithEmailAndPassword(Email, Password)
             .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()){
                         Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                         intent.putExtra("user",mUser);
                          startActivity(intent);
                     }else {
                         String error = task.getException().getMessage();
                         Toast.makeText(LoginActivity.this, "error" + error, Toast.LENGTH_SHORT).show();
                     }

                 }
             })
             .addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                     String failure = e.getMessage();
                     Toast.makeText(LoginActivity.this, "error" + failure, Toast.LENGTH_SHORT).show();

                 }
             });

    }
}
