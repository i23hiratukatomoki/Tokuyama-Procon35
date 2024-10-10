package com.google.mediapipe.examples.gesturerecognizer

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Rect
import android.hardware.usb.UsbDevice
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Surface
import android.view.View
import android.view.View.OnLongClickListener
import android.widget.Button
import android.widget.CompoundButton
import android.widget.ImageButton
import android.widget.Toast
import android.widget.ToggleButton
import androidx.lifecycle.lifecycleScope
import com.google.mediapipe.examples.gesturerecognizer.fragment.GestureRecognizerResultsAdapter
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import com.serenegiant.common.BaseActivity
import com.serenegiant.usb_libuvccamera.CameraDialog
import com.serenegiant.usb_libuvccamera.CameraDialog.CameraDialogParent
import com.serenegiant.usb_libuvccamera.LibUVCCameraUSBMonitor
import com.serenegiant.usb_libuvccamera.UVCCamera
import com.serenegiant.usbcameracommon.UVCCameraHandler
import com.serenegiant.widget.CameraViewInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.math.max
import kotlin.math.min

class MainActivity : BaseActivity(), CameraDialogParent, GestureRecognizerHelper.GestureRecognizerListener {
    val DEBUG = true
    val TAG = "CameraActivity"
    private val PREVIEW_WIDTH = 1920 //取得する映像の横幅
    private val PREVIEW_HEIGHT = 1080 //取得する映像の縦幅
    private val PREVIEW_MODE = UVCCamera.FRAME_FORMAT_MJPEG

    private var mUSBMonitor: LibUVCCameraUSBMonitor? = null
    private var mCameraHandler: UVCCameraHandler? = null
    private var mUVCCameraView: CameraViewInterface? = null
    private var mCameraButton: ToggleButton? = null //UVCcameraのON・OFF切り替えボタン
    //private var mCaptureButton: ImageButton? = null //撮影用ボタン
    private var mPicture: Bitmap? = null //撮影した画像が入るBitmap

    private lateinit var backgroundExecutor: ExecutorService //ジェスチャー認識のためのスレッド
    private lateinit var gestureRecognizerHelper: GestureRecognizerHelper
    // アダプターのインスタンスを保持するための変数
    private lateinit var gestureRecognizerResultAdapter: GestureRecognizerResultsAdapter
    private lateinit var overlay: OverlayView //実際のハンドジェスチャー認識による手のマークアップはこのオーバーレイ上で行っている
    private var isCapturing = false

    // HandGestureのインスタンス
    val hanges = HandGesture()

    // Width: 2880, Height: 1700
    private lateinit var beforecutbitmap : Bitmap

    private val name_ja = arrayOf(
        "-1",
        "アメンボ",
        "アリ",
        "オサムシ",
        "ガ",
        "カゲロウ",
        "カブトムシ",
        "カマキリ",
        "カミキリムシ",
        "カメムシ",
        "カワゲラ",
        "クモ",
        "クワガタムシ",
        "ゲンゴロウ",
        "コガネムシ",
        "ゴキブリ",
        "セミ",
        "ゾウムシ",
        "タガメ",
        "タマムシ",
        "ダンゴムシ",
        "チョウ",
        "テントウムシ",
        "トンボ",
        "ナナフシ",
        "ハサミムシ",
        "ハチ",
        "バッタ",
        "ハンミョウ",
        "ホタル"
    )

    private val name_ex = arrayOf(
        "amenbo",
        "ari",
        "osamushi",
        "ga",
        "kagerou",
        "kabutomusi",
        "kamakiri",
        "kamikirimusi",
        "kamemusi",
        "kawagera",
        "kumo",
        "kuwagatamusi",
        "gengorou",
        "koganemushi",
        "gokiburi",
        "semi",
        "zoumshi",
        "tagame",
        "tamamushi",
        "dangomushi",
        "tyou",
        "tentoumushi",
        "tonbo",
        "nanafushi",
        "hasamimushi",
        "hati",
        "batta",
        "hanmyou",
        "hotaru"
    )

