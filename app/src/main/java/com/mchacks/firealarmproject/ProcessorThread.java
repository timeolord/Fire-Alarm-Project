package com.mchacks.firealarmproject;

import android.content.Context;
import android.media.MediaPlayer;

public class ProcessorThread implements Runnable{
    private Context context;

    @Override
    public void run() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this.context, R.raw.minecraft);
        mediaPlayer.start();
    }

    ProcessorThread(Context context){
        this.context = context;
        run();
    }
}
