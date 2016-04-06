package bit.darvja1.loadinglistviewfromfile;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLoad = (Button) findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ArrayList<String> cityArray = new ArrayList<String>();

            String assetFile = "cities.txt";

            AssetManager am = getAssets();

            InputStream is = null;
            try {
                is = am.open(assetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);

            String newCity;
            try {
                while ((newCity = br.readLine()) != null){
                    cityArray.add(newCity);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            loadListView(cityArray);
        }
    }

    private void loadListView(ArrayList<String> citiesArray){
        ListView lvCities = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> lvAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,citiesArray);

        lvCities.setAdapter(lvAdapter);
    }
}
