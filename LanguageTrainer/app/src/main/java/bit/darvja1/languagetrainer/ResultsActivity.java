package bit.darvja1.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Button btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new ButtonClick());

        TextView txtResult = (TextView) findViewById(R.id.txtResultScore);

        Intent launchIntent = getIntent();
        int score = launchIntent.getIntExtra("score",0);

        txtResult.setText(Integer.toString(score));
    }

    private class ButtonClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //exit program
            finish();
        }
    }
}
