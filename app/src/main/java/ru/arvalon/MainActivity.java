package ru.arvalon;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.admin.sensor.R;

import java.util.List;

/**
 * Example of work with sensor
 * @author arvalon
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "sensor.tag";

    SensorManager manager;

    private Sensor accel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);

        for(Sensor s : sensors)
        {
            Log.d(TAG, "Sensor: "+s.toString());
        }

        // List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        accel = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();

        manager.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();

        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d(TAG, "onSensorChanged "+event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG,"onAccuracyChanged "+accuracy);
    }
}
