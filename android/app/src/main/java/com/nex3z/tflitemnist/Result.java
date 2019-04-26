package com.nex3z.tflitemnist;

public class Result {

    private final int mNumber1,mNumber2,mNumber;
    private final float mProbability;
    private final long mTimeCost;

    public Result(float[] probs1, float[] probs2, long timeCost) {
        mNumber1 = argmax(probs1);
        mNumber2 = argmax(probs2);
        if(mNumber1==mNumber2){
            mNumber = mNumber1;
        }
        else{
            mNumber = -1;
        }
        mProbability = (probs1[mNumber1]*probs2[mNumber2]);
        mTimeCost = timeCost;
    }

    // first test commit
    //kire mama

    public int getNumber() {
        return mNumber;
    }
    public int alternate1(){
        return mNumber1;
    }
    public int alternate2(){
        return mNumber2;
    }

    public float getProbability() {
        if(mNumber>=0)
            return mProbability;
        else
            return 0;
    }

    public long getTimeCost() {
        return mTimeCost;
    }

    private static int argmax(float[] probs) {
        int maxIdx = -1;
        float maxProb = 0.0f;
        for (int i = 0; i < probs.length; i++) {
            if (probs[i] > maxProb) {
                maxProb = probs[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}
