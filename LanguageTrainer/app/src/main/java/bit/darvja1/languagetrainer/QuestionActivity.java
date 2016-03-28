package bit.darvja1.languagetrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    TextView txtEnglish = (TextView) findViewById(R.id.txtEnglish);
    TextView txtGerman = (TextView) findViewById(R.id.txtGerman);

    Button btnNext = (Button) findViewById(R.id.btnNext);


    RadioGroup rdoGroup = (RadioGroup) findViewById(R.id.rdoGroup);
    RadioButton rdoBtnDie = (RadioButton) findViewById(R.id.rdoBtnDie);
    RadioButton rdoBtnDas = (RadioButton) findViewById(R.id.rdoBtnDas);
    RadioButton rdoBtnDer = (RadioButton) findViewById(R.id.rdoBtnDer);

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Manager manager = new Manager();
        Question[] questions = manager.createQuestions();
        manager.shuffleQuestions(questions);

        populateQuestion(questions);
    }

    public void populateQuestion(Question[] questions){
        txtEnglish.setText(questions[count].getEnglish());
    }

    private class ButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            count++;
            populateQuestion(questions);
        }
    }
}
