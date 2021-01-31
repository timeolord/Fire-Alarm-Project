
package com.mchacks.firealarmproject.wave;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import android.os.Build;
import android.media.MediaRecorder;
import android.util.Log;
import androidx.annotation.RequiresApi;

import com.mchacks.firealarmproject.MainActivity;

public class AudioRecording {
    private static final String LOG_TAG = "AUDIO_FAILED";
    private MediaRecorder recording = null;

    public AudioRecording() {
        this.recording = new MediaRecorder();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startRecording() throws IllegalStateException{

        // creating media recorder
        recording.setAudioSource(MediaRecorder.AudioSource.MIC);
        recording.setOutputFile(MainActivity.fileName);
        recording.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS); //what output format do we want or AMR_NB
        recording.setAudioEncoder(MediaRecorder.AudioEncoder.AAC); //encoder?
        try {
            recording.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recording.start();

    }

    public void stopRecording() {
        recording.stop();
        recording.release(); // free up object for new recording
        recording = null;

    }



}
