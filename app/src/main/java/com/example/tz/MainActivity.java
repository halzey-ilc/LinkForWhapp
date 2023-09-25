package com.example.tz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {

    private static final String message = " set";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppCompatButton sendbtn = findViewById(R.id.sendbtn);
        final AppCompatButton btn2 = findViewById(R.id.btn2);

        sendbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                chooseImageFromGallery();
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, message);
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                startActivity(intent );
            }
        });
    }

    private void chooseImageFromGallery() {

        activityResultLauncher.launch("image/*");

    }

    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {

            Intent intent  = new Intent(Intent.ACTION_SEND);

            intent.putExtra(Intent.EXTRA_TEXT, message);

            intent.putExtra(Intent.EXTRA_STREAM, result);

            intent.setType("text/plain");

            intent.setType("image/jpeg");

            intent.setPackage("com.telegram");

            startActivity(intent );
        }
    });
}
