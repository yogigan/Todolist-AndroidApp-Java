package com.example.todolist.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolist.R;

public class FormProfileActivity extends AppCompatActivity {
    EditText editTextName;
    RadioButton radioMale, radioFemale;
    SeekBar seekBarAge;
    CheckBox checkBoxPainting, checkboxReading, checkboxSinging;
    Button buttonSave;
    AlertDialog.Builder dialog;
    View dialogView;
    LayoutInflater inflater;
    TextView textSeekbarAge, textName, textGender, textAge, textHobby;
    String selectedGender, selectedHobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_profile);

        editTextName = findViewById(R.id.editName);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        textSeekbarAge = findViewById(R.id.textSeekbarValue);
        seekBarAge = findViewById(R.id.seekBarAge);
        seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSeekbarAge.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        checkBoxPainting = findViewById(R.id.checkboxPainting);
        checkboxReading = findViewById(R.id.checkboxReading);
        checkboxSinging = findViewById(R.id.checkboxSinging);
        buttonSave = findViewById(R.id.btnSave);
        buttonSave.setOnClickListener(v -> {
            showDialog();
        });

    }

    private void showDialog() {
        dialog = new AlertDialog.Builder(FormProfileActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.alert_dialog, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("");

        textName = dialogView.findViewById(R.id.textName);
        textGender = dialogView.findViewById(R.id.textGender);
        textAge = dialogView.findViewById(R.id.textAge);
        textHobby = dialogView.findViewById(R.id.textHobby);

        textName.setText("  Nama : " + editTextName.getText());

        selectedGender = "";
        if (radioMale.isChecked()) {
            selectedGender = "Laki-laki";
        } else if (radioFemale.isChecked()) {
            selectedGender = "Perempuan";
        }
        textGender.setText("  Jenis Kelamin : " + selectedGender);
        textAge.setText("  Umur : " + textSeekbarAge.getText());

        selectedHobby = "";
        if (checkBoxPainting.isChecked()) {
            selectedHobby += " Melukis";
        }
        if (checkboxReading.isChecked()) {
            selectedHobby += " Membaca";
        }
        if (checkboxSinging.isChecked()) {
            selectedHobby += " Menyanyi";
        }
        textHobby.setText("  Hobi : " + selectedHobby);

        dialog.setPositiveButton("Simpan", (dialog, which) -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("name",editTextName.getText().toString());
            intent.putExtra("gender",selectedGender);
            intent.putExtra("age",textSeekbarAge.getText());
            intent.putExtra("hobby",selectedHobby);
            dialog.dismiss();
            startActivity(intent);
        });
        dialog.setNegativeButton("Tutup", (dialog, which) -> dialog.dismiss());
        dialog.show();
    }

}