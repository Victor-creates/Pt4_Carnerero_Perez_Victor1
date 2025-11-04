package com.example.pt4_carnerero_prez_vctor;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText editAlarmMessage;
    private Button btnCrearAlarma, btnNomIntent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ajuste de insets (lo tenías en tu código original)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vistas
        editAlarmMessage = findViewById(R.id.editAlarmMessage);
        btnCrearAlarma = findViewById(R.id.btnCrearAlarma);
        btnNomIntent2 = findViewById(R.id.btnNomIntent2);

        btnCrearAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimePickerAndCreateAlarm();
            }
        });

        btnNomIntent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciamos DialActivity (la otra actividad que hemos creado)
                Intent i = new Intent(MainActivity.this, DialActivity.class);
                startActivity(i);
            }
        });
    }

    private void openTimePickerAndCreateAlarm() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                createAlarm(selectedHour, selectedMinute);
            }
        }, hour, minute, true);

        tpd.setTitle("Selecciona hora de la alarma");
        tpd.show();
    }

    private void createAlarm(int hour, int minute) {
        String message = editAlarmMessage.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            message = "Alarma desde la app";
        }

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minute)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message);

        // Control de errores: comprobar que haya app para manejar el intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No hay app de reloj disponible para crear alarmas.", Toast.LENGTH_LONG).show();
        }
    }
}
