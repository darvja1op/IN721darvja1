package bit.darvja1.locationteleporter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTeleport = (Button) findViewById(R.id.btnGenerate);
        btnTeleport.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            generateCoordinates();
        }
    }

    public void generateCoordinates(){
        Random rand = new Random();
        double doubleGen;
        int intGen;

        doubleGen = rand.nextDouble();
        intGen = rand.nextInt(180) - 89;
        double latitude = doubleGen + intGen;

        doubleGen = rand.nextDouble();
        intGen = rand.nextInt(360) - 179;
        double longitude = doubleGen + intGen;

        displayCoordinates(latitude, longitude);
    }

    public void displayCoordinates(double latitude, double longitude){
        TextView txtLatitude = (TextView) findViewById(R.id.txtLatitudeDisplay);
        TextView txtLongitude = (TextView) findViewById(R.id.txtLongitudeDisplay);

        txtLatitude.setText(Double.toString((double)Math.round(latitude * 10000d) / 1000d));
        txtLongitude.setText(Double.toString((double)Math.round(longitude * 10000d) / 1000d));
    }
}
