package com.nex3z.tflitemnist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nex3z.fingerpaintview.FingerPaintView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShekhaoActivity extends AppCompatActivity {

    private static final String LOG_TAG = ShekhaoActivity.class.getSimpleName();

    @BindView(R.id.fpv_paint)
    FingerPaintView mFpvPaint;
    @BindView(R.id.tv_prediction)
    TextView tv_prediction;

    Button btn_submit,btn_clear;
    ImageView testImageView;

    //Bitmap captured_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shekhao);
        ButterKnife.bind(this);

        btn_clear = findViewById(R.id.btn_clear);
        btn_submit = findViewById(R.id.btn_detect);
        testImageView = findViewById(R.id.testImageView);

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

    @OnClick(R.id.btn_detect)
    void onDetectClick() {


        if (mFpvPaint.isEmpty()) {
            Toast.makeText(this, R.string.please_write_a_digit, Toast.LENGTH_SHORT).show();
            return;
        }



//        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.eight);
//        Bitmap scaled = Bitmap.createScaledBitmap(b, 40, 40, true);

//        Bitmap captured_image_scaled = Bitmap.createScaledBitmap(captured_image, 40, 40, true);

        Bitmap image = mFpvPaint.exportToBitmap(
                Classifier.IMG_WIDTH, Classifier.IMG_HEIGHT);

        //this image will be passed to firebase

        testImageView.setImageBitmap(image);

    }


    @OnClick(R.id.btn_clear)
    void onClearClick() {
        mFpvPaint.clear();
        tv_prediction.setText(R.string.empty);

    }



}
