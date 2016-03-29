package bit.darvja1.languagetrainer;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_question, container, false);

        return fragmentView;
    }

    public void changeEnglishText(String english){
        TextView txtEnglish = (TextView) getView().findViewById(R.id.txtEnglish);
        txtEnglish.setText(english);
    }

    public void changeGermanText(String german){
        TextView txtGerman = (TextView) getView().findViewById(R.id.txtGerman);
        txtGerman.setText(german);
    }

    public void changeImage(int resourceID){
        ImageView imgView = (ImageView) getView().findViewById(R.id.imgView);
        imgView.setImageResource(resourceID);
    }
}
