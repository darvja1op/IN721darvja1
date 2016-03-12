package bit.darvja1.multiplefragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnImage = (Button) findViewById(R.id.btnImage);
        btnImage.setOnClickListener(new ShowImageButtonOnClickListener());

        Button btnList = (Button) findViewById(R.id.btnListView);
        btnList.setOnClickListener(new ShowListViewButtonOnClickListener());
    }

    private class ShowImageButtonOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Fragment imageFragment = new ShowImageFragment();
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container,imageFragment);
            ft.commit();
        }
    }
    private class ShowListViewButtonOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Fragment listFragment = new ShowListFragment();
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container,listFragment);
            ft.commit();
        }
    }
}
