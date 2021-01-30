package com.mchacks.firealarmproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AudioProcessor extends Service {
    public AudioProcessor() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}