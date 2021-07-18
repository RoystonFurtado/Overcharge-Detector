package com.royston.overchargedetector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class FullyChargedReceiver extends BroadcastReceiver {
    static boolean playing;
    static boolean played;
    static Ringtone ringtoneSound;
    static Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);//The default sound of the alarm's URI is stored in ringtoneUri;

    @Override
    public void onReceive(Context context, Intent intent) {
        int status=intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        if(ringtoneSound==null)
            ringtoneSound = RingtoneManager.getRingtone(context.getApplicationContext(), ringtoneUri);//The default sound of the alarm is stored in ringtoneSound
        playing=ringtoneSound.isPlaying();
        if(status==BatteryManager.BATTERY_STATUS_DISCHARGING) {
            if(playing)
                ringtoneSound.stop();
            played=false;
        }
        if(status==BatteryManager.BATTERY_STATUS_FULL && !playing && !played) {
            Toast.makeText(context.getApplicationContext(), "Charging",Toast.LENGTH_LONG).show();
            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    if(ringtoneSound.isPlaying()) {
                        ringtoneSound.stop();
                        played = true;
                    }
                }
            };
            Timer timer=new Timer();
            timer.schedule(task,10000);
            ringtoneSound.play();
        }
    }
}