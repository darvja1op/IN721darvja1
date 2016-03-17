package bit.darvja1.datapassingusername;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DataPassing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_passing);

        Button btnStartForResult = (Button) findViewById(R.id.btnSettings);
        btnStartForResult.setOnClickListener(new ButtonClickListener());
    }

    private class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent goToSettings = new Intent(DataPassing.this,SettingsActivity.class);

            startActivityForResult(goToSettings, 0);
        }
    }

    private void setTxtUsername(String result){
        TextView txtUsername = (TextView) findViewById(R.id.txtUsernameDisplay);
        Intent launchIntent = getIntent();

        if(result != null){
            txtUsername.setText(result);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if((requestCode == 0)&&(resultCode == Activity.RESULT_OK)){
            String result = data.getStringExtra("username");

            setTxtUsername(result);
        }
    }
}
