package com.mchacks.firealarmproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private EditText editTextInput;
    public static String fileName = null;
    private static boolean on = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Audio file
        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/audiorecordtest.3gp";

        //ActivityCompat.requestPermissions(this, permAudio, REQUEST_RECORD_AUDIO_PERMISSION);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }

        // This is the description
        TextView description = (TextView) findViewById(R.id.textView);
        String s = "This app is designed to notify the hearing impaired/deaf of nearby  fire alarms though flashing screen and vibration of the phone.\n\n" +
                "There is also an optional feature under the settings page that sends a text to emergency contacts when during the fire alarm.";
        description.setText(s);

        // This is the settings button functionality
        Button settings = (Button) findViewById(R.id.settingsID);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent set = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(set);
            }
        });

        // This is the test run/actual run functionality
        Button testRun = (Button) findViewById(R.id.testrunID);
        testRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testR = new Intent(getApplicationContext(), TestRunActivity.class);
                startActivity(testR);
            }
        });

        editTextInput = findViewById(R.id.edit_text_input);

        // FFT analysis
        // byte [] audioByteArray = AudioProcessor.convertToByteArray(fileName);
        // double audioFFT = FrequencyAnalysis.calculateFFT(audioByteArray);



        startService(findViewById(R.id.testrunID));
    }

    public void toggleService(View v){
        if (!on){
            on = true;
            startService(v);
        }
        else {
            on = false;
            stopService(v);
        }
    }

    public void startService(View v){
        String input = editTextInput.getText().toString();

        Intent serviceIntent = new Intent(this, AudioProcessor.class);
        serviceIntent.putExtra("Input Extra", input);

        startService(serviceIntent);
    }

    public void stopService(View v){
        Intent serviceIntent = new Intent(this, AudioProcessor.class);
        stopService(serviceIntent);
    }

}