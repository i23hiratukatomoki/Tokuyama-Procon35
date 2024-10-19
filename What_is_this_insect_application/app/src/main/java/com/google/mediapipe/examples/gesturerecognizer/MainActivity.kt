package com.google.mediapipe.examples.gesturerecognizer

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Rect
import android.hardware.usb.UsbDevice
import android.media.MediaPlayer
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
import android.widget.Toast
import android.widget.ToggleButton
import androidx.lifecycle.lifecycleScope
import com.google.mediapipe.tasks.vision.core.RunningMode
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
    private var mPicture: Bitmap? = null //撮影した画像が入るBitmap

    private lateinit var backgroundExecutor: ExecutorService //ジェスチャー認識のためのスレッド
    private lateinit var gestureRecognizerHelper: GestureRecognizerHelper// mediapipeを使用できる
    private lateinit var overlay: OverlayView //実際のハンドジェスチャー認識による手のマークアップはこのオーバーレイ上で行っている
    private var isCapturing = false

    // HandGestureのインスタンス
    val hanges = HandGesture()

    // bitmapを取得
    private lateinit var beforecutbitmap : Bitmap

    private lateinit var sound1: MediaPlayer
    private lateinit var sound2: MediaPlayer

    private var isSound1 = false
    private var isSound2 = false

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

    @SuppressLint("MissingPermission", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (DEBUG) {
            Log.v(TAG, "onCreate:")
        }
        setContentView(R.layout.activity_main)

        // 効果音ファイルをロード
        sound1 = MediaPlayer.create(this, R.raw.hit)
        sound2 = MediaPlayer.create(this, R.raw.over)

        // 効果音を再生する
        // playSoundEffect()

        mCameraButton = findViewById<View>(R.id.camera_button) as ToggleButton
        mCameraButton!!.setOnCheckedChangeListener(mOnCheckedChangeListener)

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

        // オーバーレイの初期化
        overlay = findViewById(R.id.overlay)

        val rirekiNavigate = findViewById<Button>(R.id.rirekiNavigate)
        // ボタンがクリックされた時にRirekiActivityに遷移
        rirekiNavigate.setOnClickListener {
            val intent = Intent(this, RirekiActivity::class.java)
            startActivity(intent)
        }
        val helpNavigate = findViewById<Button>(R.id.helpNavigate)
        // ボタンがクリックされた時にHelpActivityに遷移
        helpNavigate.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            intent.putExtra("layoutResId", R.layout.activity_help_screen1)  // レイアウトリソースIDを渡す
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
        // メディアプレーヤーのリソースを解放
        sound1.release()
        sound2.release()
    }

    private val mOnCheckedChangeListener =
        CompoundButton.OnCheckedChangeListener { compoundButton, isChecked ->
            if (compoundButton.id == R.id.camera_button) {
                if (isChecked && !mCameraHandler!!.isOpened()) {
                    CameraDialog.showDialog(this@MainActivity)
                } else {
                    mCameraHandler!!.close()
                    setCameraButton(false)
                }
            }
        }

    override fun checkPermissionResult(requestCode: Int, permission: String, result: Boolean) {
        super.checkPermissionResult(requestCode, permission, result)
        if (!result) {
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
                    beforecutbitmap = mPicture!!
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
                //400ミリ秒ごとに認識を行うように設定している
                delay(400)
            }
        }
    }

    private fun recognizeHand(bitmap: Bitmap) {

        Log.d(TAG, "startCaptureImage -> recognizeHandが実行されました")
        try {
            Log.d(TAG, "recognizeHandメソッドを実行")

            bitmap.let { gestureRecognizerHelper.recognizeLiveStream(it) }

        } catch (e: Exception) {
            Log.e(TAG, "Error in recognizeHand: ${e.message}")
        } finally {
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
        val results = resultBundle.results.first()
        val gestureCategories = results.gestures()
        // resultsをセット
        if (gestureCategories.isNotEmpty()) {
            hanges.setResults(results)
        } else {
            hanges.setResults(null)
        }
        // handgestureを実行
        hanges.handgesture()
        // ハンドサインをしているか
        val open_palm = results.gestures().any { gesture ->
            gesture.get(0).categoryName() == "Open_Palm"
        }
        val closed_fist = results.gestures().any { gesture ->
            gesture.get(0).categoryName() == "Closed_Fist"
        }
        if(open_palm){
            if(!isSound1) {
                playSoundEffect1()
                isSound1 = true
            }
        }else{
            isSound1 = false
        }
        if(closed_fist){
            if(!isSound2) {
                playSoundEffect2()
                isSound2 = true
            }
        }else{
            isSound2 = false
        }
        // 撮影可能であるか
        if (hanges.currentIsTake[1]){
            // bitmapを指定した範囲でトリミング
            val aftercutbitmap = trimming(beforecutbitmap, hanges.currentPicrec)
            // ModelUserで虫を判別し、名前を格納
            val musilabel = name_ja[ModelUser.answer(this, aftercutbitmap)]
            if(musilabel == "-1"){ // 虫と判別されなかったとき
                // HandGestureをリセットする
                hanges.reset_rec()
            }else{
                // BitmapをMediaStoreに保存 戻り値は画像名
                val uri_name = saveBitmapToMediaStore(aftercutbitmap, musilabel)
                // HandGestureをリセットする
                hanges.reset_rec()
                mCameraHandler!!.captureStill() // シャッター音
                // 画像名をIntroduceに送るのと、画面遷移
                navigateToIntroduceActivity(uri_name)
            }
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
    private fun playSoundEffect1() {
        if (::sound1.isInitialized) {
            sound1.start()
        }
    }
    private fun playSoundEffect2() {
        if (::sound2.isInitialized) {
            sound2.start()
        }
    }
    // putExtraでuriファイル名の送信と画面遷移
    private fun navigateToIntroduceActivity(uri_name: String) {
        val intent = Intent(this, IntroduceActivity::class.java).apply {
        putExtra("image_uri", uri_name)
        // アクティビティスタックをクリアするためのフラグを追加
        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
        finish() // 現在のアクティビティを終了してキャッシュを破棄
    }
    // Bitmapを指定範囲でトリミングする
    private fun cropBitmap(bitmap: Bitmap, cropRect: Rect): Bitmap {
        return Bitmap.createBitmap(
            bitmap,
            cropRect.left,
            cropRect.top,
            cropRect.width(),
            cropRect.height()
        )
    }
    // カメラの画像をトリミングする
    private fun trimming(bitmap: Bitmap, picrec: FloatArray): Bitmap {
        // トリミング範囲を指定
        val MAXSIZE_X = bitmap.width
        val MAXSIZE_Y = bitmap.height
        val MINSIZE = 0
        val EXPANTION = intArrayOf(200, 50)
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
    // SharedPreferencesにテキストを保存する関数
    private fun saveTextToSharedPreferences(text: String) {
        val sharedPreferences = getSharedPreferences("RirekiPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("NEW_ITEM", text)
        editor.apply()  // 保存
    }
    // bitmapをMediaStoreにuriとして保存する
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
}
