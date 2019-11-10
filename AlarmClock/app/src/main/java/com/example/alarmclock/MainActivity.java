package com.example.alarmclock;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

//Purpose : Widgets declaration
    AlarmManager alarmManager;
    private PendingIntent pending_intent;
    private TimePicker timePicker1;

    Context context;

    //Purpose : Alarm Manager is being used to set alarm for a specific time
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;
        final Intent myIntent = new Intent(this.context, MyReceiver.class);

        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        final Calendar calendar = Calendar.getInstance();
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        Button setAlarm= (Button) findViewById(R.id.setAlarm);
        final TextView alarmTime = (TextView) findViewById(R.id.alarmTime);

        //Purpose : Pick user selected time and send to alarm manager to set alarm on button click
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                calendar.add(Calendar.SECOND, 0);

                final int hour = timePicker1.getCurrentHour();
                final int minute = timePicker1.getCurrentMinute();;

                Log.e("MyActivity", "In the receiver with " + hour + " and " + minute);

                alarmTime.setText("Alarm is set to " + hour+ ":" + minute );

                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, timePicker1.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker1.getCurrentMinute());

                myIntent.putExtra("hour", "hour");
                pending_intent = PendingIntent.getBroadcast(getBaseContext(), 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.setExact(AlarmManager.RTC, calendar.getTimeInMillis(), pending_intent);

            }
        });
    }
}
