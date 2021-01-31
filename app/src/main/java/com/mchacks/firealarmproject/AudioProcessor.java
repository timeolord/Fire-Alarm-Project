package com.mchacks.firealarmproject;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.mchacks.firealarmproject.App.CHANNEL_ID;

public class AudioProcessor extends Service {
    public AudioProcessor() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String input = intent.getStringExtra("Input Extra");

        //Creates the foreground notification

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Audio Processor")
                .setContentText(input).setSmallIcon(R.drawable.ic_hearing_disabled).setContentIntent(pendingIntent).build();

        this.startForeground(1, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_MICROPHONE);

        //Starts a new thread for recording
        Thread thread = new ProcessorThread(this);

        thread.start();

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

    // CONVERT AUDIO RECORDINGS TO BYTE ARRAY METHOD

    public byte[] convertToByteArray(String inputFile) {

        // input stream -> input file
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // write filename to output stream
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();

        try {
            while (inputStream.available() > 0) {
                byteOutputStream.write(inputStream.read());
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        byte[] byteArray = byteOutputStream.toByteArray(); // CONVERT TO BYTE ARRAY

        return byteArray;

    }

}