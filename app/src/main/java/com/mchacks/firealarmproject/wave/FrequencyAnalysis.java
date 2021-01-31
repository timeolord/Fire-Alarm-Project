package com.mchacks.firealarmproject.wave;

import com.mchacks.firealarmproject.math.Complex;
import com.mchacks.firealarmproject.math.FFT;

public class FrequencyAnalysis {
    //  3500 Hz and 85 dB(A)
    public static final int FFT_SIZE = 1024;
    double MAX_FFT;
    int PEAK;


    public double[] calculateFFT (byte [] audioInput) {
        Complex[] complexSignal = new Complex[FFT_SIZE];
        double[] absSignal = new double[FFT_SIZE/2];

        // adding to complex
        double tmp;
        for(int i = 0; i < FFT_SIZE; i++){
            tmp = (double)((audioInput[2*i] & 0xFF) | (audioInput[2*i+1] << 8)) / 32768.0F;
            complexSignal[i] = new Complex(tmp,0.0); // real, img
        }

        // FFT
        Complex[] yFreq;
        yFreq = FFT.fft(complexSignal);

        // adding to abs
        MAX_FFT = 0.0;
        PEAK = 0;
        for(int i = 0; i < (FFT_SIZE/2); i++)
        {
            absSignal[i] = Math.sqrt(Math.pow(yFreq[i].re(), 2) + Math.pow(yFreq[i].im(), 2));
            if(absSignal[i] > MAX_FFT)
            {
                MAX_FFT = absSignal[i];
                PEAK = i;
            }
        }

        return absSignal;





    }




}
