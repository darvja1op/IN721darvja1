package bit.darvja1.lastfmhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> artists;

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
        getArtists();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,artists);
    }

    public void getArtists(){



    }

    private class HTTPrequest extends HttpURLConnection{

        @Override
        public void disconnect() {

        }

        @Override
        public boolean usingProxy() {
            return false;
        }

        @Override
        public void connect() throws IOException {

        }
    }
}
