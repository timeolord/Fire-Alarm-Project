package com.mchacks.firealarmproject;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.mchacks.firealarmproject.wave.AudioRecording;

import java.io.File;

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
        audio.stopRecording();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    ProcessorThread(Context context){
        this.context = context;
    }
}
