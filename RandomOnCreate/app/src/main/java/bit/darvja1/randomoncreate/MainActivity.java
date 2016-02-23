package bit.darvja1.randomoncreate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView randomTextView = (TextView) findViewById(R.id.randomTextView);

        randomTextView.setText(setRandomText());
    }

    private String setRandomText(){
        Random random = new Random();

        String dogBreed = "";

        int randNum = random.nextInt(4);

        switch(randNum) {
            case 0:
                dogBreed = "Poodle";
                break;
            case 1:
                dogBreed = "Labrador";
                break;
            case 2:
                dogBreed = "Shar Pei";
                break;
            case 3:
                dogBreed = "Newfoundland";
                break;
        }
        return dogBreed;
    }
}
