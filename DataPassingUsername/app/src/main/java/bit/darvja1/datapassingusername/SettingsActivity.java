package bit.darvja1.datapassingusername;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new ButtonOnClick());
    }

    private class ButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent goMain = new Intent(SettingsActivity.this,DataPassing.class);

            TextView txtUsername = (TextView) findViewById(R.id.editText);
            String text = txtUsername.getText().toString();
            int length = text.length();
            if(length >= 5){
                goMain.putExtra("username",txtUsername.getText().toString());
                startActivity(goMain);
            }
            else{
                Toast.makeText(SettingsActivity.this,"Please enter a Username greater than 5 characters",Toast.LENGTH_LONG).show();
            }
        }
    }
}
