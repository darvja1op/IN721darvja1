package bit.darvja1.dunedineventsjson;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFill = (Button) findViewById(R.id.btnFill);
        btnFill.setOnClickListener(new ButtonClick());
    }

    private class ButtonClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            fillList();
        }
    }

    public void fillList(){
        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayList<String> events = getEvents();

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,events);
        listView.setAdapter(adapter);
    }

    public ArrayList<String> getEvents(){
        ArrayList<String> events = new ArrayList<String>();

        String fileName = "dunedin_events.json";

        AssetManager am = getAssets();
        InputStream inputStream = null;
        try {
            inputStream = am.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int length = 0;
        int fileSizeBytes = 0;
        try {
            fileSizeBytes = inputStream.available();
            byte[] JSONBuffer = new byte[fileSizeBytes];
            inputStream.read(JSONBuffer);
            inputStream.close();

            String JSONInput = new String(JSONBuffer);
            JSONObject eventData = null;
            try {
                eventData = new JSONObject(JSONInput);
                JSONObject dataObject = eventData.getJSONObject("events");
                JSONArray eventsArray = dataObject.getJSONArray("event");
                length = eventsArray.length();

                for (int i = 0; i < length; i++){
                    JSONObject event = eventsArray.getJSONObject(i);
                    String title = event.getString("title");
                    events.add(title);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }
}
