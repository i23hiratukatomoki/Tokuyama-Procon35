# この虫なーんだ？  ーハンドサインで虫がわかる!!ー

## 使用技術一覧
・Tensorflow-lite

・androidx

・mediapipe

・jsoup

## What_is_this_insect_application
このディレクトリがアプリケーションの本体です。

環境構築を完了したAndroidStudioを使用して実行することでAndroidスマートフォンにアプリをインストールすることができます。

また、このアプリケーションに含まれる主要な部分について以下で説明します。
### libuvccameraとusbCameraCommon
この二つのディレクトリは外部カメラを使用する際に利用されています。

### ModelUserActivity.kt
このファイルには合計13個のtfliteモデルを使用して撮影した写真に含まれる昆虫を段階的に認識するプログラムが含まれています。

このファイルはMainActivityで以下のようにして使用されています。

val musilabel = name_ja[ModelUser.answer(this, aftercutbitmap)]
    
tfliteモデルはassetフォルダに入っています。

## プロジェクトの構成
<!-- Treeコマンドを使ってディレクトリ構成を記載 -->
<pre>
.
├── README.md
└── What_is_this_insect_application
    ├── app
    │   ├── build
    │   │   └── ...
    │   ├── build.gradle
    │   ├── download_tasks.gradle
    │   ├── libs
    │   │   ├── jsoup-1.18.1.jar
    │   │   └── lucene-gosen-2.1-dev-ipadic.jar
    │   ├── proguard-rules.pro
    │   └── src
    │       ├── androidTest
    │       │   ├── assets
    │       │   │   ├── hand_thumb_up.jpg
    │       │   │   ├── test_video.mp4
    │       │   │   └── text_resource_links.txt
    │       │   └── java
    │       │       └── ...
    │       │           └── GestureRecognizerTest.kt
    │       └── main
    │           ├── AndroidManifest.xml
    │           ├── assets
    │           │   ├── gesture_recognizer.task
    │           │   ├── model1.tflite
    │           │   ├── model2_1.tflite
    │           │   ├── model2_2.tflite
    │           │   ├── model2_3.tflite
    │           │   ├── model2_4.tflite
    │           │   ├── model2_7.tflite
    │           │   ├── model3_3a.tflite
    │           │   ├── model3_3b.tflite
    │           │   ├── model3_3c.tflite
    │           │   ├── model3_3d.tflite
    │           │   ├── model3_3e.tflite
    │           │   ├── model4_3aII.tflite
    │           │   └── model4_3cII.tflite
    │           ├── java
    │           │   └── ...
    │           │       └── gesturerecognizer
    │           │           ├── FuriganaView.java
    │           │           ├── GestureRecognizerHelper.kt
    │           │           ├── HandGesture.kt
    │           │           ├── Hurigana.java
    │           │           ├── IntroduceActivity.kt
    │           │           ├── MainActivity.kt
    │           │           ├── MainViewModel.kt
    │           │           ├── ModelUser.java
    │           │           ├── OverlayView.kt
    │           │           ├── QuadraticOptimizer.java
    │           │           ├── RirekiActivity.kt
    │           │           ├── RirekiAdapter.kt
    │           │           └── fragment
    │           │               └── GestureRecognizerResultsAdapter.kt
    │           └── res
    │               ├── color
    │               │   └── bg_nav_item.xml
    │               ├── drawable
    │               │   ├── ic_baseline_add_24.xml
    │               │   ├── ic_baseline_photo_camera_24.xml
    │               │   ├── ic_baseline_photo_library_24.xml
    │               │   ├── ic_minus.xml
    │               │   ├── ic_plus.xml
    │               │   ├── icn_chevron_up.png
    │               │   └── media_pipe_banner.xml
    │               ├── layout
    │               │   ├── activity_introduce.xml
    │               │   ├── activity_main.xml
    │               │   ├── activity_rireki.xml
    │               │   ├── item_gesture_recognizer_result.xml
    │               │   └── item_rireki.xml
    │               ├── menu
    │               │   └── menu_introduce.xml
    │               ├── mipmap-hdpi
    │               │   ├── ic_konomusi.jpg
    │               │   ├── ic_launcher.png
    │               │   └── ic_launcher_round.png
    │               ├── mipmap-mdpi
    │               │   ├── ic_launcher.png
    │               │   └── ic_launcher_round.png
    │               ├── mipmap-xhdpi
    │               │   ├── ic_launcher.png
    │               │   └── ic_launcher_round.png
    │               ├── mipmap-xxhdpi
    │               │   ├── ic_launcher.png
    │               │   └── ic_launcher_round.png
    │               ├── mipmap-xxxhdpi
    │               │   ├── ic_launcher.png
    │               │   └── ic_launcher_round.png
    │               └── values
    │                   ├── colors.xml
    │                   ├── dimens.xml
    │                   ├── strings.xml
    │                   ├── styles.xml
    │                   └── themes.xml
    ├── build.gradle
    ├── gradle
    │   └── wrapper
    │       ├── gradle-wrapper.jar
    │       └── gradle-wrapper.properties
    ├── gradle.properties
    ├── gradlew
    ├── gradlew.bat
    ├── libuvccamera
    │   └──...
    ├── local.properties
    ├── settings.gradle
    └── usbCameraCommon
        └──...
        
<pre>