    @SuppressLint("MissingPermission", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (DEBUG) {
            Log.v(TAG, "onCreate:")
        }
        setContentView(R.layout.activity_main)

        mCameraButton = findViewById<View>(R.id.camera_button) as ToggleButton
        mCameraButton!!.setOnCheckedChangeListener(mOnCheckedChangeListener)

        //mCaptureButton = findViewById<View>(R.id.capture_button) as ImageButton
        //mCaptureButton!!.setOnClickListener { v: View? -> captureItem() }
        //mCaptureButton!!.setVisibility(View.INVISIBLE)

        val view = findViewById<View>(R.id.camera_view)
        view.setOnLongClickListener(mOnLongClickListener)
        mUVCCameraView = view as CameraViewInterface

        mUSBMonitor = LibUVCCameraUSBMonitor(this, mOnDeviceConnectListener)
        mCameraHandler = UVCCameraHandler.createHandler(
            this,
            mUVCCameraView,
            2,
            PREVIEW_WIDTH,
            PREVIEW_HEIGHT,
            PREVIEW_MODE
        )

        //ジェスチャー認識用バックグラウンド処理を初期化
        backgroundExecutor = Executors.newSingleThreadExecutor()
        backgroundExecutor.execute {
            gestureRecognizerHelper = GestureRecognizerHelper(
                context = this@MainActivity,
                runningMode = RunningMode.LIVE_STREAM,
                gestureRecognizerListener = this
            )
            gestureRecognizerHelper.setResultListener(this)
            // リスナーを設定
            gestureRecognizerHelper.setupGestureRecognizer()
        }

        // RecyclerViewの初期化
        //val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        gestureRecognizerResultAdapter = GestureRecognizerResultsAdapter()
        //recyclerView.adapter = gestureRecognizerResultAdapter

        // RecyclerView のレイアウトマネージャーを設定
        //recyclerView.layoutManager = LinearLayoutManager(this)

        // オーバーレイの初期化
        overlay = findViewById(R.id.overlay)

        val buttonNavigate = findViewById<Button>(R.id.buttonNavigate)

        // ボタンがクリックされた時にRirekiActivityに遷移
        buttonNavigate.setOnClickListener {
            val intent = Intent(this, RirekiActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        if (DEBUG) {
            Log.v(TAG, "onStart:")
        }
        //トーストでの表示はここしかいじっていません。
        //「『OFF』ボタンをおしてカメラをえらびます」
        Toast.makeText(this@MainActivity, "『OFF』ボタンをおして\nカメラをえらびます", Toast.LENGTH_LONG)
            .show()

        checkPermissionCamera()
        mUSBMonitor!!.register()
    }

    override fun onStop() {
        isCapturing = false // ループ停止
        if (DEBUG) {
            Log.v(TAG, "onStop:")
        }
        queueEvent({ mCameraHandler!!.close() }, 0)
        if (mUVCCameraView != null) mUVCCameraView!!.onPause()
        setCameraButton(false)
        //mCaptureButton!!.setVisibility(View.INVISIBLE)
        mUSBMonitor!!.unregister()
        //ジェスチャー認識用バックグラウンド処理を初期化
        backgroundExecutor = Executors.newSingleThreadExecutor()
        backgroundExecutor.execute {
            gestureRecognizerHelper = GestureRecognizerHelper(
                context = this@MainActivity,
                runningMode = RunningMode.LIVE_STREAM,
                gestureRecognizerListener = this
            )
            gestureRecognizerHelper.setResultListener(this)
            // リスナーを設定
            gestureRecognizerHelper.setupGestureRecognizer()
        }

        // RecyclerViewの初期化
        //val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        gestureRecognizerResultAdapter = GestureRecognizerResultsAdapter()
        //recyclerView.adapter = gestureRecognizerResultAdapter

        // RecyclerView のレイアウトマネージャーを設定
        //recyclerView.layoutManager = LinearLayoutManager(this)

        // オーバーレイの初期化
        overlay = findViewById(R.id.overlay)
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        isCapturing = false // ループ停止
    }

    public override fun onDestroy() {
        if (DEBUG) {
            Log.v(TAG, "onDestroy:")
        }
        if (mCameraHandler != null) {
            mCameraHandler!!.release()
            mCameraHandler = null
        }
        if (mUSBMonitor != null) {
            mUSBMonitor!!.destroy()
            mUSBMonitor = null
        }
        mUVCCameraView = null
        mCameraButton = null
        //mCaptureButton = null

        // ジェスチャー認識用スレッドを破棄
        backgroundExecutor.shutdown()
        backgroundExecutor.awaitTermination(
            Long.MAX_VALUE, TimeUnit.NANOSECONDS
        )
        super.onDestroy()
    }

    private val mOnCheckedChangeListener =
        CompoundButton.OnCheckedChangeListener { compoundButton, isChecked ->
            if (compoundButton.id == R.id.camera_button) {
                if (isChecked && !mCameraHandler!!.isOpened()) {
                    CameraDialog.showDialog(this@MainActivity)
                } else {
                    mCameraHandler!!.close()
                    //mCaptureButton!!.setVisibility(View.INVISIBLE)
                    setCameraButton(false)
                }
            }
        }

    override fun checkPermissionResult(requestCode: Int, permission: String, result: Boolean) {
        super.checkPermissionResult(requestCode, permission, result)
        if (!result && permission != null) {
            setCameraButton(false)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG, "onResume")
        if (mUVCCameraView != null) {
            mUVCCameraView!!.onResume()
            startCapturingImage()
        }
    }

    //ジェスチャー認識を行うメソッド
    private fun startCapturingImage() {
        if(DEBUG) {
            Log.v(TAG, "startCapturingImageメソッドが実行されました")
        }
        isCapturing = true
        //コルーチンとやらを使用して非同期に画像をキャプチャするらしい(lifecycleScope)
        lifecycleScope.launch(Dispatchers.IO) {
            while (isCapturing && mUVCCameraView != null) {
                mPicture = mUVCCameraView!!.captureStillImage()
                if (mPicture != null) {
                    beforecutbitmap = mPicture!!//hanges.trimming(bitmap)
                    //変換メソッドを呼び出し、Bitmap型のmPictureを変換
                    //imageProxy = bitmapToImageProxy(mPicture!!)
                    if(DEBUG) {
                        Log.v(TAG, "recognizeHandメソッドが呼び出されます")
                    }
                    backgroundExecutor.execute{
                        recognizeHand(mPicture!!)
                    }
                    Log.v(TAG, "ジェスチャー認識を行いました")
                } else {
                    Log.e(TAG, "画像キャプチャに失敗しました")
                }
                //500ミリ秒ごとに認識を行うように設定している
                delay(500)
            }
        }
    }

    // このメソッドは呼び出される際にバックグラウンド実行されている。
    /*
    private fun recognizeHand(imageProxy: ImageProxy) {
        Log.d(TAG, "startCaptureImage -> recognizeHandが実行されました")
        if (!isGestureRecognizerInitialized) {
            Log.v(TAG, "GestureRecognizer is not initialized yet.")
            imageProxy.close()
            return
        }

        try {
            Log.d(TAG, "recognizeHandメソッドを実行")
            /*
            val bitmap = gestureRecognizerHelper.recognizeLiveStream(
                imageProxy = imageProxy)
            */
            val bitmap = mPicture?.let { gestureRecognizerHelper.recognizeLiveStream(it) }
        } catch (e: Exception) {
            Log.e(TAG, "Error in recognizeHand: ${e.message}")
        } finally {
            imageProxy.close()
        }
    }
    */
    private fun recognizeHand(bitmap: Bitmap) {

        Log.d(TAG, "startCaptureImage -> recognizeHandが実行されました")
        try {
            Log.d(TAG, "recognizeHandメソッドを実行")

            bitmap.let { gestureRecognizerHelper.recognizeLiveStream(it) }

        } catch (e: Exception) {
            Log.e(TAG, "Error in recognizeHand: ${e.message}")
        } finally {
            //imageProxy.close()
        }
    }

