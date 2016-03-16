package bit.darvja1.datapassingusername;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DataPassing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_passing);

        Button btnSettings = (Button) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new ButtonClickListener());

        setTxtUsername();
    }

    private class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent goToSettings = new Intent(DataPassing.this,SettingsActivity.class);

            startActivity(goToSettings);
        }
    }

    private void setTxtUsername(){
        TextView txtUsername = (TextView) findViewById(R.id.editText);
        Intent launchIntent = getIntent();
        String text = launchIntent.getStringExtra("username");
        if(text != null){
            txtUsername.setText(text);
        }
    }
}
