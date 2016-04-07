package bit.darvja1.loadingfromsql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase dbCities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create database and populate with data
        createDatabase();
        //fetch current list of countries from database
        ArrayList<String> countries = getCountriesSQL();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countries);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerListener());
    }

    private class SpinnerListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String country = parent.getItemAtPosition(position).toString();

            populateCities(country);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void populateCities(String country){
        //populate array from SQL
        ArrayList<String> cities = getCitiesSQL(country);

        ListView lvCities = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cities);

        lvCities.setAdapter(adapter);
    }

    public void createDatabase(){
        dbCities = openOrCreateDatabase("dbCountries",MODE_PRIVATE,null);

        String dropQuery = "DROP TABLE IF EXISTS tblCities;";
        dbCities.execSQL(dropQuery);

        String createQuery = "CREATE TABLE IF NOT EXISTS tblCities("+
                                "cityID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                "cityName TEXT NOT NULL, "+
                                "countryName TEXT NOT NULL);";
        dbCities.execSQL(createQuery);

        addRecord("Dunedin", "New Zealand");
        addRecord("Wanaka","New Zealand");
        addRecord("Christchurch","New Zealand");
        addRecord("Madrid","Spain");
        addRecord("Barcelona","Spain");
        addRecord("Paris","France");
        addRecord("Lyon","France");
        addRecord("Marsailles","France");
        addRecord("Brisbane","Australia");
        addRecord("Berlin", "Germany");
    }

    public void addRecord(String name, String country){
        String addQuery = "INSERT INTO tblCities VALUES (null,'"+name+"','"+country+"');";
        dbCities.execSQL(addQuery);
    }

    public ArrayList<String> getCitiesSQL(String country){
        String selectQuery = "SELECT cityName FROM tblCities WHERE countryName LIKE '" + country + "'";
        Cursor recordSet = dbCities.rawQuery(selectQuery,null);

        int count = recordSet.getCount();
        int cityNameIndex = recordSet.getColumnIndex("cityName");
        recordSet.moveToFirst();

        ArrayList<String> cities = new ArrayList<String>();

        for (int i=0;i<count;i++){
            cities.add(recordSet.getString(cityNameIndex));
            recordSet.moveToNext();
        }

        return cities;
    }

    public ArrayList<String> getCountriesSQL(){
        String selectQuery = "SELECT DISTINCT(countryName) FROM tblCities;";
        Cursor recordSet = dbCities.rawQuery(selectQuery,null);

        int count = recordSet.getCount();
        int countryNameIndex = recordSet.getColumnIndex("countryName");
        recordSet.moveToFirst();

        ArrayList<String> countries = new ArrayList<String>();

        for (int i=0;i<count;i++){
            countries.add(recordSet.getString(countryNameIndex));
            recordSet.moveToNext();
        }

        return countries;
    }
}
