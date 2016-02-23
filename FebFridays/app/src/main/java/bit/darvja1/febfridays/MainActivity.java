package bit.darvja1.febfridays;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String dates = "";

        String text = "February Fridays are on: ";
        TextView txtOnCreateMessage = (TextView) findViewById(R.id.txtOnCreateMessage);

        Resources resourceResolver = getResources();
        int datesArray[] = resourceResolver.getIntArray(R.array.FebFridays);

        for (int i=0; i<datesArray.length; i++){
            dates = dates + datesArray[i] + " ";
        }
        txtOnCreateMessage.setText(text + dates);
    }
}
