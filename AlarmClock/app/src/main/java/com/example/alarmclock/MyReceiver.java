package com.example.alarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class MyReceiver extends BroadcastReceiver {

    //Purpose : Broadcast receiver
    @Override
    public void onReceive(Context context, Intent intent) {

        String state = intent.getExtras().getString("hour");
        Log.e("MyActivity", "In the receiver with " + state );

        Intent scheduledIntent = new Intent(context, MyScheduledActivity.class);
        scheduledIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(scheduledIntent);
    }
}
