package etec.com.ewen.matheus.appplantae.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import etec.com.ewen.matheus.appplantae.AlertReceiver;
import etec.com.ewen.matheus.appplantae.R;

public class TelaAlarme extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_alarme);

        spinner2 = findViewById(R.id.spinner2);
        String[] horarios = getResources().getStringArray(R.array.horario);
        spinner2.setAdapter(new ArrayAdapter<String>(TelaAlarme.this, R.layout.style_spinner, horarios ));



        Button buttonTimePicker = findViewById(R.id.button_timepicker);
        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                /*DialogFragment timePicker = new TimePickerFragment();
                //DialogFragment timePicker = new DialogFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");*/
                updateTimeText(/*c*/);
                startAlarm(/*c*/);
            }
        });
        Button buttonCancelAlarm = findViewById(R.id.button_cancel);
        buttonCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(/*c*/);
        startAlarm(/*c*/);
    }
    private void updateTimeText(/*Calendar c*/) {
        //timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(/*Calendar c*/) {
        int hora = Integer.parseInt(spinner2.getSelectedItem().toString());
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hora);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Lembretes de aviso para as " + hora + "H", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Lembrete de exemplo deve ser exibido logo!", Toast.LENGTH_LONG).show();
        
    }
    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarme cancelado!", Toast.LENGTH_SHORT).show();

    }
}