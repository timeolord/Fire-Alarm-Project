package com.mchacks.firealarmproject;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.mchacks.firealarmproject.wave.AudioRecording;

public class ProcessorThread extends Thread{
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {
        AudioRecording audio = new AudioRecording();
        audio.startRecording();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread = new ProcessorThread(context);
        thread.start();

        audio.stopRecording();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    ProcessorThread(Context context){
        this.context = context;
    }
}
