package com.eddah.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.time.Instant;
import java.util.Objects;

public class ImageUpload extends AppCompatActivity {
    private static final int IMAGE_REQUEST = 1 ;
    private Button mchooseimg, mupload;
    private ImageView mimageview;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    public static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private StorageTask uploadTask;
    StorageReference storageReference;
    DatabaseReference reference;
    String Userid;
    private Instant Glide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        mchooseimg = findViewById(R.id.chooseimg);
        mupload = findViewById(R.id.upload);
        mimageview = findViewById(R.id.imageview);
        mchooseimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImg();
            }

        });
    }

    private void chooseImg() {
        Intent intent = new Intent();
        intent.setType("image/*");
         intent.setAction(Intent.ACTION_PICK);
         startActivityForResult(Intent.createChooser(intent,"select image"),PICK_IMAGE_REQUEST);
    }
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = Objects.requireNonNull(this).getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE_REQUEST && RESULT_OK==resultCode && data !=null && data.getData() !=null && mImageUri == data.getData());
        Toast.makeText(this, "choose"+mImageUri.toString(), Toast.LENGTH_SHORT).show();


    }




    }

