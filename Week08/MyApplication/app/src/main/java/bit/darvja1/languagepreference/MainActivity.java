package bit.darvja1.languagepreference;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;

    TextView txtHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSetPreferences = (Button) findViewById(R.id.btnSetPreferences);
        btnSetPreferences.setOnClickListener(new ButtonListener());

        prefs = getSharedPreferences("languagePrefs", MODE_PRIVATE);
        prefsEditor = prefs.edit();

        String languagePrefs = prefs.getString("language",null);
        if(languagePrefs != null){
            txtHello = (TextView) findViewById(R.id.txtHello);
            txtHello.setText(getGreeting(languagePrefs));
        }

        int colourPrefs = prefs.getInt("colour", 1);
        if(colourPrefs != 1){
            txtHello = (TextView) findViewById(R.id.txtHello);
            txtHello.setTextColor(colourPrefs);
        }
    }

    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            RadioGroup languageGroup = (RadioGroup) findViewById(R.id.rdoGroupLanguage);
            int rdoBtnLangId = languageGroup.getCheckedRadioButtonId();

            if(rdoBtnLangId == -1){
                Toast.makeText(MainActivity.this,"Please select a Language",Toast.LENGTH_LONG).show();
            }
            else{
                RadioButton rdoChecked = (RadioButton) findViewById(rdoBtnLangId);
                prefsEditor.putString("language",rdoChecked.getText().toString());
                prefsEditor.commit();

                String languagePrefs = prefs.getString("language", null);
                String greeting = getGreeting(languagePrefs);

                txtHello.setText(greeting);
            }

            RadioGroup colourGroup = (RadioGroup) findViewById(R.id.rdoGroupColour);
            int rdoBtnColourId = languageGroup.getCheckedRadioButtonId();

            if(rdoBtnColourId == -1){
                Toast.makeText(MainActivity.this,"Please select a Language",Toast.LENGTH_LONG).show();
            }
            else{
                RadioButton rdoChecked = (RadioButton) findViewById(rdoBtnColourId);
                prefsEditor.putInt("colour",rdoChecked.getCurrentTextColor());
                prefsEditor.commit();

                int colourPrefs = prefs.getInt("colour",1);
                txtHello.setTextColor(colourPrefs);
            }
        }
    }

    private String getGreeting(String language){
        String greeting = "";

        switch(language){
            case "German":
                greeting = "Guten tag";
                break;
            case "French":
                greeting = "Salut";
                break;
            case "English":
                greeting = "Hello";
                break;
            default:
                break;
        }

        return greeting;
    }
}
