package bit.darvja1.MusicSchool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set button listener
        Button btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new ButtonClickListener());

        //define string array for spinner. Could be as a string resource
        String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};

        //set layout for spinner
        Spinner spnMonths = (Spinner) findViewById(R.id.spnMonth);
        int layoutID = R.layout.spinner_item;

        //create adapter and set adapter
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this,layoutID,months);
        spnMonths.setAdapter(monthAdapter);
    }

    public class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //get reference to controls
            TextView txtMessage = (TextView)findViewById(R.id.txtMessage);
            RadioGroup rdoGroup = (RadioGroup) findViewById(R.id.rdoGroup);
            Spinner spnMonth = (Spinner) findViewById(R.id.spnMonth);
            RadioButton rdoButton = (RadioButton) findViewById(rdoGroup.getCheckedRadioButtonId());

            //get strings from chosen controls
            String chosenInstrument = rdoButton.getText().toString();
            String chosenMonth = spnMonth.getSelectedItem().toString();

            //display message
            String feedbackText = "You have enrolled for " + chosenInstrument + " lessons in " + chosenMonth;
            txtMessage.setText(feedbackText);
        }
    }
}
