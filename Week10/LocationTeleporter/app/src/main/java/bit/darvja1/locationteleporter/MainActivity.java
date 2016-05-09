package bit.darvja1.locationteleporter;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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
    ProgressDialog progress;

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
            AsyncAPILocation APIThread = new AsyncAPILocation();
            APIThread.execute();
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
    }

    public void displayCoordinates(){
        TextView txtLatitude = (TextView) findViewById(R.id.txtLatitudeDisplay);
        TextView txtLongitude = (TextView) findViewById(R.id.txtLongitudeDisplay);
        TextView txtLocation = (TextView) findViewById(R.id.txtCityDisplay);

        txtLatitude.setText(Double.toString((double)Math.round(latitude * 10000d) / 1000d));
        txtLongitude.setText(Double.toString((double) Math.round(longitude * 10000d) / 1000d));

        txtLocation.setText(location);

        AsyncAPIFlickr APIThread = new AsyncAPIFlickr();
        APIThread.execute();
    }

    public void processJSONLocation(String JSONString){
        JSONObject JSON = null;
        try {

            if(JSONString.equals("[[]]")){
                location = "No location found.";
            }
            else{
                JSON = new JSONObject(JSONString);
                String name = JSON.getString("geoplugin_place");
                String countryCode = JSON.getString("geoplugin_countryCode");

                location = name + ", " + countryCode;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class AsyncAPILocation extends AsyncTask<Void,Void,String> {

        @Override
        protected void onPreExecute(){
            progress = ProgressDialog.show(MainActivity.this,"Search","Finding a Location...",true);
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
            } while (JSONString.equals("[[]]"));
            return JSONString;
        }

        protected void onPostExecute(String fetchedString){
            //pass string to appropriate place
            progress.dismiss();
            processJSONLocation(fetchedString);
            displayCoordinates();
        }
    }

    private class AsyncAPIFlickr extends AsyncTask<Void,Void,String> {

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(MainActivity.this,"Search","Finding an Image...",true);
        }

        @Override
        protected String doInBackground(Void... arg0) {
            String JSONString = null;
            try {
                String urlString = "https://api.flickr.com/services/rest/?method=flickr.photos.search&" +
                        "api_key=eda41a123d459be0f85276d37290651e" +
                        "&text=" + location + "&format=json&nojsoncallback=1";

                URL URLObject = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

                connection.connect();
                int responseCode = connection.getResponseCode();

                if (responseCode != 200) {
                    //no data
                    Toast.makeText(MainActivity.this, "No Data found", Toast.LENGTH_LONG).show();
                }

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String responseString;
                StringBuilder stringBuilder = new StringBuilder();
                while ((responseString = bufferedReader.readLine()) != null) {
                    stringBuilder = stringBuilder.append(responseString);
                }

                JSONString = stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return JSONString;
        }


        protected void onPostExecute(String fetchedString) {
            //pass string to appropriate place
            progress.dismiss();
            processJSONFlickr(fetchedString);
        }
    }

        public void processJSONFlickr(String JSONString) {
            JSONObject JSON = null;
            Log.d("TAG", JSONString);
            Random rand = new Random();
            ImageView image = (ImageView) findViewById(R.id.imageView);
            try {
                JSON = new JSONObject(JSONString);
                JSONObject photos = JSON.getJSONObject("photos");
                int total = photos.getInt("total");
                JSONArray photoArray = photos.getJSONArray("photo");

                if (total == 0) {
                    //no image found
                    TextView txtNoImage = (TextView) findViewById(R.id.txtNoImage);
                    txtNoImage.setText("No Image found.");
                } else {
                    int randomSlot = rand.nextInt(total);
                    JSONObject photo = photoArray.getJSONObject(randomSlot);

                    //get relevant info to construct URL
                    String id = photo.getString("id");
                    String serverID = photo.getString("server");
                    int farmID = photo.getInt("farm");
                    String secret = photo.getString("secret");

                    String URL = "https://farm"+farmID+".staticflickr.com/"+serverID+
                                "/"+id+"_"+secret+".jpg";

                    image.setImageDrawable(LoadImageFromWebOperations(URL));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    public static Drawable LoadImageFromWebOperations(String url){
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "Flickr");
            return d;
        }
        catch (Exception e){
            return null;
        }
    }
}
