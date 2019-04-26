package com.nex3z.tflitemnist;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class Classifier {
    private static final String LOG_TAG = Classifier.class.getSimpleName();

<<<<<<< HEAD
//    private static final String MODEL_NAME = "mnistbangla2.tflite";

    private static String MODEL_NAME = "mnistbangla2.tflite";
=======
    private static final String MODEL_NAME_1 = "mnistbangla2.tflite";
    private static final String MODEL_NAME_2 = "mnistbangla.tflite";
//    private static final String MODEL_NAME_3 = "mnistbangla2.tflite";
>>>>>>> bbf8263052447cb0cc6952171a1c172279efa453

    private static final int BATCH_SIZE = 1;
    public static final int IMG_HEIGHT = 40;
    public static final int IMG_WIDTH = 40;
    private static final int NUM_CHANNEL = 1;
    private static final int NUM_CLASSES = 10;
    public static float total =0;

    private final Interpreter.Options options = new Interpreter.Options();
    private final Interpreter mInterpreter1;
    private final Interpreter mInterpreter2;
    private final ByteBuffer mImageData;
    private final int[] mImagePixels = new int[IMG_HEIGHT * IMG_WIDTH];
    private final float[][] mResult1 = new float[1][NUM_CLASSES];
    private final float[][] mResult2 = new float[1][NUM_CLASSES];

<<<<<<< HEAD
    public Classifier(Activity activity,String MODEL_NAMES) throws IOException {
        MODEL_NAME = MODEL_NAMES;
        mInterpreter = new Interpreter(loadModelFile(activity), options);
=======
    public Classifier(Activity activity) throws IOException {
        mInterpreter1 = new Interpreter(loadModelFile1(activity), options);
        mInterpreter2 = new Interpreter(loadModelFile2(activity), options);
>>>>>>> bbf8263052447cb0cc6952171a1c172279efa453
        mImageData = ByteBuffer.allocateDirect(
                4 * BATCH_SIZE * IMG_HEIGHT * IMG_WIDTH * NUM_CHANNEL);
        mImageData.order(ByteOrder.nativeOrder());
    }

    public Result classify(Bitmap bitmap) {
        convertBitmapToByteBuffer(bitmap);
        long startTime = SystemClock.uptimeMillis();
        mInterpreter1.run(mImageData, mResult1);
        mInterpreter2.run(mImageData, mResult2);
        long endTime = SystemClock.uptimeMillis();
        long timeCost = endTime - startTime;
        Log.v(LOG_TAG, "classify(): result = " + Arrays.toString(mResult1[0])
                + ", timeCost = " + timeCost);
        return new Result(mResult1[0],mResult2[0], timeCost);
    }

    private MappedByteBuffer loadModelFile1(Activity activity) throws IOException {
        AssetFileDescriptor fileDescriptor = activity.getAssets().openFd(MODEL_NAME_1);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }
    private MappedByteBuffer loadModelFile2(Activity activity) throws IOException {
        AssetFileDescriptor fileDescriptor = activity.getAssets().openFd(MODEL_NAME_2);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }
//    private void convertBitmapToByteBuffer(Bitmap bitmap) {
//        if (mImageData == null) {
//            return;
//        }
//        mImageData.rewind();
//
//        bitmap.getPixels(mImagePixels, 0, bitmap.getWidth(), 0, 0,
//                bitmap.getWidth(), bitmap.getHeight());
//        //total=0;
//        int pixel = 0;
//        for (int i = 0; i < IMG_WIDTH; ++i) {
//            for (int j = 0; j < IMG_HEIGHT; ++j) {
//                int value = mImagePixels[pixel++];
//                mImageData.putFloat(convertPixel(value));
//                //total = total + convertPixel(value);
//            }
//        }
//
//    }

//    private static float convertPixel(int color) {
//        return ((((color >> 16) & 0xFF) * 0.299f
//                + ((color >> 8) & 0xFF) * 0.587f
//                + (color & 0xFF) * 0.114f)) / 255.0f;
//    }
    private void convertBitmapToByteBuffer(Bitmap bitmap) {
        if (mImageData == null) {
            return;
        }
        mImageData.rewind();
        total=0;
        bitmap.getPixels(mImagePixels, 0, bitmap.getWidth(), 0, 0,
                bitmap.getWidth(), bitmap.getHeight());
        int pixel = 0;
        for (int i = 0; i < IMG_WIDTH; ++i) {
            for (int j = 0; j < IMG_HEIGHT; ++j) {
                int value = mImagePixels[pixel++];
                //mImageData.putFloat(convertPixelWhite(value));
                total = total + convertPixelWhite(value);
            }
        }
        bitmap.getPixels(mImagePixels, 0, bitmap.getWidth(), 0, 0,
                bitmap.getWidth(), bitmap.getHeight());
        pixel=0;
        if(total>800) {
            for (int i = 0; i < IMG_WIDTH; ++i) {
                for (int j = 0; j < IMG_HEIGHT; ++j) {
                    int value = mImagePixels[pixel++];
                    mImageData.putFloat(convertPixelWhite(value));

                }
            }
        }
        else{
            for (int i = 0; i < IMG_WIDTH; ++i) {
                for (int j = 0; j < IMG_HEIGHT; ++j) {
                    int value = mImagePixels[pixel++];
                    mImageData.putFloat(convertPixelBlack(value));

                }
            }
        }
    }



    private static float convertPixelWhite(int color) {
        float color2 = ((((color >> 16) & 0xFF) * 0.299f
                + ((color >> 8) & 0xFF) * 0.587f
                + (color & 0xFF) * 0.114f)) / 255.0f;
        if(color2 < 0.6f)
            return 0.0f;
        else
            return 1.0f;
    }
    private static float convertPixelBlack(int color) {
        float color2 = (255.0f - (((color >> 16) & 0xFF) * 0.299f
                + ((color >> 8) & 0xFF) * 0.587f
                + (color & 0xFF) * 0.114f)) / 255.0f;
        if(color2 < 0.6f)
            return 0.0f;
        else
            return 1.0f;
    }
}
