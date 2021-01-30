
package com.mchacks.firealarmproject.wave;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.os.ParcelFileDescriptor;

import android.media.MediaRecorder;
import android.util.Log;

public class AudioRecording {
    private static final int BYTE_ARRAY_SIZE = 44;
    private static final String LOG_TAG = "AUDIO_FAILED";
    private MediaRecorder recording = null;

    public AudioRecording() {
        byte[] outputByteArray = new byte[BYTE_ARRAY_SIZE];
    }

    private void startRecording()  {
        // creating byte array to output the file
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ParcelFileDescriptor[] descriptors = ParcelFileDescriptor.createPipe();
        } catch (IOException e) {
            Log.e(LOG_TAG, "createPipe() failed");
        }
        ParcelFileDescriptor[] descriptors = new ParcelFileDescriptor[0];
        ParcelFileDescriptor parcelRead = new ParcelFileDescriptor(descriptors[0]);
        ParcelFileDescriptor parcelWrite = new ParcelFileDescriptor(descriptors[1]);

        InputStream inputStream = new ParcelFileDescriptor.AutoCloseInputStream(parcelRead);
        // creating media recorder
        MediaRecorder recording = new MediaRecorder();
        recording.setAudioSource(MediaRecorder.AudioSource.MIC);
        recording.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); //what output format do we want or AMR_NB
        recording.setOutputFile(parcelWrite.getFileDescriptor());
        recording.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); //encoder?
        try {
            recording.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recording.start();
        int read;
        byte[] data = new byte[16384];

        try {
            while ((read = inputStream.read(data, 0, data.length)) != -1) {
                byteArrayOutputStream.write(data, 0, read);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "inputStream.read() failed");
        }

        try {
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            Log.e(LOG_TAG, "flush() failed");
        }
        byteArrayOutputStream.toByteArray(); // convert to byte[]
    }

    private void stopRecording() {
        recording.stop();
        recording.release(); // free up object for new recording
        recording = null;

    }


}
