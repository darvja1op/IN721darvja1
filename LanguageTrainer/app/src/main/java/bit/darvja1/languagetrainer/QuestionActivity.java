package bit.darvja1.languagetrainer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuestionActivity extends AppCompatActivity {

    Manager manager;
    Question[] shuffledQuestions;
    Question[] questions;
    int questionNumber;
    int userScore;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        manager = new Manager();
        questions = manager.createQuestions();
        shuffledQuestions = manager.shuffleQuestions(questions);

        userScore = 0;

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new ButtonClick());

        questionNumber = 0;
        displayQuestion();
    }

    private class ButtonClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            RadioGroup rdoGroup = (RadioGroup) findViewById(R.id.rdoGroup);
            if (rdoGroup.getCheckedRadioButtonId() == -1){
                Toast.makeText(QuestionActivity.this,"Please select an Article",Toast.LENGTH_LONG).show();
            }
            else{
                questionNumber++;
                checkQuestion(rdoGroup);
                displayQuestion();
            }
        }
    }

    public void displayQuestion(){
        RadioGroup rdoGroup = (RadioGroup) findViewById(R.id.rdoGroup);
        rdoGroup.clearCheck();
        TextView txtEnglish = (TextView) findViewById(R.id.txtEnglish);
        TextView txtGerman = (TextView) findViewById(R.id.txtGerman);
        ImageView imageView = (ImageView) findViewById(R.id.imgView);
        TextView txtNumber = (TextView) findViewById(R.id.txtNumber);

        txtNumber.setText(Integer.toString(questionNumber+1));
        txtEnglish.setText(shuffledQuestions[questionNumber].getEnglish());
        txtGerman.setText(shuffledQuestions[questionNumber].getNoun());
        imageView.setImageResource(shuffledQuestions[questionNumber].getImgPath());
    }

    public void checkQuestion(RadioGroup rdoGroup){
        RadioButton checkedButton = (RadioButton) findViewById(rdoGroup.getCheckedRadioButtonId());
        FragmentManager fm = getFragmentManager();
        if(shuffledQuestions[questionNumber].getArticle().equals(checkedButton.toString())){
            CorrectDialog alertDialog = new CorrectDialog();
            alertDialog.show(fm,"correct");
            userScore++;
        }
        else{
            IncorrectDialog alertDialog = new IncorrectDialog();
            alertDialog.show(fm,"incorrect");
        }

        if(questionNumber == 10){
            btnNext.setText("Check Results!");
            Intent launchResult = new Intent(QuestionActivity.this,ResultsActivity.class);
            launchResult.putExtra("score",userScore);
            startActivity(launchResult);
        }
    }
}
