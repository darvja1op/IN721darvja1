package bit.darvja1.explodedimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.ExplodeAnimation;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new ButtonListener());

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            new ExplodeAnimation(imageView)
                    .setExplodeMatrix(ExplodeAnimation.MATRIX_2X2)
                    .setInterpolator(new DecelerateInterpolator())
                    .setDuration(500)
                    .setListener(new AnimationListener() {
                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }
                    })
                    .animate();
        }
    }
}
