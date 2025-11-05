package com.example.pt4_carnerero_prez_vctor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DialActivity extends AppCompatActivity {

    private EditText editPhoneNumber;
    private Button btnDial, btnOpenMapActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);

        editPhoneNumber = findViewById(R.id.editPhoneNumber);
        btnDial = findViewById(R.id.btnDial);
        btnOpenMapActivity = findViewById(R.id.btnOpenMapActivity);

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDial();
            }
        });

        btnOpenMapActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DialActivity.this, MapActivity.class);
                startActivity(i);
            }
        });
    }

    private void handleDial() {
        String number = editPhoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(number)) {
            Toast.makeText(this, "Introduce un número de teléfono.", Toast.LENGTH_SHORT).show();
            return;
        }

        Uri uri = Uri.parse("tel:" + number);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);

            startActivity(intent);

    }
}
