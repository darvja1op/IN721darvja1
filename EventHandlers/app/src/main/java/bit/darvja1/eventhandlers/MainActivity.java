package bit.darvja1.eventhandlers;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ButtonChangeDisplay = (Button) findViewById(R.id.button);
        ButtonChangeDisplayClickHandler clickHandler = new ButtonChangeDisplayClickHandler();
        ButtonChangeDisplayLongClickHandler longClickHandler = new ButtonChangeDisplayLongClickHandler();

        ButtonChangeDisplay.setOnClickListener(clickHandler);
        ButtonChangeDisplay.setOnLongClickListener(longClickHandler);
    }

    public class ButtonChangeDisplayClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this,"You clicked the Button!",Toast.LENGTH_LONG).show();
        }
    }

    public class ButtonChangeDisplayLongClickHandler implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(MainActivity.this,"You long clicked the Button!",Toast.LENGTH_LONG).show();
            return true;
        }
    }
}
