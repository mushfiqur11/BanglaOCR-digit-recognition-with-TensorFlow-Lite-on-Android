package com.nex3z.tflitemnist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nex3z.fingerpaintview.FingerPaintView;

import java.io.IOException;

import butterknife.BindView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShikhoActivity extends AppCompatActivity {

    private static final String LOG_TAG = ShikhoActivity.class.getSimpleName();


    @BindView(R.id.tv_prediction) TextView mTvPrediction;
    @BindView(R.id.tv_probability) TextView mTvProbability;
    @BindView(R.id.tv_timecost) TextView mTvTimeCost;



    Button captureButton;
    private Classifier mClassifier;
    Bitmap captured_image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();

        captureButton = findViewById(R.id.captureButton);

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,0);
            }
        });


    }



    void onDetectClick() {
        if (mClassifier == null) {
            Log.e(LOG_TAG, "onDetectClick(): Classifier is not initialized");
            return;}
//        } else if (mFpvPaint.isEmpty()) {
//            Toast.makeText(this, R.string.please_write_a_digit, Toast.LENGTH_SHORT).show();
//            return;
//        }



//        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.eight);
//        Bitmap scaled = Bitmap.createScaledBitmap(b, 40, 40, true);

        Bitmap captured_image_scaled = Bitmap.createScaledBitmap(captured_image, 40, 40, true);


        Result result = mClassifier.classify(captured_image_scaled);
        renderResult(result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        captured_image = (Bitmap) data.getExtras().get("data");
        onDetectClick();

    }



    private void init() {


        try {

            //mClassifier = new Interpreter(loadModelFile(ShikhoActivity.this,modelFile));
            mClassifier = new Classifier(this);
        } catch (IOException e) {
            Toast.makeText(this, R.string.failed_to_create_classifier, Toast.LENGTH_LONG).show();
            Log.e(LOG_TAG, "init(): Failed to create Classifier", e);
        }


            //mClassifier = new Classifier(this);

    }

    private void renderResult(Result result) {
        mTvPrediction.setText(String.valueOf(result.getNumber()));
        mTvProbability.setText(String.valueOf(result.getProbability()));
        mTvTimeCost.setText(String.format(getString(R.string.timecost_value),
                result.getTimeCost()));


//        mTvTimeCost.setText(String.format(String.valueOf(Classifier.total)));
    }

}
