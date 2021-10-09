package com.example.todolist.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolist.R;

public class ProfileActivity extends AppCompatActivity {
    TextView textName, textAge, textGender, textHobby;
    Button buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textName = findViewById(R.id.textName);
        textGender = findViewById(R.id.textGender);
        textAge = findViewById(R.id.textAge);
        textHobby = findViewById(R.id.textHobby);
        buttonExit = findViewById(R.id.btnExit);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String age = intent.getStringExtra("age");
        String hobby = intent.getStringExtra("hobby");

        textName.setText("Nama : " + name);
        textGender.setText("Jenis Kelamin : " + gender);
        textAge.setText("Umur : " + age);
        textHobby.setText("Hobi : " + hobby);

        buttonExit.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Peringatan")
                    .setMessage("Kamu yakin ingin keluar?")
                    .setPositiveButton("OK", (dialog, which) ->
                            onDestroy())
                    .setNegativeButton("Batal", (dialog, which) -> dialog.cancel())
                    .show();
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Selamat datang kembali", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}