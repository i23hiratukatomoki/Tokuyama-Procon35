package com.google.mediapipe.examples.gesturerecognizer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.util.Log;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class ModelUser {
    //推論用モデル
    private static Interpreter tfLiteModel = null;
    //受け取る画像(Bitmap形式)
    private static Bitmap bitmap;
    //モデル推論結果保存配列
    private static float[] result;
    //識別対象の画像をBitmap形式で引数として受け取る。
    //画像認識を行うためのメソッド(昆虫の種類を番号で返す。)
    public static int answer(Context context, Bitmap image) {
        // 引数として取得したBitmap型の画像をプライベート変数のbitmapにコピー
        bitmap = image;
        System.out.println("Successes:loading bitmap");
        return using_model1(context);
    }

    private static int using_model1(Context context) {
        try {
            // 最初に使用する推論モデル(model1)の読み込み
            tfLiteModel = new Interpreter(loadModelFile(context, "model1.tflite"));
            System.out.println("Successes:loading model1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ans = -1;
        float most = -1;
        //推論メソッドを使っている。(resultに結果を格納)
        try {
            // model1は29クラスなのでclasses_num=29で推論を実行
            result = runInference(29);
            System.out.println("model1 推論成功");
            Log.d("Inference", "Prediction model1: " + Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        resultには各番号(虫の種類)ごとにスコアがあり、高いほど一致している。なので一番高いスコアの種類がどれかを識別している。
        ただし、どの虫のスコアも1.0以下であった場合(虫が存在しない場合)ansは-1となりreturnの値は0となる。
        */
        for (int i = 0; i < result.length; i++) {
            if(most == -1) {
                most=result[i];
                ans=i;
            } else if(most < result[i]) {
                most=result[i];
                ans=i;
            }
        }


        switch(ans+1){
            case 1://アメンボ
            case 7://カマキリ
            case 24://ナナフシ
            case 27://バッタ
                return using_model2_1(context);
            case 2://アリ
            case 25://ハサミムシ
            case 26://ハチ
                return using_model2_2(context);
            case 3://オサムシ
            case 6://カブトムシ
            case 8://カミキリムシ
            case 9://カメムシ
            case 12://クワガタムシ
            case 13://ゲンゴロウ
            case 14://コガネムシ
            case 15://ゴキブリ
            case 16://セミ
            case 17://ゾウムシ
            case 18://タガメ
            case 19://タマムシ
            case 22://テントウムシ
            case 28://ハンミョウ
            case 29://ホタル
                return using_model2_3(context);
            case 5://カゲロウ
            case 10://カワゲラ
            case 23://トンボ
                return using_model2_4(context);
            case 4://ガ
            case 21://チョウ
                return using_model2_7(context);
            default://クモ、ダンゴムシまたは虫を認識できなかった場合
                return ans+1;

        }
    }

    private static int using_model2_1(Context context) {
        try {
            // 使用する推論モデル(model2_1)の読み込み
            tfLiteModel = new Interpreter(loadModelFile(context, "model2_1.tflite"));
            System.out.println("Successes:loading model2_1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ans = -1;
        float most = -1;
        //推論メソッドを使っている。(resultに結果を格納)
        try {
            // model2_1は4クラスなのでclasses_num=4で推論を実行
            result = runInference(4);
            System.out.println("model2_1 推論成功");
            Log.d("Inference", "Prediction model2_1: " + Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        resultには各番号(虫の種類)ごとにスコアがあり、高いほど一致している。なので一番高いスコアの種類がどれかを識別している。
        ただし、どの虫のスコアも1.0以下であった場合(虫が存在しない場合)ansは-1となりreturnの値は0となる。
        */
        for (int i = 0; i < result.length; i++) {
            if(most == -1) {
                most=result[i];
                ans=i;
            } else if(most < result[i]) {
                most=result[i];
                ans=i;
            }
        }

        switch (ans) {
            case 0://アメンボ
                return 1;
            case 1://カマキリ
                return 7;
            case 2://ナナフシ
                return 24;
            case 3://バッタ
                return 27;
            default://虫を認識できなかった場合
                return 0;
        }
    }

    private static int using_model2_2(Context context) {
        try {
            // 使用する推論モデル(model2_2)の読み込み
            tfLiteModel = new Interpreter(loadModelFile(context, "model2_2.tflite"));
            System.out.println("Successes:loading model2_2");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ans = -1;
        float most = -1;
        //推論メソッドを使っている。(resultに結果を格納)
        try {
            // model2_2は3クラスなのでclasses_num=3で推論を実行
            result = runInference(3);
            System.out.println("model2_2 推論成功");
            Log.d("Inference", "Prediction model2_2: " + Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        resultには各番号(虫の種類)ごとにスコアがあり、高いほど一致している。なので一番高いスコアの種類がどれかを識別している。
        ただし、どの虫のスコアも1.0以下であった場合(虫が存在しない場合)ansは-1となりreturnの値は0となる。
        */
        for (int i = 0; i < result.length; i++) {
            if(most == -1) {
                most=result[i];
                ans=i;
            } else if(most < result[i]) {
                most=result[i];
                ans=i;
            }
        }

        switch (ans) {
            case 0://アリ
                return 2;
            case 1://ハサミムシ
                return 25;
            case 2://ハチ
                return 26;
            default://虫を認識できなかった場合
                return 0;
        }
    }

    private static int using_model2_3(Context context) {
        try {
            // 使用する推論モデル(model2_3)の読み込み
            tfLiteModel = new Interpreter(loadModelFile(context, "model2_3.tflite"));
            System.out.println("Successes:loading model2_3");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ans = -1;
        float most = -1;
        //推論メソッドを使っている。(resultに結果を格納)
        try {
            // model2_3は15クラスなのでclasses_num=15で推論を実行
            result = runInference(15);
            System.out.println("model2_3 推論成功");
            Log.d("Inference", "Prediction model2_3: " + Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        resultには各番号(虫の種類)ごとにスコアがあり、高いほど一致している。なので一番高いスコアの種類がどれかを識別している。
        ただし、どの虫のスコアも1.0以下であった場合(虫が存在しない場合)ansは-1となりreturnの値は0となる。
        */
        for (int i = 0; i < result.length; i++) {
            if(most == -1) {
                most=result[i];
                ans=i;
            } else if(most < result[i]) {
                most=result[i];
                ans=i;
            }
        }

        switch (ans) {
            case 0://オサムシ
            case 1://カミキリムシ
            case 2://タマムシ
            case 3://ハンミョウ
            case 4://ホタル
                return using_model3_3a(context);
            case 5://カメムシ
            case 6://タガメ
                return using_model3_3b(context);
            case 7://クワガタムシ
            case 8://カブトムシ
            case 9://コガネムシ
            case 10://ゾウムシ
                return using_model3_3c(context);
            case 11://ゲンゴロウ
            case 12://テントウムシ
                return using_model3_3d(context);
            case 13://ゴキブリ
            case 14://セミ
                return using_model3_3e(context);
            default://虫を認識できなかった場合
                return 0;
        }
    }

    private static int using_model2_4(Context context) {
        try {
            // 使用する推論モデル(model2_4)の読み込み
            tfLiteModel = new Interpreter(loadModelFile(context, "model2_4.tflite"));
            System.out.println("Successes:loading model2_4");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ans = -1;
        float most = -1;
        //推論メソッドを使っている。(resultに結果を格納)
        try {
            // model2_4は3クラスなのでclasses_num=3で推論を実行
            result = runInference(3);
            System.out.println("model2_4 推論成功");
            Log.d("Inference", "Prediction model2_4: " + Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        resultには各番号(虫の種類)ごとにスコアがあり、高いほど一致している。なので一番高いスコアの種類がどれかを識別している。
        ただし、どの虫のスコアも1.0以下であった場合(虫が存在しない場合)ansは-1となりreturnの値は0となる。
        */
        for (int i = 0; i < result.length; i++) {
            if(most == -1) {
                most=result[i];
                ans=i;
            } else if(most < result[i]) {
                most=result[i];
                ans=i;
            }
        }

        switch (ans) {
            case 0://カゲロウ
                return 5;
            case 1://カワゲラ
                return 10;
            case 2://トンボ
                return 23;
            default://虫を認識できなかった場合
                return 0;
        }
    }

    private static int using_model2_7(Context context) {
        try {
            // 使用する推論モデル(model2_7)の読み込み
            tfLiteModel = new Interpreter(loadModelFile(context, "model2_7.tflite"));
            System.out.println("Successes:loading model2_7");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ans = -1;
        float most = -1;
        //推論メソッドを使っている。(resultに結果を格納)
        try {
            // model2_7は2クラスなのでclasses_num=2で推論を実行
            result = runInference(2);
            System.out.println("model2_7 推論成功");
            Log.d("Inference", "Prediction model2_7: " + Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        resultには各番号(虫の種類)ごとにスコアがあり、高いほど一致している。なので一番高いスコアの種類がどれかを識別している。
        ただし、どの虫のスコアも1.0以下であった場合(虫が存在しない場合)ansは-1となりreturnの値は0となる。
        */
        for (int i = 0; i < result.length; i++) {
            if(most == -1) {
                most=result[i];
                ans=i;
            } else if(most < result[i]) {
                most=result[i];
                ans=i;
            }
        }

        switch (ans) {
            case 0://チョウ
                return 21;
            case 1://ガ
                return 4;
            default://虫を認識できなかった場合
                return 0;
        }
    }

    private static int using_model3_3a(Context context) {
        try {
            // 使用する推論モデル(model3_3a)の読み込み
            tfLiteModel = new Interpreter(loadModelFile(context, "model3_3a.tflite"));
            System.out.println("Successes:loading model3_3a");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ans = -1;
        float most = -1;
        //推論メソッドを使っている。(resultに結果を格納)
        try {
            // model3_3aは5クラスなのでclasses_num=5で推論を実行
            result = runInference(5);
            System.out.println("model3_3a 推論成功");
            Log.d("Inference", "Prediction model3_3a: " + Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        resultには各番号(虫の種類)ごとにスコアがあり、高いほど一致している。なので一番高いスコアの種類がどれかを識別している。
        ただし、どの虫のスコアも1.0以下であった場合(虫が存在しない場合)ansは-1となりreturnの値は0となる。
        */
        for (int i = 0; i < result.length; i++) {
            if(most == -1) {
                most=result[i];
                ans=i;
            } else if(most < result[i]) {
                most=result[i];
                ans=i;
            }
        }

        switch(ans){
            case 0://オサムシ
                return 3;
            case 1://カミキリムシ
            case 2://タマムシ
            case 3://ハンミョウ
                return using_model4_3aII(context);
            case 4://ホタル
                return 29;
            default://虫を認識できなかった場合
                return 0;
        }
    }

    private static int using_model3_3b(Context context) {
        try {
            // 使用する推論モデル(model3_3b)の読み込み
            tfLiteModel = new Interpreter(loadModelFile(context, "model3_3b.tflite"));
            System.out.println("Successes:loading model3_3b");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ans = -1;
        float most = -1;
        //推論メソッドを使っている。(resultに結果を格納)
        try {
            // model3_3bは2クラスなのでclasses_num=2で推論を実行
            result = runInference(2);
            System.out.println("model3_3b 推論成功");
            Log.d("Inference", "Prediction model3_3b: " + Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        resultには各番号(虫の種類)ごとにスコアがあり、高いほど一致している。なので一番高いスコアの種類がどれかを識別している。
        ただし、どの虫のスコアも1.0以下であった場合(虫が存在しない場合)ansは-1となりreturnの値は0となる。
        */
        for (int i = 0; i < result.length; i++) {
            if(most == -1) {
                most=result[i];
                ans=i;
            } else if(most < result[i]) {
                most=result[i];
                ans=i;
            }
        }

        switch (ans) {
            case 0://カメムシ
                return 9;
            case 1://タガメ
                return 18;
            default://虫を認識できなかった場合
                return 0;
        }
    }

    private static int using_model3_3c(Context context) {
        try {
            // 使用する推論モデル(model3_3c)の読み込み
            tfLiteModel = new Interpreter(loadModelFile(context, "model3_3c.tflite"));
            System.out.println("Successes:loading model3_3c");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ans = -1;
        float most = -1;
        //推論メソッドを使っている。(resultに結果を格納)
        try {
            // model3_3aは4クラスなのでclasses_num=4で推論を実行
            result = runInference(4);
            System.out.println("model3_3c 推論成功");
            Log.d("Inference", "Prediction model3_3c: " + Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        resultには各番号(虫の種類)ごとにスコアがあり、高いほど一致している。なので一番高いスコアの種類がどれかを識別している。
        ただし、どの虫のスコアも1.0以下であった場合(虫が存在しない場合)ansは-1となりreturnの値は0となる。
        */
        for (int i = 0; i < result.length; i++) {
            if(most == -1) {
                most=result[i];
                ans=i;
            } else if(most < result[i]) {
                most=result[i];
                ans=i;
            }
        }

        switch (ans) {
            case 0://クワガタムシ
                return 12;
            case 1://カブトムシ
            case 2://コガネムシ
            case 3://ゾウムシ
                return using_model4_3cII(context);
            default://虫を認識できなかった場合
                return 0;
        }
    }

    private static int using_model3_3d(Context context) {
        try {
            // 使用する推論モデル(model3_3d)の読み込み
            tfLiteModel = new Interpreter(loadModelFile(context, "model3_3d.tflite"));
            System.out.println("Successes:loading model3_3d");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ans = -1;
        float most = -1;
        //推論メソッドを使っている。(resultに結果を格納)
        try {
            // model3_3dは2クラスなのでclasses_num=2で推論を実行
            result = runInference(2);
            System.out.println("model3_3d 推論成功");
            Log.d("Inference", "Prediction model3_3d: " + Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        resultには各番号(虫の種類)ごとにスコアがあり、高いほど一致している。なので一番高いスコアの種類がどれかを識別している。
        ただし、どの虫のスコアも1.0以下であった場合(虫が存在しない場合)ansは-1となりreturnの値は0となる。
        */
        for (int i = 0; i < result.length; i++) {
            if(most == -1) {
                most=result[i];
                ans=i;
            } else if(most < result[i]) {
                most=result[i];
                ans=i;
            }
        }

        switch(ans){
            case 0://ゲンゴロウ
                return 13;
            case 1://テントウムシ
                return 22;
            default://虫を認識できなかった場合
                return 0;
        }
    }

    private static int using_model3_3e(Context context) {
        try {
            // 使用する推論モデル(model3_3e)の読み込み
            tfLiteModel = new Interpreter(loadModelFile(context, "model3_3e.tflite"));
            System.out.println("Successes:loading model3_3e");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ans = -1;
        float most = -1;
        //推論メソッドを使っている。(resultに結果を格納)
        try {
            // model3_3eは2クラスなのでclasses_num=2で推論を実行
            result = runInference(2);
            System.out.println("model3_3e 推論成功");
            Log.d("Inference", "Prediction model3_3e: " + Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        resultには各番号(虫の種類)ごとにスコアがあり、高いほど一致している。なので一番高いスコアの種類がどれかを識別している。
        ただし、どの虫のスコアも1.0以下であった場合(虫が存在しない場合)ansは-1となりreturnの値は0となる。
        */
        for (int i = 0; i < result.length; i++) {
            if(most == -1) {
                most=result[i];
                ans=i;
            } else if(most < result[i]) {
                most=result[i];
                ans=i;
            }
        }

        switch (ans){
            case 0://ゴキブリ
                return 15;
            case 1://セミ
                return 16;
            default://虫を認識できなかった場合
                return 0;
        }
    }

    private static int using_model4_3aII(Context context) {
        try {
            // 使用する推論モデル(model4_3aII)の読み込み
            tfLiteModel = new Interpreter(loadModelFile(context, "model4_3aII.tflite"));
            System.out.println("Successes:loading model4_3aII");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ans = -1;
        float most = -1;
        //推論メソッドを使っている。(resultに結果を格納)
        try {
            // model4_3aIIは3クラスなのでclasses_num=3で推論を実行
            result = runInference(3);
            System.out.println("model4_3aII 推論成功");
            Log.d("Inference", "Prediction model4_3aII: " + Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        resultには各番号(虫の種類)ごとにスコアがあり、高いほど一致している。なので一番高いスコアの種類がどれかを識別している。
        ただし、どの虫のスコアも1.0以下であった場合(虫が存在しない場合)ansは-1となりreturnの値は0となる。
        */
        for (int i = 0; i < result.length; i++) {
            if(most == -1) {
                most=result[i];
                ans=i;
            } else if(most < result[i]) {
                most=result[i];
                ans=i;
            }
        }

        switch (ans) {
            case 0://カミキリムシ
                return 8;
            case 1://タマムシ
                return 19;
            case 2://ハンミョウ
                return 28;
            default://虫を認識できなかった場合
                return 0;
        }
    }

    private static int using_model4_3cII(Context context) {
        try {
            // 使用する推論モデル(model4_3cII)の読み込み
            tfLiteModel = new Interpreter(loadModelFile(context, "model4_3cII.tflite"));
            System.out.println("Successes:loading model4_3cII");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ans = -1;
        float most = -1;
        //推論メソッドを使っている。(resultに結果を格納)
        try {
            // model4_3cIIは3クラスなのでclasses_num=3で推論を実行
            result = runInference(3);
            System.out.println("model4_3cII 推論成功");
            Log.d("Inference", "Prediction model4_3cII: " + Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        resultには各番号(虫の種類)ごとにスコアがあり、高いほど一致している。なので一番高いスコアの種類がどれかを識別している。
        ただし、どの虫のスコアも1.0以下であった場合(虫が存在しない場合)ansは-1となりreturnの値は0となる。
        */
        for (int i = 0; i < result.length; i++) {
            if(most == -1) {
                most=result[i];
                ans=i;
            } else if(most < result[i]) {
                most=result[i];
                ans=i;
            }
        }

        switch (ans) {
            case 0://カブトムシ
                return 6;
            case 1://コガネムシ
                return 14;
            case 2://ゾウムシ
                return 17;
            default://虫を認識できなかった場合
                return 0;
        }
    }

    //　モデル名を引数として読み込み、モデルを読み込むメソッド
    private static MappedByteBuffer loadModelFile(Context context, String filename) throws IOException {
        AssetFileDescriptor fileDescriptor = context.getAssets().openFd(filename);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long length = fileDescriptor.getLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, length);
    }

    //TensorImage型に変換されたjpgファイルに対し推論の実行を行い、float[]型の配列を返す
    private static float[] runInference(int classes_num) {
        TensorImage tensorImage = preprocessImage(bitmap);
        //引数のclasses_numでクラス数を指定
        TensorBuffer outputBuffer = TensorBuffer.createFixedSize(new int[]{1, classes_num}, DataType.FLOAT32);

        Log.d("ModelHelper", "Output Tensor Shape: " + Arrays.toString(outputBuffer.getShape()));

        Log.d("ModelHelper", "Output Tensor Data Type: " + outputBuffer.getDataType());

        tfLiteModel.run(tensorImage.getBuffer(), outputBuffer.getBuffer().rewind());

        return outputBuffer.getFloatArray();
    }

    //画像認識のためにBitmap型の写真をTensorImage型に変換
    private static TensorImage preprocessImage(Bitmap bitmap) {
        TensorImage tensorImage = new TensorImage(DataType.FLOAT32);
        tensorImage.load(bitmap);

        // モデルに合わせた画像処理
        ImageProcessor imageProcessor = new ImageProcessor.Builder()
                .add(new ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
                .add(new NormalizeOp(0.0f, 255.0f))  // ピクセル値を0-1に正規化
                .build();
        tensorImage = imageProcessor.process(tensorImage);
        return tensorImage;
    }
}
