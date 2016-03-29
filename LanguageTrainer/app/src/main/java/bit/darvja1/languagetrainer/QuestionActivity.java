package bit.darvja1.languagetrainer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    Manager manager;
    Question[] shuffledQuestions;
    Question[] questions;
    int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        manager = new Manager();
        questions = manager.createQuestions();
        shuffledQuestions = manager.shuffleQuestions(questions);

        Button btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new ButtonClick());

        displayQuestion();
    }

    private class ButtonClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            count++;
            displayQuestion();
        }
    }

    public void displayQuestion(){
        QuestionFragment questionFragment = new QuestionFragment();
        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_question,questionFragment);
        ft.commit();

        questionFragment.changeEnglishText(shuffledQuestions[count].getEnglish());
        questionFragment.changeGermanText(shuffledQuestions[count].getNoun());
        questionFragment.changeImage(shuffledQuestions[count].getImgPath());
    }
}
