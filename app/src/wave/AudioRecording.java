package com.mchacks.firealarm.wave;

import android.media.MediaRecorder;

public class AudioRecording() {
    private static String outputFileName = null;


    private void startRecording() {
        MediaRecorder recording = new MediaRecorder();package com.mchacks.firealarm.wave;

import android.media.MediaRecorder;

        public class AudioRecording() {
            private static String outputFileName = null;


            private void startRecording() {
                MediaRecorder recording = new MediaRecorder();
                recording.setAudioSource(MediaRecorder.AudioSource.MIC);
                recording.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); //what output format do we want
                recording.setOutputFile(outputFileName);
                recording.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); //encoder?

                try {
                    recorder.prepare();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "prepare() failed");
                }

                recorder.start();

            }

            private void stopRecording() {
                recorder.stop();
                recorder.release(); // free up object for new recording
                recorder = null;
            }

        }

        recording.setAudioSource(MediaRecorder.AudioSource.MIC);
        recording.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); //what output format do we want
        recording.setOutputFile(outputFileName);
        recording.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); //encoder?

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recorder.start();

    }

    private void stopRecording() {
        recorder.stop();
        recorder.release(); // free up object for new recording
        recorder = null;
    }

}
