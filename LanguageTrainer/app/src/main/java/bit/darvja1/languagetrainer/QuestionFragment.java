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

    View fragmentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_question, container, false);

        return fragmentView;
    }

    public void changeEnglishText(String english){
        TextView txtEnglish = (TextView) fragmentView.findViewById(R.id.txtEnglish);
        txtEnglish.setText(english);
    }

    public void changeGermanText(String german){
        TextView txtGerman = (TextView) fragmentView.findViewById(R.id.txtGerman);
        txtGerman.setText(german);
    }

    public void changeImage(int resourceID){
        ImageView imgView = (ImageView) fragmentView.findViewById(R.id.imgView);
        imgView.setImageResource(resourceID);
    }
}
