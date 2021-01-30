/*

package com.mchacks.firealarmproject.wave;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

import android.os.Environment;
import android.os.ParcelFileDescriptor;

import android.media.MediaRecorder;

public class AudioRecording() {
    private static final int BYTE_ARRAY_SIZE = 44;

    public AudioRecording() {
        byte[] outputByteArray = new byte[BYTE_ARRAY_SIZE];
    }

    private void startRecording() {
        // creating byte array to output the file
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ParcelFileDescriptor[] descriptors = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor parcelRead = new ParcelFileDescriptor(descriptors[0]);
        ParcelFileDescriptor parcelWrite = new ParcelFileDescriptor(descriptors[1]);

        InputStream inputStream = new ParcelFileDescriptor.AutoCloseInputStream(parcelRead);
        // creating mediarecorder
        MediaRecorder recording = new MediaRecorder();
        recording.setAudioSource(MediaRecorder.AudioSource.MIC);
        recording.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); //what output format do we want
        recording.setOutputFile(outputFileName);
        recording.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); //encoder?
        try {
            recording.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recording.start();
        int read;
        byte[] data = new byte[16384];

        while ((read = inputStream.read(data, 0, data.length)) != -1) {
            byteArrayOutputStream.write(data, 0, read);
        }

        byteArrayOutputStream.flush();
        byteArrayOutputStream.toByteArray(); // convert to byte[]


    }

    private void stopRecording() {
        recording.stop();
        recording.release(); // free up object for new recording
        recording = null;

    }


}
*/