    //画面下部のカメラボタンを押した時の処理(Bitmapで画像を受け取るだけで別に特に何をするというメソッドではない)
    private fun captureItem() {
        if (mUVCCameraView != null) {
            // 非同期で実行する処理
            Thread {
                mPicture = mUVCCameraView!!.captureStillImage()
                runOnUiThread {
                    if (mPicture != null) {
                        Log.v(TAG, "Success: capture UVCcameraView")
                        Toast.makeText(
                            this@MainActivity,
                            "Success: capture UVCcameraView",
                            Toast.LENGTH_SHORT
                        ).show()
                        //insectText.setText("Success: capture mPicture");
                        //String insect_name = Arrays.toString(ModelUser.answer(this, mPicture));
                        //insectText.setText(insect_name);
                    } else {
                        Log.v(TAG, "ERROR: capture UVCcameraView")
                        Toast.makeText(
                            this@MainActivity,
                            "ERROR: capture UVCcameraView",
                            Toast.LENGTH_SHORT
                        ).show()
                        //insectText.setText("ERROR: capture mPicture");
                    }
                }
            }.start()
        } else {
            Log.v(
                TAG,
                "カメラの初期化が行われていなかったためUSBカメラからの画像の取得に失敗しました"
            )
        }
    }

    private val mOnLongClickListener = OnLongClickListener { view ->
        if (view.id == R.id.camera_view) {
            if (mCameraHandler!!.isOpened()) {
                if (checkPermissionWriteExternalStorage()) {
                    mCameraHandler!!.captureStill()
                }
                return@OnLongClickListener true
            }
        }
        false
    }

