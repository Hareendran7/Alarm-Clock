package com.example.alarmclock;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyScheduledActivity  extends AppCompatActivity {

    //Purpose : Widget declaration
    private Button alarmOff;
    static Ringtone ringtone;
    private TextView currentTime;

    static final int PICK_CONTACT_REQUEST = 1;

    //Purpose : Activity which will be invoked when alarm is triggered
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduled_activity);

        Log.e("MyActivity", "In the receiver with ");
        TextView currentTime = (TextView) findViewById(R.id.currentTime);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm");
        String strDate =  mdformat.format(calendar.getTime());
        Log.e("MyActivity", strDate);
        currentTime.setText(strDate);

        //Purpose : Setting up a default alarm
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(MyScheduledActivity.this, alarmUri);
        ringtone.play();

        alarmOff = findViewById(R.id.alarmOff);

        //Purpose : Start the Quiz Activity on button click
        alarmOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MyScheduledActivity.this, QuizActivity.class);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);

            }
        });
    }

    //Purpose : Stop the ringing alarm when correct answer is selected from the QuizActivity class
        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){

            // check that it is the Quiz_Activity with an OK result
            if (requestCode == PICK_CONTACT_REQUEST) {
                if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                   ringtone.stop();
                   startActivity(new Intent (MyScheduledActivity.this , MainActivity.class));

                }
            }

        }


}
