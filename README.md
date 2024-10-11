# この虫なーんだ？  ーハンドサインで虫がわかる!!ー

## 使用技術一覧
<!-- シールド一覧 -->
<!-- 該当するプロジェクトの中から任意のものを選ぶ-->
<p style="display: inline">
  <img src="https://www.tensorflow.org/images/tf_logo_horizontal.png">
</p>

## What_is_this_insect_applicationについて
このディレクトリがアプリケーションの本体です。

環境構築を完了したAndroidStudioを使用して実行することでAndroidスマートフォンにアプリをインストールすることができます。

また、このアプリケーションに含まれる主要な部分について以下で説明します。
### libuvccameraとusbCameraCommonついて
この二つのディレクトリは外部カメラを使用する際に利用されています。

### ModelUserActivity.ktについて
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
    │   │   ├── generated
    │   │   │   ├── data_binding_base_class_source_out
    │   │   │   │   └── debug
    │   │   │   │       └── ...
    │   │   │   │           └── gesturerecognizer
    │   │   │   │               └── databinding
    │   │   │   │                   ├── ActivityIntroduceBinding.java
    │   │   │   │                   ├── ActivityMainBinding.java
    │   │   │   │                   ├── ActivityRirekiBinding.java
    │   │   │   │                   ├── ItemGestureRecognizerResultBinding.java
    │   │   │   │                   └── ItemRirekiBinding.java
    │   │   │   └── source
    │   │   │       └── buildConfig
    │   │   │           └── ...
    │   │   │               └── gesturerecognizer
    │   │   │                   └── BuildConfig.java
    │   │   ├── intermediates
    │   │   │   ├── assets
    │   │   │   │   └── debug
    │   │   │   │       ├── gesture_recognizer.task
    │   │   │   │       ├── mergeDebugAssets
    │   │   │   │       │   ├── gesture_recognizer.task
    │   │   │   │       │   ├── model1.tflite
    │   │   │   │       │   ├── model2_1.tflite
    │   │   │   │       │   ├── model2_2.tflite
    │   │   │   │       │   ├── model2_3.tflite
    │   │   │   │       │   ├── model2_4.tflite
    │   │   │   │       │   ├── model2_7.tflite
    │   │   │   │       │   ├── model3_3a.tflite
    │   │   │   │       │   ├── model3_3b.tflite
    │   │   │   │       │   ├── model3_3c.tflite
    │   │   │   │       │   ├── model3_3d.tflite
    │   │   │   │       │   ├── model3_3e.tflite
    │   │   │   │       │   ├── model4_3aII.tflite
    │   │   │   │       │   └── model4_3cII.tflite
    │   │   │   │       └── model.tflite
    │   │   │   ├── data_binding_base_class_log_artifact
    │   │   │   │   └── debug
    │   │   │   │       ├── dataBindingGenBaseClassesDebug
    │   │   │   │       │   └── out
    │   │   │   │       │       └── com.google.mediapipe.examples.gesturerecognizer-binding_classes.json
    │   │   │   │       └── out
    │   │   │   │           └── com.google.mediapipe.examples.gesturerecognizer-binding_classes.json
    │   │   │   ├── data_binding_dependency_artifacts
    │   │   │   │   └── debug
    │   │   │   │       └── dataBindingMergeDependencyArtifactsDebug
    │   │   │   ├── data_binding_layout_info_type_merge
    │   │   │   │   └── debug
    │   │   │   │       ├── mergeDebugResources
    │   │   │   │       │   └── out
    │   │   │   │       │       ├── activity_introduce-layout.xml
    │   │   │   │       │       ├── activity_main-layout.xml
    │   │   │   │       │       ├── activity_rireki-layout.xml
    │   │   │   │       │       ├── item_gesture_recognizer_result-layout.xml
    │   │   │   │       │       └── item_rireki-layout.xml
    │   │   │   │       └── out
    │   │   │   │           ├── activity_introduce-layout.xml
    │   │   │   │           ├── activity_main-layout.xml
    │   │   │   │           ├── fragment_camera-layout.xml
    │   │   │   │           ├── fragment_gallery-layout.xml
    │   │   │   │           └── item_gesture_recognizer_result-layout.xml
    │   │   │   ├── data_binding_layout_info_type_package
    │   │   │   │   └── debug
    │   │   │   │       ├── out
    │   │   │   │       │   ├── activity_introduce-layout.xml
    │   │   │   │       │   ├── activity_main-layout.xml
    │   │   │   │       │   ├── fragment_camera-layout.xml
    │   │   │   │       │   ├── fragment_gallery-layout.xml
    │   │   │   │       │   └── item_gesture_recognizer_result-layout.xml
    │   │   │   │       └── packageDebugResources
    │   │   │   │           └── out
    │   │   │   │               ├── activity_introduce-layout.xml
    │   │   │   │               ├── activity_main-layout.xml
    │   │   │   │               ├── activity_rireki-layout.xml
    │   │   │   │               ├── item_gesture_recognizer_result-layout.xml
    │   │   │   │               └── item_rireki-layout.xml
    │   │   │   ├── desugar_graph
    │   │   │   │   └── debug
    │   │   │   │       ├── dexBuilderDebug
    │   │   │   │       │   └── out
    │   │   │   │       │       ├── currentProject
    │   │   │   │       │       │   ├── dirs_bucket_0
    │   │   │   │       │       │   │   └── graph.bin
    │   │   │   │       │       │   ├── dirs_bucket_1
    │   │   │   │       │       │   │   └── graph.bin
    │   │   │   │       │       │   ├── dirs_bucket_2
    │   │   │   │       │       │   │   └── graph.bin
    │   │   │   │       │       │   ├── dirs_bucket_3
    │   │   │   │       │       │   │   └── graph.bin
    │   │   │   │       │       │   ├── jar_430e58915ff1ab13fc08f8bbb01741167a39977d33a672abf50f1d4701a44fcb_bucket_0
    │   │   │   │       │       │   │   └── graph.bin
    │   │   │   │       │       │   ├── jar_430e58915ff1ab13fc08f8bbb01741167a39977d33a672abf50f1d4701a44fcb_bucket_1
    │   │   │   │       │       │   │   └── graph.bin
    │   │   │   │       │       │   ├── jar_430e58915ff1ab13fc08f8bbb01741167a39977d33a672abf50f1d4701a44fcb_bucket_2
    │   │   │   │       │       │   │   └── graph.bin
    │   │   │   │       │       │   └── jar_430e58915ff1ab13fc08f8bbb01741167a39977d33a672abf50f1d4701a44fcb_bucket_3
    │   │   │   │       │       │       └── graph.bin
    │   │   │   │       │       ├── externalLibs
    │   │   │   │       │       ├── mixedScopes
    │   │   │   │       │       └── otherProjects
    │   │   │   │       └── out
    │   │   │   │           └── currentProject
    │   │   │   │               ├── dirs_bucket_0
    │   │   │   │               │   └── graph.bin
    │   │   │   │               ├── dirs_bucket_1
    │   │   │   │               │   └── graph.bin
    │   │   │   │               ├── dirs_bucket_2
    │   │   │   │               │   └── graph.bin
    │   │   │   │               ├── dirs_bucket_3
    │   │   │   │               │   └── graph.bin
    │   │   │   │               ├── jar_acecfb00a23a6e2d8ca78b244f82511b91ad740d6a08e40d0a846b95582dd30f_bucket_0
    │   │   │   │               │   └── graph.bin
    │   │   │   │               ├── jar_acecfb00a23a6e2d8ca78b244f82511b91ad740d6a08e40d0a846b95582dd30f_bucket_1
    │   │   │   │               │   └── graph.bin
    │   │   │   │               ├── jar_acecfb00a23a6e2d8ca78b244f82511b91ad740d6a08e40d0a846b95582dd30f_bucket_2
    │   │   │   │               │   └── graph.bin
    │   │   │   │               └── jar_acecfb00a23a6e2d8ca78b244f82511b91ad740d6a08e40d0a846b95582dd30f_bucket_3
    │   │   │   │                   └── graph.bin
    │   │   │   ├── dex
    │   │   │   │   └── debug
    │   │   │   │       ├── mergeExtDexDebug
    │   │   │   │       │   ├── classes.dex
    │   │   │   │       │   ├── classes2.dex
    │   │   │   │       │   └── classes3.dex
    │   │   │   │       ├── mergeLibDexDebug
    │   │   │   │       │   ├── 0
    │   │   │   │       │   ├── 1
    │   │   │   │       │   ├── 10
    │   │   │   │       │   ├── 11
    │   │   │   │       │   │   └── classes.dex
    │   │   │   │       │   ├── 12
    │   │   │   │       │   ├── 13
    │   │   │   │       │   ├── 14
    │   │   │   │       │   │   └── classes.dex
    │   │   │   │       │   ├── 15
    │   │   │   │       │   ├── 2
    │   │   │   │       │   ├── 3
    │   │   │   │       │   │   └── classes.dex
    │   │   │   │       │   ├── 4
    │   │   │   │       │   ├── 5
    │   │   │   │       │   ├── 6
    │   │   │   │       │   ├── 7
    │   │   │   │       │   ├── 8
    │   │   │   │       │   └── 9
    │   │   │   │       │       └── classes.dex
    │   │   │   │       └── mergeProjectDexDebug
    │   │   │   │           ├── 0
    │   │   │   │           │   └── classes.dex
    │   │   │   │           ├── 1
    │   │   │   │           ├── 10
    │   │   │   │           ├── 11
    │   │   │   │           │   └── classes.dex
    │   │   │   │           ├── 12
    │   │   │   │           ├── 13
    │   │   │   │           ├── 14
    │   │   │   │           ├── 15
    │   │   │   │           ├── 2
    │   │   │   │           ├── 3
    │   │   │   │           ├── 4
    │   │   │   │           │   └── classes.dex
    │   │   │   │           ├── 5
    │   │   │   │           ├── 6
    │   │   │   │           ├── 7
    │   │   │   │           ├── 8
    │   │   │   │           └── 9
    │   │   │   │               └── classes.dex
    │   │   │   ├── dex_archive_input_jar_hashes
    │   │   │   │   └── debug
    │   │   │   │       ├── dexBuilderDebug
    │   │   │   │       │   └── out
    │   │   │   │       └── out
    │   │   │   ├── dex_number_of_buckets_file
    │   │   │   │   └── debug
    │   │   │   │       ├── dexBuilderDebug
    │   │   │   │       │   └── out
    │   │   │   │       └── out
    │   │   │   ├── duplicate_classes_check
    │   │   │   │   └── debug
    │   │   │   │       └── checkDebugDuplicateClasses
    │   │   │   ├── external_file_lib_dex_archives
    │   │   │   │   └── debug
    │   │   │   │       └── desugarDebugFileDependencies
    │   │   │   │           ├── 0_jsoup-1.18.1.jar
    │   │   │   │           └── 1_lucene-gosen-2.1-dev-ipadic.jar
    │   │   │   ├── external_libs_dex_archive
    │   │   │   │   └── debug
    │   │   │   │       └── dexBuilderDebug
    │   │   │   │           └── out
    │   │   │   ├── external_libs_dex_archive_with_artifact_transforms
    │   │   │   │   └── debug
    │   │   │   │       └── dexBuilderDebug
    │   │   │   │           └── out
    │   │   │   ├── incremental
    │   │   │   │   ├── dataBindingGenBaseClassesDebug
    │   │   │   │   │   └── base_builder_log.json
    │   │   │   │   ├── debug
    │   │   │   │   │   ├── mergeDebugResources
    │   │   │   │   │   │   ├── compile-file-map.properties
    │   │   │   │   │   │   ├── merged.dir
    │   │   │   │   │   │   │   ├── values
    │   │   │   │   │   │   │   │   └── values.xml
    │   │   │   │   │   │   │   ├── values-af
    │   │   │   │   │   │   │   │   └── values-af.xml
    │   │   │   │   │   │   │   ├── values-am
    │   │   │   │   │   │   │   │   └── values-am.xml
    │   │   │   │   │   │   │   ├── values-ar
    │   │   │   │   │   │   │   │   └── values-ar.xml
    │   │   │   │   │   │   │   ├── values-as
    │   │   │   │   │   │   │   │   └── values-as.xml
    │   │   │   │   │   │   │   ├── values-az
    │   │   │   │   │   │   │   │   └── values-az.xml
    │   │   │   │   │   │   │   ├── values-b+es+419
    │   │   │   │   │   │   │   │   └── values-b+es+419.xml
    │   │   │   │   │   │   │   ├── values-b+sr+Latn
    │   │   │   │   │   │   │   │   └── values-b+sr+Latn.xml
    │   │   │   │   │   │   │   ├── values-be
    │   │   │   │   │   │   │   │   └── values-be.xml
    │   │   │   │   │   │   │   ├── values-bg
    │   │   │   │   │   │   │   │   └── values-bg.xml
    │   │   │   │   │   │   │   ├── values-bn
    │   │   │   │   │   │   │   │   └── values-bn.xml
    │   │   │   │   │   │   │   ├── values-bs
    │   │   │   │   │   │   │   │   └── values-bs.xml
    │   │   │   │   │   │   │   ├── values-ca
    │   │   │   │   │   │   │   │   └── values-ca.xml
    │   │   │   │   │   │   │   ├── values-cs
    │   │   │   │   │   │   │   │   └── values-cs.xml
    │   │   │   │   │   │   │   ├── values-da
    │   │   │   │   │   │   │   │   └── values-da.xml
    │   │   │   │   │   │   │   ├── values-de
    │   │   │   │   │   │   │   │   └── values-de.xml
    │   │   │   │   │   │   │   ├── values-el
    │   │   │   │   │   │   │   │   └── values-el.xml
    │   │   │   │   │   │   │   ├── values-en-rAU
    │   │   │   │   │   │   │   │   └── values-en-rAU.xml
    │   │   │   │   │   │   │   ├── values-en-rCA
    │   │   │   │   │   │   │   │   └── values-en-rCA.xml
    │   │   │   │   │   │   │   ├── values-en-rGB
    │   │   │   │   │   │   │   │   └── values-en-rGB.xml
    │   │   │   │   │   │   │   ├── values-en-rIN
    │   │   │   │   │   │   │   │   └── values-en-rIN.xml
    │   │   │   │   │   │   │   ├── values-en-rXC
    │   │   │   │   │   │   │   │   └── values-en-rXC.xml
    │   │   │   │   │   │   │   ├── values-es
    │   │   │   │   │   │   │   │   └── values-es.xml
    │   │   │   │   │   │   │   ├── values-es-rUS
    │   │   │   │   │   │   │   │   └── values-es-rUS.xml
    │   │   │   │   │   │   │   ├── values-et
    │   │   │   │   │   │   │   │   └── values-et.xml
    │   │   │   │   │   │   │   ├── values-eu
    │   │   │   │   │   │   │   │   └── values-eu.xml
    │   │   │   │   │   │   │   ├── values-fa
    │   │   │   │   │   │   │   │   └── values-fa.xml
    │   │   │   │   │   │   │   ├── values-fi
    │   │   │   │   │   │   │   │   └── values-fi.xml
    │   │   │   │   │   │   │   ├── values-fr
    │   │   │   │   │   │   │   │   └── values-fr.xml
    │   │   │   │   │   │   │   ├── values-fr-rCA
    │   │   │   │   │   │   │   │   └── values-fr-rCA.xml
    │   │   │   │   │   │   │   ├── values-gl
    │   │   │   │   │   │   │   │   └── values-gl.xml
    │   │   │   │   │   │   │   ├── values-gu
    │   │   │   │   │   │   │   │   └── values-gu.xml
    │   │   │   │   │   │   │   ├── values-h320dp-port-v13
    │   │   │   │   │   │   │   │   └── values-h320dp-port-v13.xml
    │   │   │   │   │   │   │   ├── values-h360dp-land-v13
    │   │   │   │   │   │   │   │   └── values-h360dp-land-v13.xml
    │   │   │   │   │   │   │   ├── values-h480dp-land-v13
    │   │   │   │   │   │   │   │   └── values-h480dp-land-v13.xml
    │   │   │   │   │   │   │   ├── values-h550dp-port-v13
    │   │   │   │   │   │   │   │   └── values-h550dp-port-v13.xml
    │   │   │   │   │   │   │   ├── values-h720dp-v13
    │   │   │   │   │   │   │   │   └── values-h720dp-v13.xml
    │   │   │   │   │   │   │   ├── values-hdpi-v4
    │   │   │   │   │   │   │   │   └── values-hdpi-v4.xml
    │   │   │   │   │   │   │   ├── values-hi
    │   │   │   │   │   │   │   │   └── values-hi.xml
    │   │   │   │   │   │   │   ├── values-hr
    │   │   │   │   │   │   │   │   └── values-hr.xml
    │   │   │   │   │   │   │   ├── values-hu
    │   │   │   │   │   │   │   │   └── values-hu.xml
    │   │   │   │   │   │   │   ├── values-hy
    │   │   │   │   │   │   │   │   └── values-hy.xml
    │   │   │   │   │   │   │   ├── values-in
    │   │   │   │   │   │   │   │   └── values-in.xml
    │   │   │   │   │   │   │   ├── values-is
    │   │   │   │   │   │   │   │   └── values-is.xml
    │   │   │   │   │   │   │   ├── values-it
    │   │   │   │   │   │   │   │   └── values-it.xml
    │   │   │   │   │   │   │   ├── values-iw
    │   │   │   │   │   │   │   │   └── values-iw.xml
    │   │   │   │   │   │   │   ├── values-ja
    │   │   │   │   │   │   │   │   └── values-ja.xml
    │   │   │   │   │   │   │   ├── values-ka
    │   │   │   │   │   │   │   │   └── values-ka.xml
    │   │   │   │   │   │   │   ├── values-kk
    │   │   │   │   │   │   │   │   └── values-kk.xml
    │   │   │   │   │   │   │   ├── values-km
    │   │   │   │   │   │   │   │   └── values-km.xml
    │   │   │   │   │   │   │   ├── values-kn
    │   │   │   │   │   │   │   │   └── values-kn.xml
    │   │   │   │   │   │   │   ├── values-ko
    │   │   │   │   │   │   │   │   └── values-ko.xml
    │   │   │   │   │   │   │   ├── values-ky
    │   │   │   │   │   │   │   │   └── values-ky.xml
    │   │   │   │   │   │   │   ├── values-land
    │   │   │   │   │   │   │   │   └── values-land.xml
    │   │   │   │   │   │   │   ├── values-large-v4
    │   │   │   │   │   │   │   │   └── values-large-v4.xml
    │   │   │   │   │   │   │   ├── values-ldltr-v21
    │   │   │   │   │   │   │   │   └── values-ldltr-v21.xml
    │   │   │   │   │   │   │   ├── values-ldrtl-v17
    │   │   │   │   │   │   │   │   └── values-ldrtl-v17.xml
    │   │   │   │   │   │   │   ├── values-lo
    │   │   │   │   │   │   │   │   └── values-lo.xml
    │   │   │   │   │   │   │   ├── values-lt
    │   │   │   │   │   │   │   │   └── values-lt.xml
    │   │   │   │   │   │   │   ├── values-lv
    │   │   │   │   │   │   │   │   └── values-lv.xml
    │   │   │   │   │   │   │   ├── values-mk
    │   │   │   │   │   │   │   │   └── values-mk.xml
    │   │   │   │   │   │   │   ├── values-ml
    │   │   │   │   │   │   │   │   └── values-ml.xml
    │   │   │   │   │   │   │   ├── values-mn
    │   │   │   │   │   │   │   │   └── values-mn.xml
    │   │   │   │   │   │   │   ├── values-mr
    │   │   │   │   │   │   │   │   └── values-mr.xml
    │   │   │   │   │   │   │   ├── values-ms
    │   │   │   │   │   │   │   │   └── values-ms.xml
    │   │   │   │   │   │   │   ├── values-my
    │   │   │   │   │   │   │   │   └── values-my.xml
    │   │   │   │   │   │   │   ├── values-nb
    │   │   │   │   │   │   │   │   └── values-nb.xml
    │   │   │   │   │   │   │   ├── values-ne
    │   │   │   │   │   │   │   │   └── values-ne.xml
    │   │   │   │   │   │   │   ├── values-night-v8
    │   │   │   │   │   │   │   │   └── values-night-v8.xml
    │   │   │   │   │   │   │   ├── values-nl
    │   │   │   │   │   │   │   │   └── values-nl.xml
    │   │   │   │   │   │   │   ├── values-or
    │   │   │   │   │   │   │   │   └── values-or.xml
    │   │   │   │   │   │   │   ├── values-pa
    │   │   │   │   │   │   │   │   └── values-pa.xml
    │   │   │   │   │   │   │   ├── values-pl
    │   │   │   │   │   │   │   │   └── values-pl.xml
    │   │   │   │   │   │   │   ├── values-port
    │   │   │   │   │   │   │   │   └── values-port.xml
    │   │   │   │   │   │   │   ├── values-pt
    │   │   │   │   │   │   │   │   └── values-pt.xml
    │   │   │   │   │   │   │   ├── values-pt-rBR
    │   │   │   │   │   │   │   │   └── values-pt-rBR.xml
    │   │   │   │   │   │   │   ├── values-pt-rPT
    │   │   │   │   │   │   │   │   └── values-pt-rPT.xml
    │   │   │   │   │   │   │   ├── values-ro
    │   │   │   │   │   │   │   │   └── values-ro.xml
    │   │   │   │   │   │   │   ├── values-ru
    │   │   │   │   │   │   │   │   └── values-ru.xml
    │   │   │   │   │   │   │   ├── values-si
    │   │   │   │   │   │   │   │   └── values-si.xml
    │   │   │   │   │   │   │   ├── values-sk
    │   │   │   │   │   │   │   │   └── values-sk.xml
    │   │   │   │   │   │   │   ├── values-sl
    │   │   │   │   │   │   │   │   └── values-sl.xml
    │   │   │   │   │   │   │   ├── values-small-v4
    │   │   │   │   │   │   │   │   └── values-small-v4.xml
    │   │   │   │   │   │   │   ├── values-sq
    │   │   │   │   │   │   │   │   └── values-sq.xml
    │   │   │   │   │   │   │   ├── values-sr
    │   │   │   │   │   │   │   │   └── values-sr.xml
    │   │   │   │   │   │   │   ├── values-sv
    │   │   │   │   │   │   │   │   └── values-sv.xml
    │   │   │   │   │   │   │   ├── values-sw
    │   │   │   │   │   │   │   │   └── values-sw.xml
    │   │   │   │   │   │   │   ├── values-sw360dp-v13
    │   │   │   │   │   │   │   │   └── values-sw360dp-v13.xml
    │   │   │   │   │   │   │   ├── values-sw600dp-v13
    │   │   │   │   │   │   │   │   └── values-sw600dp-v13.xml
    │   │   │   │   │   │   │   ├── values-ta
    │   │   │   │   │   │   │   │   └── values-ta.xml
    │   │   │   │   │   │   │   ├── values-te
    │   │   │   │   │   │   │   │   └── values-te.xml
    │   │   │   │   │   │   │   ├── values-th
    │   │   │   │   │   │   │   │   └── values-th.xml
    │   │   │   │   │   │   │   ├── values-tl
    │   │   │   │   │   │   │   │   └── values-tl.xml
    │   │   │   │   │   │   │   ├── values-tr
    │   │   │   │   │   │   │   │   └── values-tr.xml
    │   │   │   │   │   │   │   ├── values-uk
    │   │   │   │   │   │   │   │   └── values-uk.xml
    │   │   │   │   │   │   │   ├── values-ur
    │   │   │   │   │   │   │   │   └── values-ur.xml
    │   │   │   │   │   │   │   ├── values-uz
    │   │   │   │   │   │   │   │   └── values-uz.xml
    │   │   │   │   │   │   │   ├── values-v16
    │   │   │   │   │   │   │   │   └── values-v16.xml
    │   │   │   │   │   │   │   ├── values-v17
    │   │   │   │   │   │   │   │   └── values-v17.xml
    │   │   │   │   │   │   │   ├── values-v18
    │   │   │   │   │   │   │   │   └── values-v18.xml
    │   │   │   │   │   │   │   ├── values-v21
    │   │   │   │   │   │   │   │   └── values-v21.xml
    │   │   │   │   │   │   │   ├── values-v22
    │   │   │   │   │   │   │   │   └── values-v22.xml
    │   │   │   │   │   │   │   ├── values-v23
    │   │   │   │   │   │   │   │   └── values-v23.xml
    │   │   │   │   │   │   │   ├── values-v24
    │   │   │   │   │   │   │   │   └── values-v24.xml
    │   │   │   │   │   │   │   ├── values-v25
    │   │   │   │   │   │   │   │   └── values-v25.xml
    │   │   │   │   │   │   │   ├── values-v26
    │   │   │   │   │   │   │   │   └── values-v26.xml
    │   │   │   │   │   │   │   ├── values-v28
    │   │   │   │   │   │   │   │   └── values-v28.xml
    │   │   │   │   │   │   │   ├── values-v31
    │   │   │   │   │   │   │   │   └── values-v31.xml
    │   │   │   │   │   │   │   ├── values-vi
    │   │   │   │   │   │   │   │   └── values-vi.xml
    │   │   │   │   │   │   │   ├── values-w320dp-land-v13
    │   │   │   │   │   │   │   │   └── values-w320dp-land-v13.xml
    │   │   │   │   │   │   │   ├── values-w360dp-port-v13
    │   │   │   │   │   │   │   │   └── values-w360dp-port-v13.xml
    │   │   │   │   │   │   │   ├── values-w480dp-port-v13
    │   │   │   │   │   │   │   │   └── values-w480dp-port-v13.xml
    │   │   │   │   │   │   │   ├── values-w600dp-land-v13
    │   │   │   │   │   │   │   │   └── values-w600dp-land-v13.xml
    │   │   │   │   │   │   │   ├── values-watch-v20
    │   │   │   │   │   │   │   │   └── values-watch-v20.xml
    │   │   │   │   │   │   │   ├── values-watch-v21
    │   │   │   │   │   │   │   │   └── values-watch-v21.xml
    │   │   │   │   │   │   │   ├── values-xlarge-v4
    │   │   │   │   │   │   │   │   └── values-xlarge-v4.xml
    │   │   │   │   │   │   │   ├── values-zh-rCN
    │   │   │   │   │   │   │   │   └── values-zh-rCN.xml
    │   │   │   │   │   │   │   ├── values-zh-rHK
    │   │   │   │   │   │   │   │   └── values-zh-rHK.xml
    │   │   │   │   │   │   │   ├── values-zh-rTW
    │   │   │   │   │   │   │   │   └── values-zh-rTW.xml
    │   │   │   │   │   │   │   └── values-zu
    │   │   │   │   │   │   │       └── values-zu.xml
    │   │   │   │   │   │   ├── merger.xml
    │   │   │   │   │   │   └── stripped.dir
    │   │   │   │   │   │       └── layout
    │   │   │   │   │   │           ├── activity_introduce.xml
    │   │   │   │   │   │           ├── activity_main.xml
    │   │   │   │   │   │           ├── activity_rireki.xml
    │   │   │   │   │   │           ├── item_gesture_recognizer_result.xml
    │   │   │   │   │   │           └── item_rireki.xml
    │   │   │   │   │   └── packageDebugResources
    │   │   │   │   │       ├── compile-file-map.properties
    │   │   │   │   │       ├── merged.dir
    │   │   │   │   │       │   └── values
    │   │   │   │   │       │       └── values.xml
    │   │   │   │   │       ├── merger.xml
    │   │   │   │   │       └── stripped.dir
    │   │   │   │   │           └── layout
    │   │   │   │   │               ├── activity_introduce.xml
    │   │   │   │   │               ├── activity_main.xml
    │   │   │   │   │               ├── activity_rireki.xml
    │   │   │   │   │               ├── item_gesture_recognizer_result.xml
    │   │   │   │   │               └── item_rireki.xml
    │   │   │   │   ├── debug-mergeJavaRes
    │   │   │   │   │   ├── merge-state
    │   │   │   │   │   └── zip-cache
    │   │   │   │   │       ├── +3BNejqSwLCGXT+H_xlGCjZKIqc=
    │   │   │   │   │       ├── +bLCq0R2_B3ADWePAmlN7RV+gfA=
    │   │   │   │   │       ├── 0A02SEYuEs_ifjH2pwWc+1lRcNo=
    │   │   │   │   │       ├── 1JG_qczIk1+fJulV_OAbuhLLXkA=
    │   │   │   │   │       ├── 1MhG8LOnGS2wsHDDhl5eb8XxU+c=
    │   │   │   │   │       ├── 1bRwoSpZa012vaFbIAz+DtzST3Y=
    │   │   │   │   │       ├── 1c8Hz6_cbegdNF7LM6Ia9l3iQSY=
    │   │   │   │   │       ├── 2lga0f1fYCeQAi4+Smi1HzlyKbM=
    │   │   │   │   │       ├── 3EK+FXRbdzFU+3ZKeMye1JWR19Y=
    │   │   │   │   │       ├── 3YvPDBWjojw+wIYjKcSrJapQM8o=
    │   │   │   │   │       ├── 3zgN1SwMklKUOrp5MzScTh04FLM=
    │   │   │   │   │       ├── 4+KE68fCq5kvRIgALkVwFHklSsE=
    │   │   │   │   │       ├── 5YFA14i8C11kSaEhUjQ94AJUnp4=
    │   │   │   │   │       ├── 7Rn5hGp06WaQyYKre3HAd32yEBw=
    │   │   │   │   │       ├── 7oiCUaEWpASX8aPShEHSfFHEscw=
    │   │   │   │   │       ├── B2YnBafFm9wnPSOagbLYQv094Fc=
    │   │   │   │   │       ├── C9IlEdde1rzVU3+RFSAxzhyjiL8=
    │   │   │   │   │       ├── CyyoD_84emjqFYIRI2Gyfa6vM3w=
    │   │   │   │   │       ├── D4B8sfjR1v1YNtxmDKh30gDlvic=
    │   │   │   │   │       ├── EB8T2jDn5_NgJ+yky8wZKG71644=
    │   │   │   │   │       ├── EvpOZzJieyawpI6RNps3ow7kC6U=
    │   │   │   │   │       ├── FK5lz9IKGNhM_eyzlH_WaTZn14w=
    │   │   │   │   │       ├── Fg7BLInowUCiHP8lfIjKS+R7Tww=
    │   │   │   │   │       ├── G2sYhe2wbz_NhJpeRnoU4OKDgP4=
    │   │   │   │   │       ├── HmaWuodB1iCz9sPho3FmY0QCeGE=
    │   │   │   │   │       ├── IEe5b0C6QzvbQigtaYuCQIBBDEw=
    │   │   │   │   │       ├── IbeuxVw43VptXZcHWmJw+pzXis8=
    │   │   │   │   │       ├── ItyumCbndB09OahS9gijvpQ8g5k=
    │   │   │   │   │       ├── JExtn4F5BjYnAFHVdrfxti1Nafw=
    │   │   │   │   │       ├── JdiWxQZqmnJYijnBJZwOWXoFttQ=
    │   │   │   │   │       ├── JfGQ9p5mq4U_BfrrsLPU7Uwb5O4=
    │   │   │   │   │       ├── KCC5_+sX04XCFef9gFKETQgkX4Q=
    │   │   │   │   │       ├── Lev+ubu4tVAQlYRiWCKQyX54qfs=
    │   │   │   │   │       ├── LuD4UNnUR95DToOwv2DVICeq_XM=
    │   │   │   │   │       ├── M+rvnbuxApmNptyzXd8cvvIWA+Y=
    │   │   │   │   │       ├── MDPG4fLQyOEepEhK0itlNbIHRVM=
    │   │   │   │   │       ├── MvLnjSnOPWJWaMCzYQhPh_usYqM=
    │   │   │   │   │       ├── NIc3WUESb8zsjQsAyWFoUFQ6cb4=
    │   │   │   │   │       ├── N_Vwx4V6vtlyGEa7ACsF22kZJXA=
    │   │   │   │   │       ├── Nk_uX9MgmuHvYgtpIDn8nnXHlww=
    │   │   │   │   │       ├── P8GFVL+Pkt4KzTJ3R6uTd8sRG2o=
    │   │   │   │   │       ├── PWazWg+HcYT+EQAR_RUNnhi9diI=
    │   │   │   │   │       ├── Q4Z7cIh4Zxqa6HonAI81Pjl7h5I=
    │   │   │   │   │       ├── QAai206xIZ7wXVcewOzOaP83pow=
    │   │   │   │   │       ├── QhvzzGTAGzgQ7hXQXdHv5NAKtiU=
    │   │   │   │   │       ├── RDpZApeKKVup0sdSQChnFAhTTns=
    │   │   │   │   │       ├── Rjc9edSpzIU3+R6v8a5ICu+BwuI=
    │   │   │   │   │       ├── RtTsHCh6OhSMd_nb7qjAsVI6t+k=
    │   │   │   │   │       ├── RwY53zsXS4TEbCStR0xvZo8gCAE=
    │   │   │   │   │       ├── Su9Op9o2sCbJgMz95d_5DXLlhr4=
    │   │   │   │   │       ├── Tox67jlhwPmq9pHLf0YPeO3_x1Y=
    │   │   │   │   │       ├── UGi3djJV66B22KIEvbttkluQAvg=
    │   │   │   │   │       ├── UYvdSg0hqi+lR1TgOkQ+301QBXs=
    │   │   │   │   │       ├── Uyj3GFar7Mx5p9QHgGX9cuMjr0o=
    │   │   │   │   │       ├── VBMSH1bFF0DHQDj_l3le0s7NabA=
    │   │   │   │   │       ├── VOXMPqPoWCwOQfhD5+iTRssCGhk=
    │   │   │   │   │       ├── WCX2ENx4_gfosR1SFSXrpLcSUVE=
    │   │   │   │   │       ├── X6b_0jPuZ589f4IuL+rE4npxI78=
    │   │   │   │   │       ├── XCM3WpDl2b_K6EasLXieaenOBBo=
    │   │   │   │   │       ├── XTcTETAirOO67UCtE0PuHEbhC50=
    │   │   │   │   │       ├── XohigItA1VgXBSCcO+y8rhicAag=
    │   │   │   │   │       ├── YbkC6gEbUjOOpdHaTuWyl86e_wI=
    │   │   │   │   │       ├── YqDpham5t0C2YAHzDrgWcx8eBl0=
    │   │   │   │   │       ├── ZCokBJJiaVZ32kAU9VwRGA3mhnQ=
    │   │   │   │   │       ├── ZEJyVbg2ch64zwinmqk1CsSeWGw=
    │   │   │   │   │       ├── ZjH1vPwlCOv969IrNjQ2PEiUFPQ=
    │   │   │   │   │       ├── _6TktSsP_5yrMAOzkBv24mbG2ks=
    │   │   │   │   │       ├── _QLvh1UGk8RxXRI40Vqz+X803JY=
    │   │   │   │   │       ├── _aAwLtyWocuflZpmvNoF3H0zO28=
    │   │   │   │   │       ├── _irTUY2tq1s2dhTmIyy6CSG1kmI=
    │   │   │   │   │       ├── bIZQos1pU2RJrMWASuVHAxl6brs=
    │   │   │   │   │       ├── beB00Mj+YKvM5g0sSVTiZLLhwrg=
    │   │   │   │   │       ├── by8C6mI28ks6e5VWDBhqu22f0w4=
    │   │   │   │   │       ├── cuaSNGWxCMzHqRSW6hxnErClWq4=
    │   │   │   │   │       ├── dBRjmKUZctUsPgi1P_BYgvksl9U=
    │   │   │   │   │       ├── dFCyn3Xgo44twPvuS40zssVRVPw=
    │   │   │   │   │       ├── e4RV_jtmc2Y5dzel2k+WQQbsU0A=
    │   │   │   │   │       ├── eTMHaUWjIA0a61Rcz6315sNAINY=
    │   │   │   │   │       ├── efu5Fr5cEk8OlOQoAmSQnWOEf8Y=
    │   │   │   │   │       ├── fMaFuH9ir8RXoIxkGNlzjvdh3Rw=
    │   │   │   │   │       ├── fwqnxr74JgUiHBWAkctFFAHpwSk=
    │   │   │   │   │       ├── g6e26QCjlHHpESsDCZ2012Yxgq0=
    │   │   │   │   │       ├── grJQG_5suZM01IZ2Nt30Na_Shmw=
    │   │   │   │   │       ├── iD7N5Ih98I6s83x9VCApA2UsZKU=
    │   │   │   │   │       ├── iNc3NklGyfcHygull4FPy7Q6E9I=
    │   │   │   │   │       ├── ivKHDHna60Y2JNu9GtPcoP7wXQU=
    │   │   │   │   │       ├── jGAmy6NLT6X2r0V_F4elg7thwyI=
    │   │   │   │   │       ├── jYw5hHEg2nRl1l6KccODBMSwu9I=
    │   │   │   │   │       ├── kuFEA+AlafFDQABOTmQzaZG32+0=
    │   │   │   │   │       ├── lEx_Xn+VOd4q7ty62FBdAqXS8F0=
    │   │   │   │   │       ├── lMEGnAmAmoRFmDNPVoeoMx34cwc=
    │   │   │   │   │       ├── mrZ63KmS575znYuWqrFx_AAVK6Y=
    │   │   │   │   │       ├── n3sneqgvG+NU3A2xI+48HeLDHio=
    │   │   │   │   │       ├── nVE2lDtnJT5XJsUSHf8szhFZnoo=
    │   │   │   │   │       ├── ncIQUKmMDJx4djoKalBB4CKt62M=
    │   │   │   │   │       ├── nmjujTprefQOEZV3LKXs6JoUy2I=
    │   │   │   │   │       ├── ns8RAsSuO8yo7F9HafJfLJYW3AI=
    │   │   │   │   │       ├── o7WL+SnEEFI7CjsmNNvOwR0n+Mw=
    │   │   │   │   │       ├── p7cyIJPEcuN+bQsOjC0ywh4FUZk=
    │   │   │   │   │       ├── pCiKN_llg18gplHHB8C181XLUO4=
    │   │   │   │   │       ├── pZvxXvU1hpYh+xtQyfTZbuqMsS8=
    │   │   │   │   │       ├── qO6fdBLLAFoJiI4iN9tIawXtxOk=
    │   │   │   │   │       ├── qhBpezawpnhgwdYIDmqcYeD3X2Q=
    │   │   │   │   │       ├── qs4azCFFuNYi_nzn_qhE4XRmf8Q=
    │   │   │   │   │       ├── qt4sajoxaP+9njtZuW1B+6Y0Sa0=
    │   │   │   │   │       ├── r2+dlJDzugSspNVRUfyG7t4Sw+M=
    │   │   │   │   │       ├── r_6vGcKXCmcbKv15c9+ScQTJcSY=
    │   │   │   │   │       ├── rgkwrvhr6GOiK2rB8Fg39H0KGmA=
    │   │   │   │   │       ├── rwhqctxG2n5x7QKUl5qCM0VBEhk=
    │   │   │   │   │       ├── tOsdNNmFLQVBRyL397+pb4qQAOI=
    │   │   │   │   │       ├── u70Fisl7LEJQoCZB2Q+GF6XZ_bk=
    │   │   │   │   │       ├── vxhUmHd+RNEi2rxVrxA8wqeAkfc=
    │   │   │   │   │       ├── wOkiUjKJsUlWeHU8cQGa5UdEjU4=
    │   │   │   │   │       ├── whnFnCBElmQOI9T22ZUw8xPr8vc=
    │   │   │   │   │       ├── xHBl1qjGedsRsMccMOyH+9hvLPY=
    │   │   │   │   │       ├── xfRtqYDAYtu3Hn6l5Es1PKcd2_E=
    │   │   │   │   │       ├── xvI5g9bt4BnWurfhdlQ8UaQvvII=
    │   │   │   │   │       ├── yBFJONoRshgtyAGSFPdcQGOcfzY=
    │   │   │   │   │       └── zDgIAm3v2BJWNJkA_1BBSZ66jss=
    │   │   │   │   ├── mergeDebugAssets
    │   │   │   │   │   └── merger.xml
    │   │   │   │   ├── mergeDebugJniLibFolders
    │   │   │   │   │   └── merger.xml
    │   │   │   │   ├── mergeDebugShaders
    │   │   │   │   │   └── merger.xml
    │   │   │   │   ├── packageDebug
    │   │   │   │   │   └── tmp
    │   │   │   │   │       └── debug
    │   │   │   │   │           ├── dex-renamer-state.txt
    │   │   │   │   │           └── zip-cache
    │   │   │   │   │               ├── androidResources
    │   │   │   │   │               └── javaResources0
    │   │   │   │   └── processDebugResources
    │   │   │   │       └── resources-list-for-resources-debug.ap_.txt
    │   │   │   ├── java_res
    │   │   │   │   └── debug
    │   │   │   │       └── processDebugJavaRes
    │   │   │   │           └── out
    │   │   │   │               ├── META-INF
    │   │   │   │               │   └── app_debug.kotlin_module
    │   │   │   │               └── com
    │   │   │   │                   └── google
    │   │   │   │                       └── mediapipe
    │   │   │   │                           └── examples
    │   │   │   │                               └── gesturerecognizer
    │   │   │   │                                   └── fragment
    │   │   │   ├── javac
    │   │   │   │   └── debug
    │   │   │   │       ├── classes
    │   │   │   │       │   └── com
    │   │   │   │       │       └── google
    │   │   │   │       │           └── mediapipe
    │   │   │   │       │               └── examples
    │   │   │   │       │                   └── gesturerecognizer
    │   │   │   │       │                       ├── BuildConfig.class
    │   │   │   │       │                       ├── ModelUser.class
    │   │   │   │       │                       └── databinding
    │   │   │   │       │                           ├── ActivityIntroduceBinding.class
    │   │   │   │       │                           ├── ActivityMainBinding.class
    │   │   │   │       │                           ├── FragmentCameraBinding.class
    │   │   │   │       │                           ├── FragmentGalleryBinding.class
    │   │   │   │       │                           └── ItemGestureRecognizerResultBinding.class
    │   │   │   │       └── compileDebugJavaWithJavac
    │   │   │   │           └── classes
    │   │   │   │               └── com
    │   │   │   │                   └── google
    │   │   │   │                       └── mediapipe
    │   │   │   │                           └── examples
    │   │   │   │                               └── gesturerecognizer
    │   │   │   │                                   ├── FuriganaView$1.class
    │   │   │   │                                   ├── FuriganaView$LineFurigana.class
    │   │   │   │                                   ├── FuriganaView$LineNormal.class
    │   │   │   │                                   ├── FuriganaView$Span.class
    │   │   │   │                                   ├── FuriganaView$TextFurigana.class
    │   │   │   │                                   ├── FuriganaView$TextNormal.class
    │   │   │   │                                   ├── FuriganaView.class
    │   │   │   │                                   ├── Hurigana.class
    │   │   │   │                                   ├── ModelUser.class
    │   │   │   │                                   ├── QuadraticOptimizer.class
    │   │   │   │                                   └── databinding
    │   │   │   │                                       ├── ActivityIntroduceBinding.class
    │   │   │   │                                       ├── ActivityMainBinding.class
    │   │   │   │                                       ├── ActivityRirekiBinding.class
    │   │   │   │                                       ├── ItemGestureRecognizerResultBinding.class
    │   │   │   │                                       └── ItemRirekiBinding.class
    │   │   │   ├── local_only_symbol_list
    │   │   │   │   └── debug
    │   │   │   │       ├── R-def.txt
    │   │   │   │       └── parseDebugLocalResources
    │   │   │   │           └── R-def.txt
    │   │   │   ├── manifest_merge_blame_file
    │   │   │   │   └── debug
    │   │   │   │       ├── manifest-merger-blame-debug-report.txt
    │   │   │   │       └── processDebugMainManifest
    │   │   │   │           └── manifest-merger-blame-debug-report.txt
    │   │   │   ├── merged_java_res
    │   │   │   │   └── debug
    │   │   │   │       ├── base.jar
    │   │   │   │       └── mergeDebugJavaResource
    │   │   │   │           └── base.jar
    │   │   │   ├── merged_jni_libs
    │   │   │   │   └── debug
    │   │   │   │       └── mergeDebugJniLibFolders
    │   │   │   │           └── out
    │   │   │   ├── merged_manifest
    │   │   │   │   └── debug
    │   │   │   │       ├── AndroidManifest.xml
    │   │   │   │       └── processDebugMainManifest
    │   │   │   │           └── AndroidManifest.xml
    │   │   │   ├── merged_manifests
    │   │   │   │   └── debug
    │   │   │   │       ├── AndroidManifest.xml
    │   │   │   │       ├── output-metadata.json
    │   │   │   │       └── processDebugManifest
    │   │   │   │           ├── AndroidManifest.xml
    │   │   │   │           └── output-metadata.json
    │   │   │   ├── merged_native_libs
    │   │   │   │   └── debug
    │   │   │   │       ├── mergeDebugNativeLibs
    │   │   │   │       │   └── out
    │   │   │   │       │       └── lib
    │   │   │   │       │           ├── arm64-v8a
    │   │   │   │       │           │   ├── libUVCCamera.so
    │   │   │   │       │           │   ├── libimage_processing_util_jni.so
    │   │   │   │       │           │   ├── libjpeg-turbo1500.so
    │   │   │   │       │           │   ├── libmediapipe_tasks_vision_jni.so
    │   │   │   │       │           │   ├── libtensorflowlite_gpu_jni.so
    │   │   │   │       │           │   ├── libtensorflowlite_jni.so
    │   │   │   │       │           │   ├── libusb100.so
    │   │   │   │       │           │   └── libuvc.so
    │   │   │   │       │           ├── armeabi-v7a
    │   │   │   │       │           │   ├── libUVCCamera.so
    │   │   │   │       │           │   ├── libimage_processing_util_jni.so
    │   │   │   │       │           │   ├── libjpeg-turbo1500.so
    │   │   │   │       │           │   ├── libmediapipe_tasks_vision_jni.so
    │   │   │   │       │           │   ├── libtensorflowlite_gpu_jni.so
    │   │   │   │       │           │   ├── libtensorflowlite_jni.so
    │   │   │   │       │           │   ├── libusb100.so
    │   │   │   │       │           │   └── libuvc.so
    │   │   │   │       │           ├── x86
    │   │   │   │       │           │   ├── libimage_processing_util_jni.so
    │   │   │   │       │           │   ├── libmediapipe_tasks_vision_jni.so
    │   │   │   │       │           │   ├── libtensorflowlite_gpu_jni.so
    │   │   │   │       │           │   └── libtensorflowlite_jni.so
    │   │   │   │       │           └── x86_64
    │   │   │   │       │               ├── libimage_processing_util_jni.so
    │   │   │   │       │               ├── libtensorflowlite_gpu_jni.so
    │   │   │   │       │               └── libtensorflowlite_jni.so
    │   │   │   │       └── out
    │   │   │   │           └── lib
    │   │   │   │               ├── arm64-v8a
    │   │   │   │               │   ├── libimage_processing_util_jni.so
    │   │   │   │               │   ├── libmediapipe_tasks_vision_jni.so
    │   │   │   │               │   ├── libtensorflowlite_gpu_jni.so
    │   │   │   │               │   └── libtensorflowlite_jni.so
    │   │   │   │               ├── armeabi-v7a
    │   │   │   │               │   ├── libimage_processing_util_jni.so
    │   │   │   │               │   ├── libmediapipe_tasks_vision_jni.so
    │   │   │   │               │   ├── libtensorflowlite_gpu_jni.so
    │   │   │   │               │   └── libtensorflowlite_jni.so
    │   │   │   │               ├── x86
    │   │   │   │               │   ├── libimage_processing_util_jni.so
    │   │   │   │               │   ├── libmediapipe_tasks_vision_jni.so
    │   │   │   │               │   ├── libtensorflowlite_gpu_jni.so
    │   │   │   │               │   └── libtensorflowlite_jni.so
    │   │   │   │               └── x86_64
    │   │   │   │                   ├── libimage_processing_util_jni.so
    │   │   │   │                   ├── libtensorflowlite_gpu_jni.so
    │   │   │   │                   └── libtensorflowlite_jni.so
    │   │   │   ├── merged_res
    │   │   │   │   └── debug
    │   │   │   │       ├── color_bg_nav_item.xml.flat
    │   │   │   │       ├── drawable_ic_baseline_add_24.xml.flat
    │   │   │   │       ├── drawable_ic_baseline_photo_camera_24.xml.flat
    │   │   │   │       ├── drawable_ic_baseline_photo_library_24.xml.flat
    │   │   │   │       ├── drawable_ic_minus.xml.flat
    │   │   │   │       ├── drawable_ic_plus.xml.flat
    │   │   │   │       ├── drawable_icn_chevron_up.png.flat
    │   │   │   │       ├── drawable_media_pipe_banner.xml.flat
    │   │   │   │       ├── layout_activity_introduce.xml.flat
    │   │   │   │       ├── layout_activity_main.xml.flat
    │   │   │   │       ├── layout_fragment_camera.xml.flat
    │   │   │   │       ├── layout_fragment_gallery.xml.flat
    │   │   │   │       ├── layout_item_gesture_recognizer_result.xml.flat
    │   │   │   │       ├── menu_menu_bottom_nav.xml.flat
    │   │   │   │       ├── mergeDebugResources
    │   │   │   │       │   ├── color_bg_nav_item.xml.flat
    │   │   │   │       │   ├── drawable_ic_baseline_add_24.xml.flat
    │   │   │   │       │   ├── drawable_ic_baseline_photo_camera_24.xml.flat
    │   │   │   │       │   ├── drawable_ic_baseline_photo_library_24.xml.flat
    │   │   │   │       │   ├── drawable_ic_minus.xml.flat
    │   │   │   │       │   ├── drawable_ic_plus.xml.flat
    │   │   │   │       │   ├── drawable_icn_chevron_up.png.flat
    │   │   │   │       │   ├── drawable_media_pipe_banner.xml.flat
    │   │   │   │       │   ├── layout_activity_introduce.xml.flat
    │   │   │   │       │   ├── layout_activity_main.xml.flat
    │   │   │   │       │   ├── layout_activity_rireki.xml.flat
    │   │   │   │       │   ├── layout_item_gesture_recognizer_result.xml.flat
    │   │   │   │       │   ├── layout_item_rireki.xml.flat
    │   │   │   │       │   ├── menu_menu_introduce.xml.flat
    │   │   │   │       │   ├── mipmap-hdpi_ic_konomusi.jpg.flat
    │   │   │   │       │   ├── mipmap-hdpi_ic_launcher.png.flat
    │   │   │   │       │   ├── mipmap-hdpi_ic_launcher_round.png.flat
    │   │   │   │       │   ├── mipmap-mdpi_ic_launcher.png.flat
    │   │   │   │       │   ├── mipmap-mdpi_ic_launcher_round.png.flat
    │   │   │   │       │   ├── mipmap-xhdpi_ic_launcher.png.flat
    │   │   │   │       │   ├── mipmap-xhdpi_ic_launcher_round.png.flat
    │   │   │   │       │   ├── mipmap-xxhdpi_ic_launcher.png.flat
    │   │   │   │       │   ├── mipmap-xxhdpi_ic_launcher_round.png.flat
    │   │   │   │       │   ├── mipmap-xxxhdpi_ic_launcher.png.flat
    │   │   │   │       │   ├── mipmap-xxxhdpi_ic_launcher_round.png.flat
    │   │   │   │       │   ├── values-af_values-af.arsc.flat
    │   │   │   │       │   ├── values-am_values-am.arsc.flat
    │   │   │   │       │   ├── values-ar_values-ar.arsc.flat
    │   │   │   │       │   ├── values-as_values-as.arsc.flat
    │   │   │   │       │   ├── values-az_values-az.arsc.flat
    │   │   │   │       │   ├── values-b+es+419_values-b+es+419.arsc.flat
    │   │   │   │       │   ├── values-b+sr+Latn_values-b+sr+Latn.arsc.flat
    │   │   │   │       │   ├── values-be_values-be.arsc.flat
    │   │   │   │       │   ├── values-bg_values-bg.arsc.flat
    │   │   │   │       │   ├── values-bn_values-bn.arsc.flat
    │   │   │   │       │   ├── values-bs_values-bs.arsc.flat
    │   │   │   │       │   ├── values-ca_values-ca.arsc.flat
    │   │   │   │       │   ├── values-cs_values-cs.arsc.flat
    │   │   │   │       │   ├── values-da_values-da.arsc.flat
    │   │   │   │       │   ├── values-de_values-de.arsc.flat
    │   │   │   │       │   ├── values-el_values-el.arsc.flat
    │   │   │   │       │   ├── values-en-rAU_values-en-rAU.arsc.flat
    │   │   │   │       │   ├── values-en-rCA_values-en-rCA.arsc.flat
    │   │   │   │       │   ├── values-en-rGB_values-en-rGB.arsc.flat
    │   │   │   │       │   ├── values-en-rIN_values-en-rIN.arsc.flat
    │   │   │   │       │   ├── values-en-rXC_values-en-rXC.arsc.flat
    │   │   │   │       │   ├── values-es-rUS_values-es-rUS.arsc.flat
    │   │   │   │       │   ├── values-es_values-es.arsc.flat
    │   │   │   │       │   ├── values-et_values-et.arsc.flat
    │   │   │   │       │   ├── values-eu_values-eu.arsc.flat
    │   │   │   │       │   ├── values-fa_values-fa.arsc.flat
    │   │   │   │       │   ├── values-fi_values-fi.arsc.flat
    │   │   │   │       │   ├── values-fr-rCA_values-fr-rCA.arsc.flat
    │   │   │   │       │   ├── values-fr_values-fr.arsc.flat
    │   │   │   │       │   ├── values-gl_values-gl.arsc.flat
    │   │   │   │       │   ├── values-gu_values-gu.arsc.flat
    │   │   │   │       │   ├── values-h320dp-port-v13_values-h320dp-port-v13.arsc.flat
    │   │   │   │       │   ├── values-h360dp-land-v13_values-h360dp-land-v13.arsc.flat
    │   │   │   │       │   ├── values-h480dp-land-v13_values-h480dp-land-v13.arsc.flat
    │   │   │   │       │   ├── values-h550dp-port-v13_values-h550dp-port-v13.arsc.flat
    │   │   │   │       │   ├── values-h720dp-v13_values-h720dp-v13.arsc.flat
    │   │   │   │       │   ├── values-hdpi-v4_values-hdpi-v4.arsc.flat
    │   │   │   │       │   ├── values-hi_values-hi.arsc.flat
    │   │   │   │       │   ├── values-hr_values-hr.arsc.flat
    │   │   │   │       │   ├── values-hu_values-hu.arsc.flat
    │   │   │   │       │   ├── values-hy_values-hy.arsc.flat
    │   │   │   │       │   ├── values-in_values-in.arsc.flat
    │   │   │   │       │   ├── values-is_values-is.arsc.flat
    │   │   │   │       │   ├── values-it_values-it.arsc.flat
    │   │   │   │       │   ├── values-iw_values-iw.arsc.flat
    │   │   │   │       │   ├── values-ja_values-ja.arsc.flat
    │   │   │   │       │   ├── values-ka_values-ka.arsc.flat
    │   │   │   │       │   ├── values-kk_values-kk.arsc.flat
    │   │   │   │       │   ├── values-km_values-km.arsc.flat
    │   │   │   │       │   ├── values-kn_values-kn.arsc.flat
    │   │   │   │       │   ├── values-ko_values-ko.arsc.flat
    │   │   │   │       │   ├── values-ky_values-ky.arsc.flat
    │   │   │   │       │   ├── values-land_values-land.arsc.flat
    │   │   │   │       │   ├── values-large-v4_values-large-v4.arsc.flat
    │   │   │   │       │   ├── values-ldltr-v21_values-ldltr-v21.arsc.flat
    │   │   │   │       │   ├── values-ldrtl-v17_values-ldrtl-v17.arsc.flat
    │   │   │   │       │   ├── values-lo_values-lo.arsc.flat
    │   │   │   │       │   ├── values-lt_values-lt.arsc.flat
    │   │   │   │       │   ├── values-lv_values-lv.arsc.flat
    │   │   │   │       │   ├── values-mk_values-mk.arsc.flat
    │   │   │   │       │   ├── values-ml_values-ml.arsc.flat
    │   │   │   │       │   ├── values-mn_values-mn.arsc.flat
    │   │   │   │       │   ├── values-mr_values-mr.arsc.flat
    │   │   │   │       │   ├── values-ms_values-ms.arsc.flat
    │   │   │   │       │   ├── values-my_values-my.arsc.flat
    │   │   │   │       │   ├── values-nb_values-nb.arsc.flat
    │   │   │   │       │   ├── values-ne_values-ne.arsc.flat
    │   │   │   │       │   ├── values-night-v8_values-night-v8.arsc.flat
    │   │   │   │       │   ├── values-nl_values-nl.arsc.flat
    │   │   │   │       │   ├── values-or_values-or.arsc.flat
    │   │   │   │       │   ├── values-pa_values-pa.arsc.flat
    │   │   │   │       │   ├── values-pl_values-pl.arsc.flat
    │   │   │   │       │   ├── values-port_values-port.arsc.flat
    │   │   │   │       │   ├── values-pt-rBR_values-pt-rBR.arsc.flat
    │   │   │   │       │   ├── values-pt-rPT_values-pt-rPT.arsc.flat
    │   │   │   │       │   ├── values-pt_values-pt.arsc.flat
    │   │   │   │       │   ├── values-ro_values-ro.arsc.flat
    │   │   │   │       │   ├── values-ru_values-ru.arsc.flat
    │   │   │   │       │   ├── values-si_values-si.arsc.flat
    │   │   │   │       │   ├── values-sk_values-sk.arsc.flat
    │   │   │   │       │   ├── values-sl_values-sl.arsc.flat
    │   │   │   │       │   ├── values-small-v4_values-small-v4.arsc.flat
    │   │   │   │       │   ├── values-sq_values-sq.arsc.flat
    │   │   │   │       │   ├── values-sr_values-sr.arsc.flat
    │   │   │   │       │   ├── values-sv_values-sv.arsc.flat
    │   │   │   │       │   ├── values-sw360dp-v13_values-sw360dp-v13.arsc.flat
    │   │   │   │       │   ├── values-sw600dp-v13_values-sw600dp-v13.arsc.flat
    │   │   │   │       │   ├── values-sw_values-sw.arsc.flat
    │   │   │   │       │   ├── values-ta_values-ta.arsc.flat
    │   │   │   │       │   ├── values-te_values-te.arsc.flat
    │   │   │   │       │   ├── values-th_values-th.arsc.flat
    │   │   │   │       │   ├── values-tl_values-tl.arsc.flat
    │   │   │   │       │   ├── values-tr_values-tr.arsc.flat
    │   │   │   │       │   ├── values-uk_values-uk.arsc.flat
    │   │   │   │       │   ├── values-ur_values-ur.arsc.flat
    │   │   │   │       │   ├── values-uz_values-uz.arsc.flat
    │   │   │   │       │   ├── values-v16_values-v16.arsc.flat
    │   │   │   │       │   ├── values-v17_values-v17.arsc.flat
    │   │   │   │       │   ├── values-v18_values-v18.arsc.flat
    │   │   │   │       │   ├── values-v21_values-v21.arsc.flat
    │   │   │   │       │   ├── values-v22_values-v22.arsc.flat
    │   │   │   │       │   ├── values-v23_values-v23.arsc.flat
    │   │   │   │       │   ├── values-v24_values-v24.arsc.flat
    │   │   │   │       │   ├── values-v25_values-v25.arsc.flat
    │   │   │   │       │   ├── values-v26_values-v26.arsc.flat
    │   │   │   │       │   ├── values-v28_values-v28.arsc.flat
    │   │   │   │       │   ├── values-v31_values-v31.arsc.flat
    │   │   │   │       │   ├── values-vi_values-vi.arsc.flat
    │   │   │   │       │   ├── values-w320dp-land-v13_values-w320dp-land-v13.arsc.flat
    │   │   │   │       │   ├── values-w360dp-port-v13_values-w360dp-port-v13.arsc.flat
    │   │   │   │       │   ├── values-w480dp-port-v13_values-w480dp-port-v13.arsc.flat
    │   │   │   │       │   ├── values-w600dp-land-v13_values-w600dp-land-v13.arsc.flat
    │   │   │   │       │   ├── values-watch-v20_values-watch-v20.arsc.flat
    │   │   │   │       │   ├── values-watch-v21_values-watch-v21.arsc.flat
    │   │   │   │       │   ├── values-xlarge-v4_values-xlarge-v4.arsc.flat
    │   │   │   │       │   ├── values-zh-rCN_values-zh-rCN.arsc.flat
    │   │   │   │       │   ├── values-zh-rHK_values-zh-rHK.arsc.flat
    │   │   │   │       │   ├── values-zh-rTW_values-zh-rTW.arsc.flat
    │   │   │   │       │   ├── values-zu_values-zu.arsc.flat
    │   │   │   │       │   └── values_values.arsc.flat
    │   │   │   │       ├── mipmap-hdpi_ic_launcher.png.flat
    │   │   │   │       ├── mipmap-hdpi_ic_launcher_round.png.flat
    │   │   │   │       ├── mipmap-mdpi_ic_launcher.png.flat
    │   │   │   │       ├── mipmap-mdpi_ic_launcher_round.png.flat
    │   │   │   │       ├── mipmap-xhdpi_ic_launcher.png.flat
    │   │   │   │       ├── mipmap-xhdpi_ic_launcher_round.png.flat
    │   │   │   │       ├── mipmap-xxhdpi_ic_launcher.png.flat
    │   │   │   │       ├── mipmap-xxhdpi_ic_launcher_round.png.flat
    │   │   │   │       ├── mipmap-xxxhdpi_ic_launcher.png.flat
    │   │   │   │       ├── mipmap-xxxhdpi_ic_launcher_round.png.flat
    │   │   │   │       ├── navigation_nav_graph.xml.flat
    │   │   │   │       ├── values-af_values-af.arsc.flat
    │   │   │   │       ├── values-am_values-am.arsc.flat
    │   │   │   │       ├── values-ar_values-ar.arsc.flat
    │   │   │   │       ├── values-as_values-as.arsc.flat
    │   │   │   │       ├── values-az_values-az.arsc.flat
    │   │   │   │       ├── values-b+es+419_values-b+es+419.arsc.flat
    │   │   │   │       ├── values-b+sr+Latn_values-b+sr+Latn.arsc.flat
    │   │   │   │       ├── values-be_values-be.arsc.flat
    │   │   │   │       ├── values-bg_values-bg.arsc.flat
    │   │   │   │       ├── values-bn_values-bn.arsc.flat
    │   │   │   │       ├── values-bs_values-bs.arsc.flat
    │   │   │   │       ├── values-ca_values-ca.arsc.flat
    │   │   │   │       ├── values-cs_values-cs.arsc.flat
    │   │   │   │       ├── values-da_values-da.arsc.flat
    │   │   │   │       ├── values-de_values-de.arsc.flat
    │   │   │   │       ├── values-el_values-el.arsc.flat
    │   │   │   │       ├── values-en-rAU_values-en-rAU.arsc.flat
    │   │   │   │       ├── values-en-rCA_values-en-rCA.arsc.flat
    │   │   │   │       ├── values-en-rGB_values-en-rGB.arsc.flat
    │   │   │   │       ├── values-en-rIN_values-en-rIN.arsc.flat
    │   │   │   │       ├── values-en-rXC_values-en-rXC.arsc.flat
    │   │   │   │       ├── values-es-rUS_values-es-rUS.arsc.flat
    │   │   │   │       ├── values-es_values-es.arsc.flat
    │   │   │   │       ├── values-et_values-et.arsc.flat
    │   │   │   │       ├── values-eu_values-eu.arsc.flat
    │   │   │   │       ├── values-fa_values-fa.arsc.flat
    │   │   │   │       ├── values-fi_values-fi.arsc.flat
    │   │   │   │       ├── values-fr-rCA_values-fr-rCA.arsc.flat
    │   │   │   │       ├── values-fr_values-fr.arsc.flat
    │   │   │   │       ├── values-gl_values-gl.arsc.flat
    │   │   │   │       ├── values-gu_values-gu.arsc.flat
    │   │   │   │       ├── values-h320dp-port-v13_values-h320dp-port-v13.arsc.flat
    │   │   │   │       ├── values-h360dp-land-v13_values-h360dp-land-v13.arsc.flat
    │   │   │   │       ├── values-h480dp-land-v13_values-h480dp-land-v13.arsc.flat
    │   │   │   │       ├── values-h550dp-port-v13_values-h550dp-port-v13.arsc.flat
    │   │   │   │       ├── values-h720dp-v13_values-h720dp-v13.arsc.flat
    │   │   │   │       ├── values-hdpi-v4_values-hdpi-v4.arsc.flat
    │   │   │   │       ├── values-hi_values-hi.arsc.flat
    │   │   │   │       ├── values-hr_values-hr.arsc.flat
    │   │   │   │       ├── values-hu_values-hu.arsc.flat
    │   │   │   │       ├── values-hy_values-hy.arsc.flat
    │   │   │   │       ├── values-in_values-in.arsc.flat
    │   │   │   │       ├── values-is_values-is.arsc.flat
    │   │   │   │       ├── values-it_values-it.arsc.flat
    │   │   │   │       ├── values-iw_values-iw.arsc.flat
    │   │   │   │       ├── values-ja_values-ja.arsc.flat
    │   │   │   │       ├── values-ka_values-ka.arsc.flat
    │   │   │   │       ├── values-kk_values-kk.arsc.flat
    │   │   │   │       ├── values-km_values-km.arsc.flat
    │   │   │   │       ├── values-kn_values-kn.arsc.flat
    │   │   │   │       ├── values-ko_values-ko.arsc.flat
    │   │   │   │       ├── values-ky_values-ky.arsc.flat
    │   │   │   │       ├── values-land_values-land.arsc.flat
    │   │   │   │       ├── values-large-v4_values-large-v4.arsc.flat
    │   │   │   │       ├── values-ldltr-v21_values-ldltr-v21.arsc.flat
    │   │   │   │       ├── values-ldrtl-v17_values-ldrtl-v17.arsc.flat
    │   │   │   │       ├── values-lo_values-lo.arsc.flat
    │   │   │   │       ├── values-lt_values-lt.arsc.flat
    │   │   │   │       ├── values-lv_values-lv.arsc.flat
    │   │   │   │       ├── values-mk_values-mk.arsc.flat
    │   │   │   │       ├── values-ml_values-ml.arsc.flat
    │   │   │   │       ├── values-mn_values-mn.arsc.flat
    │   │   │   │       ├── values-mr_values-mr.arsc.flat
    │   │   │   │       ├── values-ms_values-ms.arsc.flat
    │   │   │   │       ├── values-my_values-my.arsc.flat
    │   │   │   │       ├── values-nb_values-nb.arsc.flat
    │   │   │   │       ├── values-ne_values-ne.arsc.flat
    │   │   │   │       ├── values-night-v8_values-night-v8.arsc.flat
    │   │   │   │       ├── values-nl_values-nl.arsc.flat
    │   │   │   │       ├── values-or_values-or.arsc.flat
    │   │   │   │       ├── values-pa_values-pa.arsc.flat
    │   │   │   │       ├── values-pl_values-pl.arsc.flat
    │   │   │   │       ├── values-port_values-port.arsc.flat
    │   │   │   │       ├── values-pt-rBR_values-pt-rBR.arsc.flat
    │   │   │   │       ├── values-pt-rPT_values-pt-rPT.arsc.flat
    │   │   │   │       ├── values-pt_values-pt.arsc.flat
    │   │   │   │       ├── values-ro_values-ro.arsc.flat
    │   │   │   │       ├── values-ru_values-ru.arsc.flat
    │   │   │   │       ├── values-si_values-si.arsc.flat
    │   │   │   │       ├── values-sk_values-sk.arsc.flat
    │   │   │   │       ├── values-sl_values-sl.arsc.flat
    │   │   │   │       ├── values-small-v4_values-small-v4.arsc.flat
    │   │   │   │       ├── values-sq_values-sq.arsc.flat
    │   │   │   │       ├── values-sr_values-sr.arsc.flat
    │   │   │   │       ├── values-sv_values-sv.arsc.flat
    │   │   │   │       ├── values-sw600dp-v13_values-sw600dp-v13.arsc.flat
    │   │   │   │       ├── values-sw_values-sw.arsc.flat
    │   │   │   │       ├── values-ta_values-ta.arsc.flat
    │   │   │   │       ├── values-te_values-te.arsc.flat
    │   │   │   │       ├── values-th_values-th.arsc.flat
    │   │   │   │       ├── values-tl_values-tl.arsc.flat
    │   │   │   │       ├── values-tr_values-tr.arsc.flat
    │   │   │   │       ├── values-uk_values-uk.arsc.flat
    │   │   │   │       ├── values-ur_values-ur.arsc.flat
    │   │   │   │       ├── values-uz_values-uz.arsc.flat
    │   │   │   │       ├── values-v16_values-v16.arsc.flat
    │   │   │   │       ├── values-v17_values-v17.arsc.flat
    │   │   │   │       ├── values-v18_values-v18.arsc.flat
    │   │   │   │       ├── values-v21_values-v21.arsc.flat
    │   │   │   │       ├── values-v22_values-v22.arsc.flat
    │   │   │   │       ├── values-v23_values-v23.arsc.flat
    │   │   │   │       ├── values-v24_values-v24.arsc.flat
    │   │   │   │       ├── values-v25_values-v25.arsc.flat
    │   │   │   │       ├── values-v26_values-v26.arsc.flat
    │   │   │   │       ├── values-v28_values-v28.arsc.flat
    │   │   │   │       ├── values-v31_values-v31.arsc.flat
    │   │   │   │       ├── values-vi_values-vi.arsc.flat
    │   │   │   │       ├── values-w320dp-land-v13_values-w320dp-land-v13.arsc.flat
    │   │   │   │       ├── values-w360dp-port-v13_values-w360dp-port-v13.arsc.flat
    │   │   │   │       ├── values-w480dp-port-v13_values-w480dp-port-v13.arsc.flat
    │   │   │   │       ├── values-w600dp-land-v13_values-w600dp-land-v13.arsc.flat
    │   │   │   │       ├── values-watch-v20_values-watch-v20.arsc.flat
    │   │   │   │       ├── values-watch-v21_values-watch-v21.arsc.flat
    │   │   │   │       ├── values-xlarge-v4_values-xlarge-v4.arsc.flat
    │   │   │   │       ├── values-zh-rCN_values-zh-rCN.arsc.flat
    │   │   │   │       ├── values-zh-rHK_values-zh-rHK.arsc.flat
    │   │   │   │       ├── values-zh-rTW_values-zh-rTW.arsc.flat
    │   │   │   │       ├── values-zu_values-zu.arsc.flat
    │   │   │   │       └── values_values.arsc.flat
    │   │   │   ├── merged_res_blame_folder
    │   │   │   │   └── debug
    │   │   │   │       ├── mergeDebugResources
    │   │   │   │       │   └── out
    │   │   │   │       │       ├── multi-v2
    │   │   │   │       │       │   ├── mergeDebugResources.json
    │   │   │   │       │       │   ├── values-af.json
    │   │   │   │       │       │   ├── values-am.json
    │   │   │   │       │       │   ├── values-ar.json
    │   │   │   │       │       │   ├── values-as.json
    │   │   │   │       │       │   ├── values-az.json
    │   │   │   │       │       │   ├── values-b+es+419.json
    │   │   │   │       │       │   ├── values-b+sr+Latn.json
    │   │   │   │       │       │   ├── values-be.json
    │   │   │   │       │       │   ├── values-bg.json
    │   │   │   │       │       │   ├── values-bn.json
    │   │   │   │       │       │   ├── values-bs.json
    │   │   │   │       │       │   ├── values-ca.json
    │   │   │   │       │       │   ├── values-cs.json
    │   │   │   │       │       │   ├── values-da.json
    │   │   │   │       │       │   ├── values-de.json
    │   │   │   │       │       │   ├── values-el.json
    │   │   │   │       │       │   ├── values-en-rAU.json
    │   │   │   │       │       │   ├── values-en-rCA.json
    │   │   │   │       │       │   ├── values-en-rGB.json
    │   │   │   │       │       │   ├── values-en-rIN.json
    │   │   │   │       │       │   ├── values-en-rXC.json
    │   │   │   │       │       │   ├── values-es-rUS.json
    │   │   │   │       │       │   ├── values-es.json
    │   │   │   │       │       │   ├── values-et.json
    │   │   │   │       │       │   ├── values-eu.json
    │   │   │   │       │       │   ├── values-fa.json
    │   │   │   │       │       │   ├── values-fi.json
    │   │   │   │       │       │   ├── values-fr-rCA.json
    │   │   │   │       │       │   ├── values-fr.json
    │   │   │   │       │       │   ├── values-gl.json
    │   │   │   │       │       │   ├── values-gu.json
    │   │   │   │       │       │   ├── values-h320dp-port-v13.json
    │   │   │   │       │       │   ├── values-h360dp-land-v13.json
    │   │   │   │       │       │   ├── values-h480dp-land-v13.json
    │   │   │   │       │       │   ├── values-h550dp-port-v13.json
    │   │   │   │       │       │   ├── values-h720dp-v13.json
    │   │   │   │       │       │   ├── values-hdpi-v4.json
    │   │   │   │       │       │   ├── values-hi.json
    │   │   │   │       │       │   ├── values-hr.json
    │   │   │   │       │       │   ├── values-hu.json
    │   │   │   │       │       │   ├── values-hy.json
    │   │   │   │       │       │   ├── values-in.json
    │   │   │   │       │       │   ├── values-is.json
    │   │   │   │       │       │   ├── values-it.json
    │   │   │   │       │       │   ├── values-iw.json
    │   │   │   │       │       │   ├── values-ja.json
    │   │   │   │       │       │   ├── values-ka.json
    │   │   │   │       │       │   ├── values-kk.json
    │   │   │   │       │       │   ├── values-km.json
    │   │   │   │       │       │   ├── values-kn.json
    │   │   │   │       │       │   ├── values-ko.json
    │   │   │   │       │       │   ├── values-ky.json
    │   │   │   │       │       │   ├── values-land.json
    │   │   │   │       │       │   ├── values-large-v4.json
    │   │   │   │       │       │   ├── values-ldltr-v21.json
    │   │   │   │       │       │   ├── values-ldrtl-v17.json
    │   │   │   │       │       │   ├── values-lo.json
    │   │   │   │       │       │   ├── values-lt.json
    │   │   │   │       │       │   ├── values-lv.json
    │   │   │   │       │       │   ├── values-mk.json
    │   │   │   │       │       │   ├── values-ml.json
    │   │   │   │       │       │   ├── values-mn.json
    │   │   │   │       │       │   ├── values-mr.json
    │   │   │   │       │       │   ├── values-ms.json
    │   │   │   │       │       │   ├── values-my.json
    │   │   │   │       │       │   ├── values-nb.json
    │   │   │   │       │       │   ├── values-ne.json
    │   │   │   │       │       │   ├── values-night-v8.json
    │   │   │   │       │       │   ├── values-nl.json
    │   │   │   │       │       │   ├── values-or.json
    │   │   │   │       │       │   ├── values-pa.json
    │   │   │   │       │       │   ├── values-pl.json
    │   │   │   │       │       │   ├── values-port.json
    │   │   │   │       │       │   ├── values-pt-rBR.json
    │   │   │   │       │       │   ├── values-pt-rPT.json
    │   │   │   │       │       │   ├── values-pt.json
    │   │   │   │       │       │   ├── values-ro.json
    │   │   │   │       │       │   ├── values-ru.json
    │   │   │   │       │       │   ├── values-si.json
    │   │   │   │       │       │   ├── values-sk.json
    │   │   │   │       │       │   ├── values-sl.json
    │   │   │   │       │       │   ├── values-small-v4.json
    │   │   │   │       │       │   ├── values-sq.json
    │   │   │   │       │       │   ├── values-sr.json
    │   │   │   │       │       │   ├── values-sv.json
    │   │   │   │       │       │   ├── values-sw.json
    │   │   │   │       │       │   ├── values-sw360dp-v13.json
    │   │   │   │       │       │   ├── values-sw600dp-v13.json
    │   │   │   │       │       │   ├── values-ta.json
    │   │   │   │       │       │   ├── values-te.json
    │   │   │   │       │       │   ├── values-th.json
    │   │   │   │       │       │   ├── values-tl.json
    │   │   │   │       │       │   ├── values-tr.json
    │   │   │   │       │       │   ├── values-uk.json
    │   │   │   │       │       │   ├── values-ur.json
    │   │   │   │       │       │   ├── values-uz.json
    │   │   │   │       │       │   ├── values-v16.json
    │   │   │   │       │       │   ├── values-v17.json
    │   │   │   │       │       │   ├── values-v18.json
    │   │   │   │       │       │   ├── values-v21.json
    │   │   │   │       │       │   ├── values-v22.json
    │   │   │   │       │       │   ├── values-v23.json
    │   │   │   │       │       │   ├── values-v24.json
    │   │   │   │       │       │   ├── values-v25.json
    │   │   │   │       │       │   ├── values-v26.json
    │   │   │   │       │       │   ├── values-v28.json
    │   │   │   │       │       │   ├── values-v31.json
    │   │   │   │       │       │   ├── values-vi.json
    │   │   │   │       │       │   ├── values-w320dp-land-v13.json
    │   │   │   │       │       │   ├── values-w360dp-port-v13.json
    │   │   │   │       │       │   ├── values-w480dp-port-v13.json
    │   │   │   │       │       │   ├── values-w600dp-land-v13.json
    │   │   │   │       │       │   ├── values-watch-v20.json
    │   │   │   │       │       │   ├── values-watch-v21.json
    │   │   │   │       │       │   ├── values-xlarge-v4.json
    │   │   │   │       │       │   ├── values-zh-rCN.json
    │   │   │   │       │       │   ├── values-zh-rHK.json
    │   │   │   │       │       │   ├── values-zh-rTW.json
    │   │   │   │       │       │   ├── values-zu.json
    │   │   │   │       │       │   └── values.json
    │   │   │   │       │       └── single
    │   │   │   │       │           ├── layout.json
    │   │   │   │       │           └── mergeDebugResources.json
    │   │   │   │       └── out
    │   │   │   │           ├── multi-v2
    │   │   │   │           │   ├── debug.json
    │   │   │   │           │   ├── values-af.json
    │   │   │   │           │   ├── values-am.json
    │   │   │   │           │   ├── values-ar.json
    │   │   │   │           │   ├── values-as.json
    │   │   │   │           │   ├── values-az.json
    │   │   │   │           │   ├── values-b+es+419.json
    │   │   │   │           │   ├── values-b+sr+Latn.json
    │   │   │   │           │   ├── values-be.json
    │   │   │   │           │   ├── values-bg.json
    │   │   │   │           │   ├── values-bn.json
    │   │   │   │           │   ├── values-bs.json
    │   │   │   │           │   ├── values-ca.json
    │   │   │   │           │   ├── values-cs.json
    │   │   │   │           │   ├── values-da.json
    │   │   │   │           │   ├── values-de.json
    │   │   │   │           │   ├── values-el.json
    │   │   │   │           │   ├── values-en-rAU.json
    │   │   │   │           │   ├── values-en-rCA.json
    │   │   │   │           │   ├── values-en-rGB.json
    │   │   │   │           │   ├── values-en-rIN.json
    │   │   │   │           │   ├── values-en-rXC.json
    │   │   │   │           │   ├── values-es-rUS.json
    │   │   │   │           │   ├── values-es.json
    │   │   │   │           │   ├── values-et.json
    │   │   │   │           │   ├── values-eu.json
    │   │   │   │           │   ├── values-fa.json
    │   │   │   │           │   ├── values-fi.json
    │   │   │   │           │   ├── values-fr-rCA.json
    │   │   │   │           │   ├── values-fr.json
    │   │   │   │           │   ├── values-gl.json
    │   │   │   │           │   ├── values-gu.json
    │   │   │   │           │   ├── values-h320dp-port-v13.json
    │   │   │   │           │   ├── values-h360dp-land-v13.json
    │   │   │   │           │   ├── values-h480dp-land-v13.json
    │   │   │   │           │   ├── values-h550dp-port-v13.json
    │   │   │   │           │   ├── values-h720dp-v13.json
    │   │   │   │           │   ├── values-hdpi-v4.json
    │   │   │   │           │   ├── values-hi.json
    │   │   │   │           │   ├── values-hr.json
    │   │   │   │           │   ├── values-hu.json
    │   │   │   │           │   ├── values-hy.json
    │   │   │   │           │   ├── values-in.json
    │   │   │   │           │   ├── values-is.json
    │   │   │   │           │   ├── values-it.json
    │   │   │   │           │   ├── values-iw.json
    │   │   │   │           │   ├── values-ja.json
    │   │   │   │           │   ├── values-ka.json
    │   │   │   │           │   ├── values-kk.json
    │   │   │   │           │   ├── values-km.json
    │   │   │   │           │   ├── values-kn.json
    │   │   │   │           │   ├── values-ko.json
    │   │   │   │           │   ├── values-ky.json
    │   │   │   │           │   ├── values-land.json
    │   │   │   │           │   ├── values-large-v4.json
    │   │   │   │           │   ├── values-ldltr-v21.json
    │   │   │   │           │   ├── values-ldrtl-v17.json
    │   │   │   │           │   ├── values-lo.json
    │   │   │   │           │   ├── values-lt.json
    │   │   │   │           │   ├── values-lv.json
    │   │   │   │           │   ├── values-mk.json
    │   │   │   │           │   ├── values-ml.json
    │   │   │   │           │   ├── values-mn.json
    │   │   │   │           │   ├── values-mr.json
    │   │   │   │           │   ├── values-ms.json
    │   │   │   │           │   ├── values-my.json
    │   │   │   │           │   ├── values-nb.json
    │   │   │   │           │   ├── values-ne.json
    │   │   │   │           │   ├── values-night-v8.json
    │   │   │   │           │   ├── values-nl.json
    │   │   │   │           │   ├── values-or.json
    │   │   │   │           │   ├── values-pa.json
    │   │   │   │           │   ├── values-pl.json
    │   │   │   │           │   ├── values-port.json
    │   │   │   │           │   ├── values-pt-rBR.json
    │   │   │   │           │   ├── values-pt-rPT.json
    │   │   │   │           │   ├── values-pt.json
    │   │   │   │           │   ├── values-ro.json
    │   │   │   │           │   ├── values-ru.json
    │   │   │   │           │   ├── values-si.json
    │   │   │   │           │   ├── values-sk.json
    │   │   │   │           │   ├── values-sl.json
    │   │   │   │           │   ├── values-small-v4.json
    │   │   │   │           │   ├── values-sq.json
    │   │   │   │           │   ├── values-sr.json
    │   │   │   │           │   ├── values-sv.json
    │   │   │   │           │   ├── values-sw.json
    │   │   │   │           │   ├── values-sw600dp-v13.json
    │   │   │   │           │   ├── values-ta.json
    │   │   │   │           │   ├── values-te.json
    │   │   │   │           │   ├── values-th.json
    │   │   │   │           │   ├── values-tl.json
    │   │   │   │           │   ├── values-tr.json
    │   │   │   │           │   ├── values-uk.json
    │   │   │   │           │   ├── values-ur.json
    │   │   │   │           │   ├── values-uz.json
    │   │   │   │           │   ├── values-v16.json
    │   │   │   │           │   ├── values-v17.json
    │   │   │   │           │   ├── values-v18.json
    │   │   │   │           │   ├── values-v21.json
    │   │   │   │           │   ├── values-v22.json
    │   │   │   │           │   ├── values-v23.json
    │   │   │   │           │   ├── values-v24.json
    │   │   │   │           │   ├── values-v25.json
    │   │   │   │           │   ├── values-v26.json
    │   │   │   │           │   ├── values-v28.json
    │   │   │   │           │   ├── values-v31.json
    │   │   │   │           │   ├── values-vi.json
    │   │   │   │           │   ├── values-w320dp-land-v13.json
    │   │   │   │           │   ├── values-w360dp-port-v13.json
    │   │   │   │           │   ├── values-w480dp-port-v13.json
    │   │   │   │           │   ├── values-w600dp-land-v13.json
    │   │   │   │           │   ├── values-watch-v20.json
    │   │   │   │           │   ├── values-watch-v21.json
    │   │   │   │           │   ├── values-xlarge-v4.json
    │   │   │   │           │   ├── values-zh-rCN.json
    │   │   │   │           │   ├── values-zh-rHK.json
    │   │   │   │           │   ├── values-zh-rTW.json
    │   │   │   │           │   ├── values-zu.json
    │   │   │   │           │   └── values.json
    │   │   │   │           └── single
    │   │   │   │               ├── debug.json
    │   │   │   │               └── layout.json
    │   │   │   ├── merged_shaders
    │   │   │   │   └── debug
    │   │   │   │       └── mergeDebugShaders
    │   │   │   │           └── out
    │   │   │   ├── merged_startup_profile
    │   │   │   │   └── debug
    │   │   │   │       └── mergeDebugStartupProfile
    │   │   │   ├── merged_test_only_native_libs
    │   │   │   │   └── debug
    │   │   │   │       └── mergeDebugNativeLibs
    │   │   │   │           └── out
    │   │   │   ├── mixed_scope_dex_archive
    │   │   │   │   └── debug
    │   │   │   │       └── dexBuilderDebug
    │   │   │   │           └── out
    │   │   │   ├── navigation_json
    │   │   │   │   └── debug
    │   │   │   │       ├── extractDeepLinksDebug
    │   │   │   │       │   └── navigation.json
    │   │   │   │       └── navigation.json
    │   │   │   ├── nested_resources_validation_report
    │   │   │   │   └── debug
    │   │   │   │       └── generateDebugResources
    │   │   │   │           └── nestedResourcesValidationReport.txt
    │   │   │   ├── packaged_manifests
    │   │   │   │   └── debug
    │   │   │   │       ├── AndroidManifest.xml
    │   │   │   │       ├── output-metadata.json
    │   │   │   │       └── processDebugManifestForPackage
    │   │   │   │           ├── AndroidManifest.xml
    │   │   │   │           └── output-metadata.json
    │   │   │   ├── packaged_res
    │   │   │   │   └── debug
    │   │   │   │       ├── color
    │   │   │   │       │   └── bg_nav_item.xml
    │   │   │   │       ├── drawable
    │   │   │   │       │   ├── ic_baseline_add_24.xml
    │   │   │   │       │   ├── ic_baseline_photo_camera_24.xml
    │   │   │   │       │   ├── ic_baseline_photo_library_24.xml
    │   │   │   │       │   ├── ic_minus.xml
    │   │   │   │       │   ├── ic_plus.xml
    │   │   │   │       │   ├── icn_chevron_up.png
    │   │   │   │       │   └── media_pipe_banner.xml
    │   │   │   │       ├── layout
    │   │   │   │       │   ├── activity_introduce.xml
    │   │   │   │       │   ├── activity_main.xml
    │   │   │   │       │   ├── fragment_camera.xml
    │   │   │   │       │   ├── fragment_gallery.xml
    │   │   │   │       │   └── item_gesture_recognizer_result.xml
    │   │   │   │       ├── menu
    │   │   │   │       │   └── menu_bottom_nav.xml
    │   │   │   │       ├── mipmap-hdpi-v4
    │   │   │   │       │   ├── ic_launcher.png
    │   │   │   │       │   └── ic_launcher_round.png
    │   │   │   │       ├── mipmap-mdpi-v4
    │   │   │   │       │   ├── ic_launcher.png
    │   │   │   │       │   └── ic_launcher_round.png
    │   │   │   │       ├── mipmap-xhdpi-v4
    │   │   │   │       │   ├── ic_launcher.png
    │   │   │   │       │   └── ic_launcher_round.png
    │   │   │   │       ├── mipmap-xxhdpi-v4
    │   │   │   │       │   ├── ic_launcher.png
    │   │   │   │       │   └── ic_launcher_round.png
    │   │   │   │       ├── mipmap-xxxhdpi-v4
    │   │   │   │       │   ├── ic_launcher.png
    │   │   │   │       │   └── ic_launcher_round.png
    │   │   │   │       ├── navigation
    │   │   │   │       │   └── nav_graph.xml
    │   │   │   │       ├── packageDebugResources
    │   │   │   │       │   ├── color
    │   │   │   │       │   │   └── bg_nav_item.xml
    │   │   │   │       │   ├── drawable
    │   │   │   │       │   │   ├── ic_baseline_add_24.xml
    │   │   │   │       │   │   ├── ic_baseline_photo_camera_24.xml
    │   │   │   │       │   │   ├── ic_baseline_photo_library_24.xml
    │   │   │   │       │   │   ├── ic_minus.xml
    │   │   │   │       │   │   ├── ic_plus.xml
    │   │   │   │       │   │   ├── icn_chevron_up.png
    │   │   │   │       │   │   └── media_pipe_banner.xml
    │   │   │   │       │   ├── layout
    │   │   │   │       │   │   ├── activity_introduce.xml
    │   │   │   │       │   │   ├── activity_main.xml
    │   │   │   │       │   │   ├── activity_rireki.xml
    │   │   │   │       │   │   ├── item_gesture_recognizer_result.xml
    │   │   │   │       │   │   └── item_rireki.xml
    │   │   │   │       │   ├── menu
    │   │   │   │       │   │   └── menu_introduce.xml
    │   │   │   │       │   ├── mipmap-hdpi-v4
    │   │   │   │       │   │   ├── ic_konomusi.jpg
    │   │   │   │       │   │   ├── ic_launcher.png
    │   │   │   │       │   │   └── ic_launcher_round.png
    │   │   │   │       │   ├── mipmap-mdpi-v4
    │   │   │   │       │   │   ├── ic_launcher.png
    │   │   │   │       │   │   └── ic_launcher_round.png
    │   │   │   │       │   ├── mipmap-xhdpi-v4
    │   │   │   │       │   │   ├── ic_launcher.png
    │   │   │   │       │   │   └── ic_launcher_round.png
    │   │   │   │       │   ├── mipmap-xxhdpi-v4
    │   │   │   │       │   │   ├── ic_launcher.png
    │   │   │   │       │   │   └── ic_launcher_round.png
    │   │   │   │       │   ├── mipmap-xxxhdpi-v4
    │   │   │   │       │   │   ├── ic_launcher.png
    │   │   │   │       │   │   └── ic_launcher_round.png
    │   │   │   │       │   └── values
    │   │   │   │       │       └── values.xml
    │   │   │   │       └── values
    │   │   │   │           └── values.xml
    │   │   │   ├── processed_res
    │   │   │   │   └── debug
    │   │   │   │       ├── out
    │   │   │   │       │   ├── output-metadata.json
    │   │   │   │       │   └── resources-debug.ap_
    │   │   │   │       └── processDebugResources
    │   │   │   │           └── out
    │   │   │   │               ├── output-metadata.json
    │   │   │   │               └── resources-debug.ap_
    │   │   │   ├── project_dex_archive
    │   │   │   │   └── debug
    │   │   │   │       ├── dexBuilderDebug
    │   │   │   │       │   └── out
    │   │   │   │       │       ├── 42bc37ce0960986a6f5d2299be72ca06f8266fb22f83f7850e52bec13b8b0923_0.jar
    │   │   │   │       │       ├── 42bc37ce0960986a6f5d2299be72ca06f8266fb22f83f7850e52bec13b8b0923_1.jar
    │   │   │   │       │       ├── 42bc37ce0960986a6f5d2299be72ca06f8266fb22f83f7850e52bec13b8b0923_2.jar
    │   │   │   │       │       ├── 42bc37ce0960986a6f5d2299be72ca06f8266fb22f83f7850e52bec13b8b0923_3.jar
    │   │   │   │       │       └── com
    │   │   │   │       │           └── google
    │   │   │   │       │               └── mediapipe
    │   │   │   │       │                   └── examples
    │   │   │   │       │                       └── gesturerecognizer
    │   │   │   │       │                           ├── FuriganaView$1.dex
    │   │   │   │       │                           ├── FuriganaView$LineFurigana.dex
    │   │   │   │       │                           ├── FuriganaView$LineNormal.dex
    │   │   │   │       │                           ├── FuriganaView$Span.dex
    │   │   │   │       │                           ├── FuriganaView$TextFurigana.dex
    │   │   │   │       │                           ├── FuriganaView$TextNormal.dex
    │   │   │   │       │                           ├── FuriganaView.dex
    │   │   │   │       │                           ├── GestureRecognizerHelper$Companion.dex
    │   │   │   │       │                           ├── GestureRecognizerHelper$GestureRecognizerListener$DefaultImpls.dex
    │   │   │   │       │                           ├── GestureRecognizerHelper$GestureRecognizerListener.dex
    │   │   │   │       │                           ├── GestureRecognizerHelper$ResultBundle.dex
    │   │   │   │       │                           ├── GestureRecognizerHelper$recognizeVideoFile$1$2.dex
    │   │   │   │       │                           ├── GestureRecognizerHelper.dex
    │   │   │   │       │                           ├── HandGesture.dex
    │   │   │   │       │                           ├── Hurigana.dex
    │   │   │   │       │                           ├── IntroduceActivity.dex
    │   │   │   │       │                           ├── MainActivity$mOnDeviceConnectListener$1.dex
    │   │   │   │       │                           ├── MainActivity$startCapturingImage$1.dex
    │   │   │   │       │                           ├── MainActivity.dex
    │   │   │   │       │                           ├── MainViewModel.dex
    │   │   │   │       │                           ├── ModelUser.dex
    │   │   │   │       │                           ├── OverlayView$Companion.dex
    │   │   │   │       │                           ├── OverlayView$WhenMappings.dex
    │   │   │   │       │                           ├── OverlayView.dex
    │   │   │   │       │                           ├── QuadraticOptimizer.dex
    │   │   │   │       │                           ├── RirekiActivity$loadItems$type$1.dex
    │   │   │   │       │                           ├── RirekiActivity$onCreate$2.dex
    │   │   │   │       │                           ├── RirekiActivity$onCreate$3.dex
    │   │   │   │       │                           ├── RirekiActivity$onCreate$4.dex
    │   │   │   │       │                           ├── RirekiActivity.dex
    │   │   │   │       │                           ├── RirekiAdapter$ViewHolder.dex
    │   │   │   │       │                           ├── RirekiAdapter.dex
    │   │   │   │       │                           ├── databinding
    │   │   │   │       │                           │   ├── ActivityIntroduceBinding.dex
    │   │   │   │       │                           │   ├── ActivityMainBinding.dex
    │   │   │   │       │                           │   ├── ActivityRirekiBinding.dex
    │   │   │   │       │                           │   ├── ItemGestureRecognizerResultBinding.dex
    │   │   │   │       │                           │   └── ItemRirekiBinding.dex
    │   │   │   │       │                           └── fragment
    │   │   │   │       │                               ├── GestureRecognizerResultsAdapter$Companion.dex
    │   │   │   │       │                               ├── GestureRecognizerResultsAdapter$ViewHolder.dex
    │   │   │   │       │                               ├── GestureRecognizerResultsAdapter$updateResults$$inlined$sortedBy$1.dex
    │   │   │   │       │                               ├── GestureRecognizerResultsAdapter$updateResults$$inlined$sortedByDescending$1.dex
    │   │   │   │       │                               └── GestureRecognizerResultsAdapter.dex
    │   │   │   │       └── out
    │   │   │   │           ├── 870aca24b83bd8686df9ae4a04acb54ccd2e1a2dcbf555e19c57570bf28da592_0.jar
    │   │   │   │           ├── 870aca24b83bd8686df9ae4a04acb54ccd2e1a2dcbf555e19c57570bf28da592_1.jar
    │   │   │   │           ├── 870aca24b83bd8686df9ae4a04acb54ccd2e1a2dcbf555e19c57570bf28da592_2.jar
    │   │   │   │           ├── 870aca24b83bd8686df9ae4a04acb54ccd2e1a2dcbf555e19c57570bf28da592_3.jar
    │   │   │   │           └── com
    │   │   │   │               └── google
    │   │   │   │                   └── mediapipe
    │   │   │   │                       └── examples
    │   │   │   │                           └── gesturerecognizer
    │   │   │   │                               ├── BuildConfig.dex
    │   │   │   │                               ├── GestureRecognizerHelper$Companion.dex
    │   │   │   │                               ├── GestureRecognizerHelper$GestureRecognizerListener$DefaultImpls.dex
    │   │   │   │                               ├── GestureRecognizerHelper$GestureRecognizerListener.dex
    │   │   │   │                               ├── GestureRecognizerHelper$ResultBundle.dex
    │   │   │   │                               ├── GestureRecognizerHelper$recognizeVideoFile$1$2.dex
    │   │   │   │                               ├── GestureRecognizerHelper.dex
    │   │   │   │                               ├── HandGesture.dex
    │   │   │   │                               ├── IntroduceActivity.dex
    │   │   │   │                               ├── MainActivity.dex
    │   │   │   │                               ├── MainViewModel.dex
    │   │   │   │                               ├── ModelUser.dex
    │   │   │   │                               ├── OverlayView$Companion.dex
    │   │   │   │                               ├── OverlayView$WhenMappings.dex
    │   │   │   │                               ├── OverlayView.dex
    │   │   │   │                               ├── databinding
    │   │   │   │                               │   ├── ActivityIntroduceBinding.dex
    │   │   │   │                               │   ├── ActivityMainBinding.dex
    │   │   │   │                               │   ├── FragmentCameraBinding.dex
    │   │   │   │                               │   ├── FragmentGalleryBinding.dex
    │   │   │   │                               │   └── ItemGestureRecognizerResultBinding.dex
    │   │   │   │                               └── fragment
    │   │   │   │                                   ├── CameraFragment$Companion.dex
    │   │   │   │                                   ├── CameraFragment$gestureRecognizerResultAdapter$2.dex
    │   │   │   │                                   ├── CameraFragment$special$$inlined$activityViewModels$default$1.dex
    │   │   │   │                                   ├── CameraFragment$special$$inlined$activityViewModels$default$2.dex
    │   │   │   │                                   ├── CameraFragment$special$$inlined$activityViewModels$default$3.dex
    │   │   │   │                                   ├── CameraFragment.dex
    │   │   │   │                                   ├── GalleryFragment.dex
    │   │   │   │                                   ├── GestureRecognizerResultsAdapter$Companion.dex
    │   │   │   │                                   ├── GestureRecognizerResultsAdapter$ViewHolder.dex
    │   │   │   │                                   ├── GestureRecognizerResultsAdapter$updateResults$$inlined$sortedBy$1.dex
    │   │   │   │                                   ├── GestureRecognizerResultsAdapter$updateResults$$inlined$sortedByDescending$1.dex
    │   │   │   │                                   ├── GestureRecognizerResultsAdapter.dex
    │   │   │   │                                   ├── PermissionsFragment$Companion.dex
    │   │   │   │                                   ├── PermissionsFragment$navigateToCamera$1.dex
    │   │   │   │                                   ├── PermissionsFragment.dex
    │   │   │   │                                   └── PermissionsFragmentKt.dex
    │   │   │   ├── runtime_symbol_list
    │   │   │   │   └── debug
    │   │   │   │       ├── R.txt
    │   │   │   │       └── processDebugResources
    │   │   │   │           └── R.txt
    │   │   │   ├── signing_config_versions
    │   │   │   │   └── debug
    │   │   │   │       ├── signing-config-versions.json
    │   │   │   │       └── writeDebugSigningConfigVersions
    │   │   │   │           └── signing-config-versions.json
    │   │   │   ├── source_set_path_map
    │   │   │   │   └── debug
    │   │   │   │       ├── file-map.txt
    │   │   │   │       └── mapDebugSourceSetPaths
    │   │   │   │           └── file-map.txt
    │   │   │   ├── stable_resource_ids_file
    │   │   │   │   └── debug
    │   │   │   │       ├── processDebugResources
    │   │   │   │       │   └── stableIds.txt
    │   │   │   │       └── stableIds.txt
    │   │   │   ├── stripped_native_libs
    │   │   │   │   └── debug
    │   │   │   │       ├── out
    │   │   │   │       │   └── lib
    │   │   │   │       │       ├── arm64-v8a
    │   │   │   │       │       │   ├── libimage_processing_util_jni.so
    │   │   │   │       │       │   ├── libmediapipe_tasks_vision_jni.so
    │   │   │   │       │       │   ├── libtensorflowlite_gpu_jni.so
    │   │   │   │       │       │   └── libtensorflowlite_jni.so
    │   │   │   │       │       ├── armeabi-v7a
    │   │   │   │       │       │   ├── libimage_processing_util_jni.so
    │   │   │   │       │       │   ├── libmediapipe_tasks_vision_jni.so
    │   │   │   │       │       │   ├── libtensorflowlite_gpu_jni.so
    │   │   │   │       │       │   └── libtensorflowlite_jni.so
    │   │   │   │       │       ├── x86
    │   │   │   │       │       │   ├── libimage_processing_util_jni.so
    │   │   │   │       │       │   ├── libmediapipe_tasks_vision_jni.so
    │   │   │   │       │       │   ├── libtensorflowlite_gpu_jni.so
    │   │   │   │       │       │   └── libtensorflowlite_jni.so
    │   │   │   │       │       └── x86_64
    │   │   │   │       │           ├── libimage_processing_util_jni.so
    │   │   │   │       │           ├── libtensorflowlite_gpu_jni.so
    │   │   │   │       │           └── libtensorflowlite_jni.so
    │   │   │   │       └── stripDebugDebugSymbols
    │   │   │   │           └── out
    │   │   │   │               └── lib
    │   │   │   │                   ├── arm64-v8a
    │   │   │   │                   │   ├── libUVCCamera.so
    │   │   │   │                   │   ├── libimage_processing_util_jni.so
    │   │   │   │                   │   ├── libjpeg-turbo1500.so
    │   │   │   │                   │   ├── libmediapipe_tasks_vision_jni.so
    │   │   │   │                   │   ├── libtensorflowlite_gpu_jni.so
    │   │   │   │                   │   ├── libtensorflowlite_jni.so
    │   │   │   │                   │   ├── libusb100.so
    │   │   │   │                   │   └── libuvc.so
    │   │   │   │                   ├── armeabi-v7a
    │   │   │   │                   │   ├── libUVCCamera.so
    │   │   │   │                   │   ├── libimage_processing_util_jni.so
    │   │   │   │                   │   ├── libjpeg-turbo1500.so
    │   │   │   │                   │   ├── libmediapipe_tasks_vision_jni.so
    │   │   │   │                   │   ├── libtensorflowlite_gpu_jni.so
    │   │   │   │                   │   ├── libtensorflowlite_jni.so
    │   │   │   │                   │   ├── libusb100.so
    │   │   │   │                   │   └── libuvc.so
    │   │   │   │                   ├── x86
    │   │   │   │                   │   ├── libimage_processing_util_jni.so
    │   │   │   │                   │   ├── libmediapipe_tasks_vision_jni.so
    │   │   │   │                   │   ├── libtensorflowlite_gpu_jni.so
    │   │   │   │                   │   └── libtensorflowlite_jni.so
    │   │   │   │                   └── x86_64
    │   │   │   │                       ├── libimage_processing_util_jni.so
    │   │   │   │                       ├── libtensorflowlite_gpu_jni.so
    │   │   │   │                       └── libtensorflowlite_jni.so
    │   │   │   ├── sub_project_dex_archive
    │   │   │   │   └── debug
    │   │   │   │       └── dexBuilderDebug
    │   │   │   │           └── out
    │   │   │   ├── symbol_list_with_package_name
    │   │   │   │   └── debug
    │   │   │   │       ├── package-aware-r.txt
    │   │   │   │       └── processDebugResources
    │   │   │   │           └── package-aware-r.txt
    │   │   │   ├── validate_signing_config
    │   │   │   │   └── debug
    │   │   │   │       └── validateSigningDebug
    │   │   │   └── variant_model
    │   │   │       └── debug
    │   │   │           └── out
    │   │   ├── kotlin
    │   │   │   └── compileDebugKotlin
    │   │   │       ├── cacheable
    │   │   │       │   ├── caches-jvm
    │   │   │       │   │   ├── inputs
    │   │   │       │   │   │   ├── source-to-output.tab
    │   │   │       │   │   │   ├── source-to-output.tab.keystream
    │   │   │       │   │   │   ├── source-to-output.tab.keystream.len
    │   │   │       │   │   │   ├── source-to-output.tab.len
    │   │   │       │   │   │   ├── source-to-output.tab.values.at
    │   │   │       │   │   │   ├── source-to-output.tab_i
    │   │   │       │   │   │   └── source-to-output.tab_i.len
    │   │   │       │   │   ├── jvm
    │   │   │       │   │   │   └── kotlin
    │   │   │       │   │   │       ├── class-attributes.tab
    │   │   │       │   │   │       ├── class-attributes.tab.keystream
    │   │   │       │   │   │       ├── class-attributes.tab.keystream.len
    │   │   │       │   │   │       ├── class-attributes.tab.len
    │   │   │       │   │   │       ├── class-attributes.tab.values.at
    │   │   │       │   │   │       ├── class-attributes.tab_i
    │   │   │       │   │   │       ├── class-attributes.tab_i.len
    │   │   │       │   │   │       ├── class-fq-name-to-source.tab
    │   │   │       │   │   │       ├── class-fq-name-to-source.tab.keystream
    │   │   │       │   │   │       ├── class-fq-name-to-source.tab.keystream.len
    │   │   │       │   │   │       ├── class-fq-name-to-source.tab.len
    │   │   │       │   │   │       ├── class-fq-name-to-source.tab.values.at
    │   │   │       │   │   │       ├── class-fq-name-to-source.tab_i
    │   │   │       │   │   │       ├── class-fq-name-to-source.tab_i.len
    │   │   │       │   │   │       ├── constants.tab
    │   │   │       │   │   │       ├── constants.tab.keystream
    │   │   │       │   │   │       ├── constants.tab.keystream.len
    │   │   │       │   │   │       ├── constants.tab.len
    │   │   │       │   │   │       ├── constants.tab.values.at
    │   │   │       │   │   │       ├── constants.tab_i
    │   │   │       │   │   │       ├── constants.tab_i.len
    │   │   │       │   │   │       ├── internal-name-to-source.tab
    │   │   │       │   │   │       ├── internal-name-to-source.tab.keystream
    │   │   │       │   │   │       ├── internal-name-to-source.tab.keystream.len
    │   │   │       │   │   │       ├── internal-name-to-source.tab.len
    │   │   │       │   │   │       ├── internal-name-to-source.tab.values.at
    │   │   │       │   │   │       ├── internal-name-to-source.tab_i
    │   │   │       │   │   │       ├── internal-name-to-source.tab_i.len
    │   │   │       │   │   │       ├── java-sources-proto-map.tab
    │   │   │       │   │   │       ├── java-sources-proto-map.tab.keystream
    │   │   │       │   │   │       ├── java-sources-proto-map.tab.keystream.len
    │   │   │       │   │   │       ├── java-sources-proto-map.tab.len
    │   │   │       │   │   │       ├── java-sources-proto-map.tab.values.at
    │   │   │       │   │   │       ├── java-sources-proto-map.tab_i
    │   │   │       │   │   │       ├── java-sources-proto-map.tab_i.len
    │   │   │       │   │   │       ├── proto.tab
    │   │   │       │   │   │       ├── proto.tab.keystream
    │   │   │       │   │   │       ├── proto.tab.keystream.len
    │   │   │       │   │   │       ├── proto.tab.len
    │   │   │       │   │   │       ├── proto.tab.values.at
    │   │   │       │   │   │       ├── proto.tab_i
    │   │   │       │   │   │       ├── proto.tab_i.len
    │   │   │       │   │   │       ├── source-to-classes.tab
    │   │   │       │   │   │       ├── source-to-classes.tab.keystream
    │   │   │       │   │   │       ├── source-to-classes.tab.keystream.len
    │   │   │       │   │   │       ├── source-to-classes.tab.len
    │   │   │       │   │   │       ├── source-to-classes.tab.values.at
    │   │   │       │   │   │       ├── source-to-classes.tab_i
    │   │   │       │   │   │       ├── source-to-classes.tab_i.len
    │   │   │       │   │   │       ├── subtypes.tab
    │   │   │       │   │   │       ├── subtypes.tab.keystream
    │   │   │       │   │   │       ├── subtypes.tab.keystream.len
    │   │   │       │   │   │       ├── subtypes.tab.len
    │   │   │       │   │   │       ├── subtypes.tab.values.at
    │   │   │       │   │   │       ├── subtypes.tab_i
    │   │   │       │   │   │       ├── subtypes.tab_i.len
    │   │   │       │   │   │       ├── supertypes.tab
    │   │   │       │   │   │       ├── supertypes.tab.keystream
    │   │   │       │   │   │       ├── supertypes.tab.keystream.len
    │   │   │       │   │   │       ├── supertypes.tab.len
    │   │   │       │   │   │       ├── supertypes.tab.values.at
    │   │   │       │   │   │       ├── supertypes.tab_i
    │   │   │       │   │   │       └── supertypes.tab_i.len
    │   │   │       │   │   └── lookups
    │   │   │       │   │       ├── counters.tab
    │   │   │       │   │       ├── file-to-id.tab
    │   │   │       │   │       ├── file-to-id.tab.keystream
    │   │   │       │   │       ├── file-to-id.tab.keystream.len
    │   │   │       │   │       ├── file-to-id.tab.len
    │   │   │       │   │       ├── file-to-id.tab.values.at
    │   │   │       │   │       ├── file-to-id.tab_i
    │   │   │       │   │       ├── file-to-id.tab_i.len
    │   │   │       │   │       ├── id-to-file.tab
    │   │   │       │   │       ├── id-to-file.tab.keystream
    │   │   │       │   │       ├── id-to-file.tab.keystream.len
    │   │   │       │   │       ├── id-to-file.tab.len
    │   │   │       │   │       ├── id-to-file.tab.values.at
    │   │   │       │   │       ├── id-to-file.tab_i
    │   │   │       │   │       ├── id-to-file.tab_i.len
    │   │   │       │   │       ├── lookups.tab
    │   │   │       │   │       ├── lookups.tab.keystream
    │   │   │       │   │       ├── lookups.tab.keystream.len
    │   │   │       │   │       ├── lookups.tab.len
    │   │   │       │   │       ├── lookups.tab.values.at
    │   │   │       │   │       ├── lookups.tab_i
    │   │   │       │   │       └── lookups.tab_i.len
    │   │   │       │   └── last-build.bin
    │   │   │       └── local-state
    │   │   │           └── build-history.bin
    │   │   ├── outputs
    │   │   │   └── logs
    │   │   │       └── manifest-merger-debug-report.txt
    │   │   ├── snapshot
    │   │   │   └── kotlin
    │   │   └── tmp
    │   │       ├── compileDebugJavaWithJavac
    │   │       │   ├── compileTransaction
    │   │       │   │   ├── backup-dir
    │   │       │   │   └── stash-dir
    │   │       │   │       └── ModelUser.class.uniqueId0
    │   │       │   └── previous-compilation-data.bin
    │   │       └── kotlin-classes
    │   │           └── debug
    │   │               ├── META-INF
    │   │               │   └── app_debug.kotlin_module
    │   │               └── com
    │   │                   └── google
    │   │                       └── mediapipe
    │   │                           └── examples
    │   │                               └── gesturerecognizer
    │   │                                   ├── GestureRecognizerHelper$Companion.class
    │   │                                   ├── GestureRecognizerHelper$GestureRecognizerListener$DefaultImpls.class
    │   │                                   ├── GestureRecognizerHelper$GestureRecognizerListener.class
    │   │                                   ├── GestureRecognizerHelper$ResultBundle.class
    │   │                                   ├── GestureRecognizerHelper$recognizeVideoFile$1$2.class
    │   │                                   ├── GestureRecognizerHelper.class
    │   │                                   ├── HandGesture.class
    │   │                                   ├── IntroduceActivity.class
    │   │                                   ├── MainActivity$mOnDeviceConnectListener$1.class
    │   │                                   ├── MainActivity$startCapturingImage$1.class
    │   │                                   ├── MainActivity.class
    │   │                                   ├── MainViewModel.class
    │   │                                   ├── OverlayView$Companion.class
    │   │                                   ├── OverlayView$WhenMappings.class
    │   │                                   ├── OverlayView.class
    │   │                                   ├── RirekiActivity$loadItems$type$1.class
    │   │                                   ├── RirekiActivity$onCreate$2.class
    │   │                                   ├── RirekiActivity$onCreate$3.class
    │   │                                   ├── RirekiActivity$onCreate$4.class
    │   │                                   ├── RirekiActivity.class
    │   │                                   ├── RirekiAdapter$ViewHolder.class
    │   │                                   ├── RirekiAdapter.class
    │   │                                   └── fragment
    │   │                                       ├── GestureRecognizerResultsAdapter$Companion.class
    │   │                                       ├── GestureRecognizerResultsAdapter$ViewHolder.class
    │   │                                       ├── GestureRecognizerResultsAdapter$updateResults$$inlined$sortedBy$1.class
    │   │                                       ├── GestureRecognizerResultsAdapter$updateResults$$inlined$sortedByDescending$1.class
    │   │                                       └── GestureRecognizerResultsAdapter.class
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
    │           │   └── com
    │           │       └── google
    │           │           └── mediapipe
    │           │               └── examples
    │           │                   └── gesturerecognizer
    │           │                       ├── FuriganaView.java
    │           │                       ├── GestureRecognizerHelper.kt
    │           │                       ├── HandGesture.kt
    │           │                       ├── Hurigana.java
    │           │                       ├── IntroduceActivity.kt
    │           │                       ├── MainActivity.kt
    │           │                       ├── MainViewModel.kt
    │           │                       ├── ModelUser.java
    │           │                       ├── OverlayView.kt
    │           │                       ├── QuadraticOptimizer.java
    │           │                       ├── RirekiActivity.kt
    │           │                       ├── RirekiAdapter.kt
    │           │                       └── fragment
    │           │                           └── GestureRecognizerResultsAdapter.kt
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
