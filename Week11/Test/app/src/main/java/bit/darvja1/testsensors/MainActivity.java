package bit.darvja1.testsensors;

        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager mSensorManager;
    Sensor mLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvOutput = (TextView) findViewById(R.id.tv_output);

        Button btnSensor = (Button) findViewById(R.id.btn_Sensor);
        btnSensor.setOnClickListener(new SensorBtnHandler());

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mLight = (Sensor) mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        String stringBuilder = "";
        for (int i = 0; i < sensorList.size(); i++)
        {
            stringBuilder += sensorList.get(i).toString() + "\n";
        }
        tvOutput.setText(stringBuilder);

    }



    public class SensorBtnHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {

        }
    }

    public class SensorEventHandler implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
        @Override
        protected void onResume() {
            super.onResume();
            mSensorManager.registerListener((SensorEventListener) this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
        @Override
        protected void onPause() {
            super.onPause();
            mSensorManager.unregisterListener(this);
        }

    }



}