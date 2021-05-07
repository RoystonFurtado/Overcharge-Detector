package com.royston.overchargedetector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class FullyChargedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Battery Level Changed",Toast.LENGTH_SHORT).show();
        int status=intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
//        if(status==BatteryManager.BATTERY_STATUS_CHARGING) {
//            Toast.makeText(context.getApplicationContext(), "Charging",Toast.LENGTH_LONG).show();
//        }
    }
}