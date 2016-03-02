package bit.darvja1.multipleactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        TextView txtActivityIdentifier = (TextView) findViewById(R.id.txtActivityIdentifier);

        txtActivityIdentifier.setText("Activity B");

        Button btnNext = (Button) findViewById(R.id.btnNext);
        btnOnClickHandler handler = new btnOnClickHandler();
        btnNext.setOnClickListener(handler);
    }

    private class btnOnClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(ActivityB.this,ActivityC.class);
            startActivity(changeActivityIntent);
        }
    }
}