    private fun setCameraButton(isOn: Boolean) {
        runOnUiThread({
            if (mCameraButton != null) {
                try {
                    mCameraButton!!.setOnCheckedChangeListener(null)
                    mCameraButton!!.setChecked(isOn)
                } finally {
                    mCameraButton!!.setOnCheckedChangeListener(mOnCheckedChangeListener)
                }
            }
            /*
            if (!isOn && mCaptureButton != null) {
                mCaptureButton!!.setVisibility(View.INVISIBLE)
            }
            */
        }, 0)
    }

    private var mSurface: Surface? = null
    private fun startPreview() {
        val st = mUVCCameraView!!.getSurfaceTexture()
        if (mSurface != null) {
            mSurface!!.release()
        }
        mSurface = Surface(st)
        mCameraHandler!!.startPreview(mSurface)
        //runOnUiThread { mCaptureButton!!.setVisibility(View.VISIBLE) }
    }

    private val mOnDeviceConnectListener: LibUVCCameraUSBMonitor.OnDeviceConnectListener =
        object : LibUVCCameraUSBMonitor.OnDeviceConnectListener {
            override fun onAttach(device: UsbDevice) {
                Toast.makeText(this@MainActivity, "USB_DEVICE_ATTACHED", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onDettach(device: UsbDevice) {
                Toast.makeText(this@MainActivity, "USB_DEVICE_DETACHED", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onConnect(
                device: UsbDevice,
                ctrlBlock: LibUVCCameraUSBMonitor.UsbControlBlock,
                createNew: Boolean
            ) {
                if (DEBUG) {
                    Log.v(TAG, "onConnect")
                }
                mCameraHandler!!.open(ctrlBlock)
                startPreview()
            }

            override fun onDisconnect(
                device: UsbDevice,
                ctrlBlock: LibUVCCameraUSBMonitor.UsbControlBlock
            ) {
                if (DEBUG) {
                    Log.v(TAG, "onDisconnect:")
                }
                if (mCameraHandler != null) {
                    mCameraHandler!!.close()
                    setCameraButton(false)
                }
            }

            override fun onCancel(device: UsbDevice) {}
        }

    override fun getUSBMonitor(): LibUVCCameraUSBMonitor {
        return mUSBMonitor!!
    }

    override fun onDialogResult(canceled: Boolean) {}

    override fun requireContext(): Context {
        TODO("Not yet implemented")
    }

    override fun onError(error: String, errorCode: Int) {
        runOnUiThread {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    //abstract fun onResults(resultBundle: GestureRecognizerHelper.ResultBundle)
    override fun onResults(resultBundle: GestureRecognizerHelper.ResultBundle) {
        // 結果の取得
        val gestureCategories = resultBundle.results.first().gestures()
        if (gestureCategories.isNotEmpty()) {
            gestureRecognizerResultAdapter.updateResults(gestureCategories.first())

            // ハンドサインをしているか
            val isp = gestureCategories.any { gesture ->
                gesture.get(0).categoryName() == "Pointing_Up"
            }
            Log.d("bitmapwh", beforecutbitmap.width.toString())
            Log.d("bitmapwh", beforecutbitmap.height.toString())
            // val landmark_x = resultBundle.results.first().landmarks().get(0).get(8).x()
            // val landmark_y = resultBundle.results.first().landmarks().get(0).get(8).y()
            hanges.setResults(resultBundle.results.first())
            hanges.handgesture(isp)

            if (hanges.currentIsTake[1]){
                val aftercutbitmap = trimming(beforecutbitmap, hanges.currentPicrec)
                // BitmapをMediaStoreに保存 戻り値は画像名
                //val musilabel = "カブトムシ"//name_ja[(0..29).random()/*ModelUser.answer(this, aftercutbitmap)*/]
                val musilabel = name_ja[ModelUser.answer(this, aftercutbitmap)]
                if(false/*musilabel == "-1"*/){
                    hanges.reset_rec()
                }else{
                    val uri_name = saveBitmapToMediaStore(aftercutbitmap, musilabel)
                    // mCameraHandler!!.captureStill()
                    navigateToIntroduceActivity(uri_name)
                }
            }
        } else {
            gestureRecognizerResultAdapter.updateResults(emptyList())
        }
        runOnUiThread {
            // オーバーレイへの結果の設定
            overlay.setResults(
                resultBundle.results.first(),
                resultBundle.inputImageHeight,
                resultBundle.inputImageWidth,
                RunningMode.LIVE_STREAM
            )
            // 強制的に再描画
            overlay.invalidate()
        }
        Log.v(TAG, "Results: ${resultBundle.results}")
    }
    /***********************************追加関数****************************************/
    // SharedPreferencesにテキストを保存する関数
    private fun saveTextToSharedPreferences(text: String) {
        val sharedPreferences = getSharedPreferences("RirekiPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("NEW_ITEM", text)
        editor.apply()  // 保存
    }
    // Bitmapを指定範囲でトリミングする関数
    private fun cropBitmap(bitmap: Bitmap, cropRect: Rect): Bitmap {
        return Bitmap.createBitmap(
            bitmap,
            cropRect.left,
            cropRect.top,
            cropRect.width(),
            cropRect.height()
        )
    }

    // カメラの画像をトリミング()
    private fun trimming(bitmap: Bitmap, picrec: FloatArray): Bitmap {
        // トリミング範囲を指定
        val MAXSIZE_X = 2880
        val MAXSIZE_Y = 1700
        val MINSIZE = 0
        val EXPANTION = intArrayOf(50, 50)
        val l = max((picrec[0]*MAXSIZE_X).toInt()-EXPANTION[0],MINSIZE)
        val t = max((picrec[1]*MAXSIZE_Y).toInt()-EXPANTION[1],MINSIZE)
        val r = min((picrec[2]*MAXSIZE_X).toInt()+EXPANTION[0],MAXSIZE_X)
        val b = min((picrec[3]*MAXSIZE_Y).toInt()+EXPANTION[1],MAXSIZE_Y)
        val cropRect = Rect(l, t, r, b)
        return cropBitmap(bitmap, cropRect)
    }
    // 現在の日付を "yyyyMMdd" フォーマットで取得する関数
    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        return dateFormat.format(Date())
    }
    // putExtraでuriファイル名の送信と画面遷移
    private fun navigateToIntroduceActivity(uri_name: String) {
        val intent = Intent(this, IntroduceActivity::class.java)
        // URIをIntentに渡す
        //直接渡す方法
        //intent.putExtra("image_uri", uri.toString())
        //MediaStoreに保存した画像の名前を渡す方法
        intent.putExtra("image_uri", uri_name)
        startActivity(intent)
    }

    private fun saveBitmapToMediaStore(bitmap: Bitmap, musilabel: String): String {
        val imageName = musilabel + "_" + getCurrentDate() // 画像名を生成
        val contentResolver = this.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "$imageName.jpg") // 名前を指定
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES) // 保存場所
        }

        // ファイル名を履歴のリストに保存
        saveTextToSharedPreferences(imageName)

        // MediaStoreに新しいエントリを作成してUriを取得
        val imageUri: Uri? = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        if (imageUri != null) {
            // Uriに対応するOutputStreamを取得してBitmapを保存
            val outputStream: OutputStream? = contentResolver.openOutputStream(imageUri)
            outputStream?.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
        } else {
            Log.e("SaveImage", "Failed to create new MediaStore entry.")
        }
        return imageName
    }
    /**************************************************************************/
}