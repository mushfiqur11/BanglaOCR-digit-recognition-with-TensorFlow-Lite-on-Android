package com.nex3z.tflitemnist;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nex3z.fingerpaintview.FingerPaintView;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShekhaoActivity extends AppCompatActivity {

    private static final String LOG_TAG = ShekhaoActivity.class.getSimpleName();

    private StorageReference mStorage;
    ProgressDialog progressDialog;

    @BindView(R.id.fpv_paint)
    FingerPaintView mFpvPaint;
    @BindView(R.id.tv_prediction)
    TextView tv_prediction;

    int randomNumber;

    Button btn_submit,btn_clear;
    ImageView testImageView;

    String folderName;

    //Bitmap captured_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shekhao);
        ButterKnife.bind(this);

        Random rand = new Random();
        randomNumber = rand.nextInt(10);
        String randomNumberString = Integer.toString(randomNumber);

        progressDialog = new ProgressDialog(this);

        tv_prediction.setText(randomNumberString);

        btn_clear = findViewById(R.id.btn_clear);
        btn_submit = findViewById(R.id.btn_detect);
        testImageView = findViewById(R.id.testImageView);

        mStorage = FirebaseStorage.getInstance().getReference();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDetectClick();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearClick();
            }
        });
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    @OnClick(R.id.btn_detect)
    void onDetectClick() {




        if (mFpvPaint.isEmpty()) {
            Toast.makeText(this, R.string.please_write_a_digit, Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Uploading");
        progressDialog.show();



//        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.eight);
//        Bitmap scaled = Bitmap.createScaledBitmap(b, 40, 40, true);

//        Bitmap captured_image_scaled = Bitmap.createScaledBitmap(captured_image, 40, 40, true);

        Bitmap image = mFpvPaint.exportToBitmap(
                Classifier.IMG_WIDTH, Classifier.IMG_HEIGHT);

        Uri store_uri = getImageUri(this,image);

        String randomString = getAlphaNumericString(10);

        if(randomNumber==0)
        {
            folderName="zero";
        }

        else if(randomNumber==1)
        {
            folderName="one";
        }

        else if(randomNumber==2)
        {
            folderName="two";
        }

        else if(randomNumber==3)
        {
            folderName="three";
        }

        else if(randomNumber==4)
        {
            folderName="four";
        }

        else if(randomNumber==5)
        {
            folderName="five";
        }

        else if(randomNumber==6)
        {
            folderName="six";
        }

        else if(randomNumber==7)
        {
            folderName="seven";
        }

        else if(randomNumber==8)
        {
            folderName="eight";
        }

        else if(randomNumber==9)
        {
            folderName="nine";
        }


        StorageReference storageReference = mStorage.child("images/"+folderName+"/"+randomString+".jpg");
        storageReference.putFile(store_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //Uri download_uri = taskSnapshot.getDownloadUrl();
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Thank you!",Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }
        });

        //this image will be passed to firebase

        //testImageView.setImageBitmap(image);

    }


    @OnClick(R.id.btn_clear)
    void onClearClick() {
        mFpvPaint.clear();
        //tv_prediction.setText(R.string.empty);

    }



}
