package com.example.lingxiao.tasktodo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by lingxiao on 4/17/18.
 */

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "alarm!", Toast.LENGTH_LONG).show();
    }
}
