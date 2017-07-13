package com.example.gokulkrishnam.proxsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView textView;
    int timer;
    SensorManager mSensorManager;
    Sensor mSensor;
    MediaPlayer mediaPlayer;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.textvalue);
        mediaPlayer= MediaPlayer.create(this,R.raw.alarmsound);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (mSensor == null){
            Toast.makeText(this,"No Proximity Sensor Found! ",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values=event.values;
        int compatevalue=Double.compare(values[0],5.0);
        if(compatevalue>0) {
            if(flag>0)
               mediaPlayer.pause();
            textView.setText("far");
        }
        else {
            flag++;
            mediaPlayer.start();
            textView.setText("near");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }




}
