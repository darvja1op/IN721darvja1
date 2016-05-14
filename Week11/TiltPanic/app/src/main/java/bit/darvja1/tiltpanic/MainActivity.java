package bit.darvja1.tiltpanic;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SensorManager mSensorManager;
    Sensor mAccelerometer;
    SensorListener mSensorHandler;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorHandler = new SensorListener();

        mButton = (Button) findViewById(R.id.btnCatch);

        mButton.setOnClickListener(new ButtonListener());
    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(mSensorHandler, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(mSensorHandler);
    }

    public class SensorListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {
            TextView txtX = (TextView) findViewById(R.id.txtX);
            TextView txtY = (TextView) findViewById(R.id.txtY);
            TextView txtHorizontal = (TextView) findViewById(R.id.txtHorizontal);
            TextView txtVertical = (TextView) findViewById(R.id.txtVertical);

            float x = event.values[0];
            float y = event.values[1];

            //get button location
            float buttonX = mButton.getX();
            float buttonY = mButton.getY();

            txtX.setText("X: " + x);
            txtY.setText("Y: " + y);

            //get border values
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

            //if on edge of screen don't move
            if ((buttonX == 0)||(buttonX > width)){

            }


            //if somewhat flat don't change or edge of screen
            if((x < -1)||(x > 1)){
                //if x positive move button left
                if (x > 0){
                    mButton.setX(buttonX-5);
                    txtHorizontal.setText("Left");
                }
                //else move right
                else{
                    mButton.setX(buttonX+5);
                    txtHorizontal.setText("Right");
                }
            }

            if((y < -1)||(y > 1)||(buttonY == 0)||(buttonY > height)){
                //if y positive move up
                if (y > 0){
                    mButton.setY(buttonY+5);
                    txtVertical.setText("Up");
                }
                else{
                    mButton.setY(buttonY-5);
                    txtVertical.setText("Down");
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    public class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "You got me!", Toast.LENGTH_SHORT).show();
        }
    }
}
