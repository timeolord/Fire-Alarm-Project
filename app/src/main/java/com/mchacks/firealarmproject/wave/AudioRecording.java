
package com.mchacks.firealarmproject.wave;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import java.io.File;

import android.os.ParcelFileDescriptor;

import android.media.MediaRecorder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class AudioRecording {
    private static final int BYTE_ARRAY_SIZE = 44;
    private static final String LOG_TAG = "AUDIO_FAILED";
    private MediaRecorder recording;
    private byte[] outputByteArray;
    private static File outputFile;

    public File getOutputByteArray() {
        return outputFile;
    }



    public AudioRecording() { byte[] outputByteArray = new byte[BYTE_ARRAY_SIZE]; }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startRecording()  {
        /* // creating byte array to output the file
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ParcelFileDescriptor[] descriptors = ParcelFileDescriptor.createPipe();
        } catch (IOException e) {
            Log.e(LOG_TAG, "createPipe() failed");
        }
        ParcelFileDescriptor[] descriptors = new ParcelFileDescriptor[20];
        ParcelFileDescriptor parcelRead = new ParcelFileDescriptor(descriptors[0]);
        ParcelFileDescriptor parcelWrite = new ParcelFileDescriptor(descriptors[1]);

        InputStream inputStream = new ParcelFileDescriptor.AutoCloseInputStream(parcelRead);
        */


        File directory = Environment.getExternalStorageDirectory();
        try {
            outputFile = File.createTempFile("audio","3gp",directory);
        } catch (IOException e) {
            Log.e(LOG_TAG, "external storage access error");
        }
        // creating media recorder
        MediaRecorder recording = new MediaRecorder();
        recording.setAudioSource(MediaRecorder.AudioSource.MIC);
        recording.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); //what output format do we want or AMR_NB
        recording.setOutputFile(outputFile);
        recording.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); //encoder?
        try {
            recording.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recording.start();



        /*int read;
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

         */
    }

    public void stopRecording() {
        recording.stop();
        recording.release(); // free up object for new recording
        recording = null;

    }


}
