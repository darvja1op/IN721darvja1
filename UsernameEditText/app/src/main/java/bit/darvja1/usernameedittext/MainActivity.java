package bit.darvja1.usernameedittext;

import android.content.DialogInterface;
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

    private class EditTextHandler implements View.OnKeyListener{

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            EditText editText = (EditText)findViewById(v.getId());
            String text = editText.getText().toString();
            if (keyCode == KeyEvent.KEYCODE_ENTER){
                if (text.length() == 8){
                    Toast.makeText(MainActivity.this,"Thank you "+text,Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Usernames must be 8 characters, "+text,Toast.LENGTH_LONG).show();
                }
            }
            return false;
        }
    }
}
