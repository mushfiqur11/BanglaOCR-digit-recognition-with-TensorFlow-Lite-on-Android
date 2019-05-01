# BanglaOCR(digit recognition) with TensorFlow Lite on Android

[![Open In Colab](https://colab.research.google.com/assets/colab-badge.svg)](https://colab.research.google.com/github/nex3z/tflite-mnist-android/blob/master/model.ipynb)

This project was built as a lab task for the software development corse (CSE 4510) in IUT (Islamic University of Technology). The project uses [TensorFlow Lite](https://www.tensorflow.org/lite) on Android for Bangla handwritten digits classification.
Download the apk of the app from the [drive link](https://drive.google.com/open?id=17fkdUrzHzOzHqe6fHyGHx39Nl3GFUVoG). 
A detailed report on the app is available [here](https://drive.google.com/open?id=1P7wIzQh38fIubXCW74GiRJSmGIBxPwI8).
<!--
    <div align="center">
      <img src="image/demo.gif" heigit="500"/>
    </div>
!-->


## Contributors
- Md. Mushfiqur Rahman (160041011)[Gitlab](https://gitlab.com/mushfiqur11)
- Kazi Raiyan Mahmud (160041058)
- Minhajul Islam (160041061)

### Environment
- OpenCV for data manipulation
- Python 3.6, TensorFlow 1.13.1 for model creation
- Tensorflow Lite to make the model deployable to Android
- Android Studio to make the Android application


## Credits
- The dataset used for training the model was prepared by the students. It was merged with dataset downloaded from [numta-dataset](https://github.com/BengaliAI/Numta)
- The basic model architecture comes from [tensorflow-mnist-tutorial](https://github.com/GoogleCloudPlatform/tensorflow-without-a-phd/tree/master/tensorflow-mnist-tutorial).
- The official TensorFlow Lite [examples](https://github.com/tensorflow/examples/tree/master/lite/examples).
- The [FingerPaint](https://android.googlesource.com/platform/development/+/master/samples/ApiDemos/src/com/example/android/apis/graphics/FingerPaint.java) from Android API demo.
