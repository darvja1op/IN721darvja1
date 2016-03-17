package bit.darvja1.datapassingtextcolour;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button btnChange = (Button) findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new ButtonClickListener());
    }

    private class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent changeActivity = new Intent(StartActivity.this,SecondActivity.class);

            startActivityForResult(changeActivity,0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int returnCode,Intent data){
        if ((returnCode == Activity.RESULT_OK)&&(requestCode == 0)){
            int colour = data.getIntExtra("colour",0);

            TextView txtView = (TextView) findViewById(R.id.txtMockingbird);
            txtView.setTextColor(colour);
        }
    }
}
