package com.royston.overchargedetector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RestartOverchargeDetectionService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context,OverchargeDetectionService.class));
    }
}