package com.praktikum.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ImplicitIntent extends AppCompatActivity {

    Button Share;
    Button Alarm;
    EditText etjam, etmenit, etdes;

    final int REQUEST_CODE = 111;
    Uri imageUri;
    private Button Btnfoto;
    private ImageView ImageView;


    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);

        Share = findViewById(R.id.share);
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, "Praktikum Mobile Implicit Intent");
                startActivity(i.createChooser(i, "Share"));

            }
        });

        etjam =findViewById(R.id.jam);
        etmenit=findViewById(R.id.menit);
        etdes=findViewById(R.id.des);

        Alarm=findViewById(R.id.btnalarm);
        Alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int jam = Integer.parseInt(etjam.getText().toString());
                int menit = Integer.parseInt(etmenit.getText().toString());
                String des = String.valueOf(etdes.getText());

                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_HOUR, jam);
                i.putExtra(AlarmClock.EXTRA_MINUTES, menit);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, des);

                if (jam <= 24 && menit <=60) {
                    startActivity(i);
                } else
                    Toast.makeText(getApplicationContext(),"Waktu Salah", Toast.LENGTH_SHORT).show();

            }
        });

        Btnfoto = findViewById(R.id.btnfoto);
        ImageView = findViewById(R.id.hasilfoto);

        Btnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {
            imageUri = data.getData();
            ImageView.setImageURI(imageUri);
        }
    }
}