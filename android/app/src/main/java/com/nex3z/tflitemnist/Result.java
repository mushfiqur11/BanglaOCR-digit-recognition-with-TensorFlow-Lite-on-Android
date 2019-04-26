package com.nex3z.tflitemnist;

public class Result {

    private final int mNumber1,mNumber2,mNumber3,mNumber;
    private final float mProbability;
    private final long mTimeCost;
    private final int flag;

    public Result(float[] probs1, float[] probs2,float[] probs3, long timeCost) {
        mNumber1 = argmax(probs1);
        mNumber2 = argmax(probs2);
        mNumber3 = argmax(probs3);
//        mProbability = 0;
        if(mNumber1==mNumber2 && mNumber1 == mNumber3){
            mNumber = mNumber1;
            flag=0;
            mProbability = (probs1[mNumber1]/3) + (probs2[mNumber2]/3) + (probs3[mNumber3]/3);
        }
        else if(mNumber2 == mNumber3){
            mNumber = mNumber2;
            flag=1;
            mProbability = (probs2[mNumber2]/3) + (probs3[mNumber3]/3);
        }
        else if(mNumber1 == mNumber3){
            mNumber = mNumber1;
            flag=2;
            mProbability = (probs1[mNumber1]/3)+ (probs3[mNumber3]/3);
        }
        else if(mNumber1 == mNumber2){
            mNumber = mNumber1;
            flag=3;
            mProbability = (probs1[mNumber1]/3) + (probs2[mNumber2]/3);
        }
        else{
            mNumber = -1;
            flag=4;
            mProbability = 0;
        }

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
    public int alternate3(){
        return mNumber3;
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
