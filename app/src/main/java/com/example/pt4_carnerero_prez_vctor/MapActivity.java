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

public class MapActivity extends AppCompatActivity {

    private EditText editLocationQuery;
    private Button btnOpenMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        editLocationQuery = findViewById(R.id.editLocationQuery);
        btnOpenMap = findViewById(R.id.btnOpenMap);

        btnOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMapWithQuery();
            }
        });
    }

    private void openMapWithQuery() {
        String query = editLocationQuery.getText().toString().trim();
        if (TextUtils.isEmpty(query)) {
            Toast.makeText(this, "Introduce una dirección o punto de interés.", Toast.LENGTH_SHORT).show();
            return;
        }

        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(query));
        Intent intent = new Intent(Intent.ACTION_VIEW, geoUri);

            startActivity(intent);

    }
}
