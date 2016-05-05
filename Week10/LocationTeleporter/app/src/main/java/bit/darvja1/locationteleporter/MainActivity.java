package bit.darvja1.locationteleporter;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    double latitude;
    double longitude;
    String location;

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
        latitude = doubleGen + intGen;

        doubleGen = rand.nextDouble();
        intGen = rand.nextInt(360) - 179;
        longitude = doubleGen + intGen;

        displayCoordinates();
    }

    public void displayCoordinates(){
        TextView txtLatitude = (TextView) findViewById(R.id.txtLatitudeDisplay);
        TextView txtLongitude = (TextView) findViewById(R.id.txtLongitudeDisplay);

        txtLatitude.setText(Double.toString((double)Math.round(latitude * 10000d) / 1000d));
        txtLongitude.setText(Double.toString((double) Math.round(longitude * 10000d) / 1000d));
    }

    public void processJSON(String JSONString){
        JSONObject JSON = null;
        try {
            JSON = new JSONObject(JSONString);
            String name = JSON.getString("geoplugin_place");
            String countryCode = JSON.getString("geoplugin_countryCode");

            location = name + ", " + countryCode;

            displayCoordinates();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class AsyncAPI extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute(){
        }

        @Override
        protected String doInBackground(String... artist) {
            String JSONString = null;

            try{
                String urlString = "http://www.geoplugin.net/extras/location.gp?"+
                                    "lat="+latitude+"&long="+longitude+"&format=json";

                URL URLObject = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

                connection.connect();
                int responseCode = connection.getResponseCode();

                if (responseCode != 200){
                    //no data
                    Toast.makeText(MainActivity.this, "No Data found", Toast.LENGTH_LONG).show();
                }

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String responseString;
                StringBuilder stringBuilder = new StringBuilder();
                while ((responseString = bufferedReader.readLine()) != null){
                    stringBuilder = stringBuilder.append(responseString);
                }

                JSONString = stringBuilder.toString();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return JSONString;
        }

        protected void onPostExecute(String fetchedString){
            //pass string to appropriate place
            processJSON(fetchedString);
        }
    }
}
