package bit.darvja1.usingcameramosiac;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String photoFileName;
    File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private File createUniqueFile(){
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File imageStorageDirectory = new File(imageRootPath, "CameraDemoMosiac");
        if (!imageStorageDirectory.exists()){
            imageStorageDirectory.mkdirs();
        }

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        photoFileName = "DEMO_" + timeStamp +".jpg";

        File photoFileTemp = new File(imageStorageDirectory.getPath() + File.separator + photoFileName);
        return photoFileTemp;
    }

    private void launchCamera(){
        photoFile = createUniqueFile();

        Uri photoFileUri = Uri.fromFile(photoFile);

        Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoFileUri);

        startActivityForResult(imageCaptureIntent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1){
            if  (resultCode == RESULT_OK){
                String realFilePath = photoFile.getPath();

                Bitmap photoBitmap = BitmapFactory.decodeFile(realFilePath);

                //change image views to be bitmap
                setImageViews(photoBitmap);
            }
            else{
                Toast.makeText(this,"No photo saved. Something went wrong",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void setImageViews(Bitmap image){
        ImageView img1 = (ImageView) findViewById(R.id.imageView);
        ImageView img2 = (ImageView) findViewById(R.id.imageView2);
        ImageView img3 = (ImageView) findViewById(R.id.imageView3);
        ImageView img4 = (ImageView) findViewById(R.id.imageView4);

        ImageView[] imageViews = {img1, img2, img3, img4};

        for (int i = 0; i <imageViews.length;i++){
            imageViews[i].setImageBitmap(image);
        }
    }
}
