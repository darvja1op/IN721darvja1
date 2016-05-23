package bit.darvja1.toolbarapplication;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    int rotation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        rotation = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.visible_menu_list, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String itemTitle = (String) item.getTitle();

        switch (itemTitle){
            case "Delete":
                delete();
                break;
            case "Details":
                details();
                break;
            case "Rotate":
                rotate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void rotate() {
        imageView.setRotation(rotation + 90);
    }
    private void delete() {
        imageView.setBackgroundColor(Color.WHITE);
    }
    private void details() {
        Toast.makeText(MainActivity.this,"Thierry Henry",Toast.LENGTH_LONG).show();
    }
}
