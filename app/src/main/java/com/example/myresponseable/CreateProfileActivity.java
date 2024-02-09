package com.example.myresponseable;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CreateProfileActivity extends AppCompatActivity {

    Button cameraBT, galleryBT, tochatBT;
    Bitmap imageBitmap;

    ImageView profileImg;

    private ActivityResultLauncher<Intent> cameraIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    imageBitmap = (Bitmap) result.getData().getExtras().get("data");
                    profileImg.setImageBitmap(imageBitmap);

                }
            });

    private ActivityResultLauncher<Intent> galleryIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    try {
                        Uri uri = result.getData().getData();
                        imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        profileImg.setImageBitmap(imageBitmap);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        cameraBT = findViewById(R.id.camera);
        galleryBT = findViewById(R.id.gallery);
        tochatBT = findViewById(R.id.tochat);
        profileImg = findViewById(R.id.profileImage);
        cameraBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openCamera();
            }
        });

        galleryBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        tochatBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tochat();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.launch(intent);
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.launch(intent);
    }



    private void tochat() {
        Intent intent = new Intent(CreateProfileActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

}