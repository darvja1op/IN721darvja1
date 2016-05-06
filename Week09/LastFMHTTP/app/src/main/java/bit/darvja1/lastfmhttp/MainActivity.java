package bit.darvja1.lastfmhttp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> artistsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFill = (Button) findViewById(R.id.btnFill);
        btnFill.setOnClickListener(new ButtonClick());

        artistsList = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.listView);
    }

    private class ButtonClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            AsyncAPI APIThread = new AsyncAPI();
            APIThread.execute();
        }
    }

    public void fillList(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.custom_list,artistsList);
        listView.setAdapter(adapter);
    }

    public void processJSON(String JSONString){
        JSONObject JSON = null;
        try {
            JSON = new JSONObject(JSONString);
            JSONObject object = JSON.getJSONObject("artists");

            JSONArray artists = object.getJSONArray("artist");

            int arrayLength = artists.length();

            for (int i = 0; i<arrayLength; i++){
                JSONObject artist = artists.getJSONObject(i);
                String name = artist.getString("name");
                int playcount = artist.getInt("playcount");

                String text = name + " " + playcount;

                artistsList.add(text);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        fillList();
    }

    private class AsyncAPI extends AsyncTask<Void,Void,String>{

        @Override
        protected void onPreExecute(){
        }

        @Override
        protected String doInBackground(Void... arg0) {
            String JSONString = null;

            try{
                String urlString = "http://ws.audioscrobbler.com/2.0/"+
                        "?method=chart.getTopArtists&"+
                        "api_key=58384a2141a4b9737eacb9d0989b8a8c&limit=20&format=json";

                URL URLObject = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

                connection.connect();
                int responseCode = connection.getResponseCode();

                if (responseCode != 200){
                    //no data
                    Toast.makeText(MainActivity.this,"No Data found",Toast.LENGTH_LONG);
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

        public class CustomArrayAdapter extends ArrayAdapter<String>{

            public CustomArrayAdapter(Context context, int resource) {
                super(context, resource);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup container){
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);

                View customView = inflater.inflate(R.layout.custom_list, container, false);

                TextView txtView = (TextView) customView.findViewById(R.id.textView);
                TextView txtView2 = (TextView) customView.findViewById(R.id.textView2);
                return customView;
            }
        }
    }
}
