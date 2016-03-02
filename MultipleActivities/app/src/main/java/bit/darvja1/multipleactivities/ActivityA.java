package bit.darvja1.multipleactivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        TextView txtActivityIdentifier = (TextView) findViewById(R.id.txtActivityIdentifier);

        Button btnNext = (Button) findViewById(R.id.btnNext);
        btnOnClickHandler handler = new btnOnClickHandler();

        btnNext.setOnClickListener(handler);
    }

    private class btnOnClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(ActivityA.this,ActivityB.class);
            startActivity(changeActivityIntent);
        }
    }
}
