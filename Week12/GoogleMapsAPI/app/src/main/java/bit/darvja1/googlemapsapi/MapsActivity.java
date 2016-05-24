package bit.darvja1.googlemapsapi;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    double latitude;
    double longitude;
    LatLng location;
    String cityName;
    String locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new ButtonListener());
        generateCoordinates();
    }

    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            generateCoordinates();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        generateCoordinates();

        mMap.addMarker(new MarkerOptions().position(location).title(locationName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    public void generateCoordinates() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria defaultCriteria = new Criteria();

        String providerName = locationManager.getBestProvider(defaultCriteria, false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Location currentLocation = locationManager.getLastKnownLocation(providerName);
            latitude = currentLocation.getLatitude();
            longitude = currentLocation.getLongitude();
        }
    }

    public void processJSONLocation(String JSONString){
        JSONObject JSON = null;
        try {

            if(JSONString.equals("[[]]")){
                cityName = "No location found.";
            }
            else{
                JSON = new JSONObject(JSONString);
                cityName = JSON.getString("geoplugin_place");
                String countryCode = JSON.getString("geoplugin_countryCode");

                locationName = cityName + ", " + countryCode;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class AsyncAPILocation extends AsyncTask<Void,Void,String> {

        @Override
        protected void onPreExecute(){
        }

        @Override
        protected String doInBackground(Void... arg0) {
            String JSONString = null;

            do{
                generateCoordinates();

                try{
                    String urlString = "http://www.geoplugin.net/extras/location.gp?"+
                            "lat="+latitude+"&long="+longitude+"&format=json";

                    URL URLObject = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

                    connection.connect();
                    int responseCode = connection.getResponseCode();

                    if (responseCode != 200){
                        //no data
                        Toast.makeText(MapsActivity.this, "No Data found", Toast.LENGTH_LONG).show();
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
            } while (JSONString.equals("[[]]"));
            return JSONString;
        }

        protected void onPostExecute(String fetchedString){
            //pass string to appropriate place
            processJSONLocation(fetchedString);
        }
    }
}
