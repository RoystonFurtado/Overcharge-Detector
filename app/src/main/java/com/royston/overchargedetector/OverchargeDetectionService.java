package com.royston.overchargedetector;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class OverchargeDetectionService extends Service {

    FullyChargedReceiver fullyChargedReceiver;
    int flag;

    @Override
    public void onCreate() {
        super.onCreate();
        flag=0;
        fullyChargedReceiver=new FullyChargedReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent,flags,startId);
        Log.d("OverchargeDetectionService.class","Service Started");
        IntentFilter intentFilter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        if(flag==0) {
            registerReceiver(fullyChargedReceiver, intentFilter);
            flag=1;
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (flag == 1) {
            unregisterReceiver(fullyChargedReceiver);
            flag = 0;
            Log.d("OverchargeDetectionService.class","Inside OnDestroy");
        }
        Intent restartService=new Intent(this,RestartOverchargeDetectionService.class);
        sendBroadcast(restartService);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (flag == 1) {
            unregisterReceiver(fullyChargedReceiver);
            flag = 0;
            Log.d("OverchargeDetectionService.class","Inside OnTaskRemoved");
        }
        Intent restartService=new Intent(this,RestartOverchargeDetectionService.class);
        sendBroadcast(restartService);
    }

    public OverchargeDetectionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}