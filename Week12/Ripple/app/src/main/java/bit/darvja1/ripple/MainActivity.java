package bit.darvja1.ripple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.skyfishjy.library.RippleBackground;

public class MainActivity extends AppCompatActivity {

    RippleBackground background;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        background = (RippleBackground) findViewById(R.id.content);

        button.setOnClickListener(new ButtonListener());
        count = 0;
    }

    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            count++;
            if(count % 2 == 0){
                background.stopRippleAnimation();
            }
            else{
                background.startRippleAnimation();
            }

        }
    }
}
