package bit.darvja1.dunedineventsjson;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> events;
    ArrayList<String> descriptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        events = new ArrayList<String>();
        descriptions = new ArrayList<String>();

        Button btnFill = (Button) findViewById(R.id.btnFill);
        btnFill.setOnClickListener(new ButtonClick());

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new ItemClick());
    }

    private class ButtonClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            fillList();
        }
    }

    private class ItemClick implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String description = descriptions.get(position).toString();
            //String description = parent.getItemAtPosition(position).toString();
            Toast.makeText(MainActivity.this,description,Toast.LENGTH_LONG).show();
        }
    }

    public void fillList(){
        getEvents();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,events);
        listView.setAdapter(adapter);
    }

    public void getEvents(){
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

                    String description = event.getString("description");
                    descriptions.add(description);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
