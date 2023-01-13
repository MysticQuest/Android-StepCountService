package com.unityInteration.stepcountservice;

import android.app.Service;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.IBinder;
import androidx.annotation.Nullable;
import android.hardware.Sensor;
import android.hardware.SensorManager;

public class StepCounterService extends Service {

    private SensorManager sensorManager;
    private Sensor stepSensor;
    private int stepCount = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(sensorEventListener, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
        return START_STICKY;
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            stepCount = (int) event.values[0];
            sendStepDataToUnity();
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    private void sendStepDataToUnity() {
        // Use UnitySendMessage() method to send the step count data to Unity
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

