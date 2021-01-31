package com.mchacks.firealarmproject;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.mchacks.firealarmproject.wave.AudioRecording;

import java.io.File;
import java.sql.Time;

import static com.mchacks.firealarmproject.App.CHANNEL_ID;

public class AudioProcessor extends Service {
    public AudioProcessor() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("Input Extra");

        //Creates the foreground notification

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Audio Processor")
                .setContentText(input).setSmallIcon(R.drawable.ic_hearing_disabled).setContentIntent(pendingIntent).build();

        startForeground(1, notification);


        Thread thread = new ProcessorThread(this);

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}