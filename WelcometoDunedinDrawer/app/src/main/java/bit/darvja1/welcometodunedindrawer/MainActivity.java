package bit.darvja1.welcometodunedindrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListViewItems();

        ListView lvGroups = (ListView) findViewById(R.id.lvGroups);
        lvGroups.setOnItemClickListener(new ListViewItem());
    }

    public void setListViewItems(){
        String[] array = {"Services","Fun Things To Do","Dining","Shopping"};

        ArrayAdapter<String> groupAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,array);
        ListView dunedinItems = (ListView) findViewById(R.id.lvGroups);

        dunedinItems.setAdapter(groupAdapter);
    }

    public class ListViewItem implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String clickedItem = (String) parent.getItemAtPosition(position).toString();
            Intent goIntent;

            switch (clickedItem){
                case "Dining":
                    Log.d("Log","Log Dining");
                    goIntent = new Intent(MainActivity.this,DiningActivity.class);
                    break;
                case "Fun Things To Do":
                    Log.d("Log","Log Fun Things");
                    goIntent = new Intent(MainActivity.this,FunThingsActivity.class);
                    break;
                case "Services":
                    Log.d("Log","Log Services");
                    goIntent = new Intent(MainActivity.this,ServicesActivity.class);
                    break;
                case "Shopping":
                    Log.d("Log","Log Shopping");
                    goIntent = new Intent(MainActivity.this,ShoppingActivity.class);
                    break;
                default:
                    goIntent = null;
                    break;
            }
            if(goIntent != null){
                startActivity(goIntent);
            }
        }
    }
}
