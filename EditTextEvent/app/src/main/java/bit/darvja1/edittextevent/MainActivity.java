package bit.darvja1.edittextevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText EditText = (EditText) findViewById(R.id.editText);
        EditTextHandler handler = new EditTextHandler();

        EditText.setOnKeyListener(handler);
    }

    public class EditTextHandler implements View.OnKeyListener{

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_AT){
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    Toast.makeText(MainActivity.this,"Don't type @",Toast.LENGTH_LONG).show();
                }
            }
            return false;
        }
    }
}
