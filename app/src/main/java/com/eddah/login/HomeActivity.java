package com.eddah.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private TextView mfirstname, mlastname, memail;
    private User mUser;
    String FistName, LastName, Email;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
        mfirstname = findViewById(R.id.msFirstName);
        mlastname = findViewById(R.id.msLastName);
        memail = findViewById(R.id.msEmail);

        Intent intent = getIntent();
        if (intent.hasExtra("user")){
            mUser = intent.getParcelableExtra("user");
            if (mUser !=null);
            FistName = intent.getStringExtra("user");
            LastName = intent.getStringExtra("user");
            Email = intent.getStringExtra("user");
        }else{
            Toast.makeText(this, " Nothing passed", Toast.LENGTH_SHORT).show();
        }
        mfirstname.setText(FistName);
        mlastname.setText(LastName);
        memail.setText(Email);

    }
}
