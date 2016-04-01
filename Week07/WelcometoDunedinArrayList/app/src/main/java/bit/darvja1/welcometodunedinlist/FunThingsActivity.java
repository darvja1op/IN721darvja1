package bit.darvja1.welcometodunedinlist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class FunThingsActivity extends AppCompatActivity {
    private FunThing[] funthings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_things);

        initialiseArray();

        CustomArrayAdapter customAdapter = new CustomArrayAdapter(this,R.layout.custom_list_view,funthings);

        ListView lvFunThings = (ListView) findViewById(R.id.lvFunThings);
        lvFunThings.setAdapter(customAdapter);
    }


    public class CustomArrayAdapter extends ArrayAdapter<FunThing> {

        public CustomArrayAdapter(Context context, int resource, FunThing[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container){
            LayoutInflater inflater = LayoutInflater.from(FunThingsActivity.this);

            View customView = inflater.inflate(R.layout.custom_list_view, container, false);

            ImageView ivList = (ImageView) customView.findViewById(R.id.ivListView);
            TextView tvList = (TextView) customView.findViewById(R.id.tvListView);

            FunThing currentItem = getItem(position);

            ivList.setImageDrawable(currentItem.image);
            tvList.setText(currentItem.name);

            return customView;
        }
    }

    public void initialiseArray() {
        Resources resourcesMachine = getResources();

        Drawable moanaPoolImage = resourcesMachine.getDrawable(R.drawable.moana_pool, null);
        Drawable larnachCastleImage = resourcesMachine.getDrawable(R.drawable.larnach_castle,null);
        Drawable octagonImage = resourcesMachine.getDrawable(R.drawable.octagon,null);
        Drawable olvestonImage = resourcesMachine.getDrawable(R.drawable.olveston,null);
        Drawable peninsulaImage = resourcesMachine.getDrawable(R.drawable.peninsula,null);
        Drawable saltPoolImage = resourcesMachine.getDrawable(R.drawable.salt_water_pool,null);
        Drawable speightsImage = resourcesMachine.getDrawable(R.drawable.speights_brewery,null);
        Drawable stKildaImage = resourcesMachine.getDrawable(R.drawable.st_kilda_beach,null);
        Drawable taeriImage = resourcesMachine.getDrawable(R.drawable.taeri_gorge_railway,null);
        Drawable monarchImage = resourcesMachine.getDrawable(R.drawable.monarch,null);

        funthings = new FunThing[10];

        funthings[0] = new FunThing("Moana Pool",moanaPoolImage);
        funthings[1] = new FunThing("Larnach Castle",larnachCastleImage);
        funthings[2] = new FunThing("Octagon",octagonImage);
        funthings[3] = new FunThing("Olveston",olvestonImage);
        funthings[4] = new FunThing("Peninsula",peninsulaImage);
        funthings[5] = new FunThing("Salt Water Pools",saltPoolImage);
        funthings[6] = new FunThing("Speight's Brewery",speightsImage);
        funthings[7] = new FunThing("St Kilda Beach",stKildaImage);
        funthings[8] = new FunThing("Taeri Gorge Railway",taeriImage);
        funthings[9] = new FunThing("Monarch Cruise",monarchImage);
    }
}
