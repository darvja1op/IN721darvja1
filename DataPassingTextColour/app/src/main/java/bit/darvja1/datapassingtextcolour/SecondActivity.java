package bit.darvja1.datapassingtextcolour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView txtView = (TextView) findViewById(R.id.txtColoured);
        int color = txtView.getCurrentTextColor();

        Intent 
    }
}
