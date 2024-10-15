package com.google.mediapipe.examples.gesturerecognizer

import android.R
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.speech.tts.TextToSpeech
import android.text.TextPaint
import android.text.util.Linkify
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.mediapipe.examples.gesturerecognizer.databinding.ActivityIntroduceBinding
import java.io.IOException
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.content.Context

class IntroduceActivity : AppCompatActivity() , TextToSpeech.OnInitListener{
    private lateinit var activityIntroduceBinding: ActivityIntroduceBinding
    var tts: TextToSpeech? = null
    private var musi: String? = null

    private var Url: String? = null
    private var text1: String? = null
    private var text2: String? = null
    var execService: ExecutorService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityIntroduceBinding = ActivityIntroduceBinding.inflate(layoutInflater)
        setContentView(activityIntroduceBinding.root)

        // 画像名を受け取る
        val imageUriString = intent.getStringExtra("image_uri")

        // 画像名の内、虫の名前を取り出す
        musi = imageUriString!!.substring(0,imageUriString.length-16)

        Url = "https://ja.wikipedia.org/wiki/" + musi
        execService = Executors.newSingleThreadExecutor()
        activityIntroduceBinding.textView.text = "このむしのしゅるいは" + musi+"です。"

        val text0 = findViewById<TextView>(com.google.mediapipe.examples.gesturerecognizer.R.id.textView1)
        text0.text = "さんこうにしたWebサイト:wikipedia\n\nURL:$Url"
        Linkify.addLinks(text0, Linkify.ALL)
        val n_furigana = findViewById<FuriganaView>(com.google.mediapipe.examples.gesturerecognizer.R.id.furiganaView)
        val tp = TextPaint()
        tp.setTextSize(36f)
        val mark_s = 11 // highlight 厚い in text (characters 11-13)
        val mark_e = 13

        n_furigana.text_set(tp, "お{待;ま}ちください。", mark_s, mark_e)
        execService?.execute(Runnable {
            val hurigana: Hurigana
            try {
                hurigana = Hurigana(musi)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
            text2 = "この虫の種類は" + musi + "です。" +  hurigana.readText()
            if(isInternetAvailable(this)){
                text1 = hurigana.huriganagaesi()
            }else{
                text1 = "インターネットに{接続;せつぞく}してください"
            }
            n_furigana.post { n_furigana.text_set(tp, text1, mark_s, mark_e) }
        })
        android.os.Handler().postDelayed({
            if(text2 == null){
                onseiWait()
            }
            else{
                tts = TextToSpeech(this, this)
            }
        },1000)

        // Toolbarを設定
        val toolbar: Toolbar = findViewById(com.google.mediapipe.examples.gesturerecognizer.R.id.toolbar)
        setSupportActionBar(toolbar)

        // 戻るボタンを表示
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 戻るボタンの動作
        toolbar.setNavigationOnClickListener {
            tts?.stop()
            tts?.shutdown()
            finish()  // RirekiActivityに戻る
        }

        // MediaStoreの画像名参照
        val imageUri = findImageUriByName(imageUriString)

        // uriをimageViewで表示させる
        if (imageUri != null) {
            // 画像を表示する、または他の処理を行う
            val imageView = findViewById<ImageView>(com.google.mediapipe.examples.gesturerecognizer.R.id.musiimage)
            imageView.setImageURI(imageUri)
        } else {
            // 画像が見つからなかった場合の処理
            Toast.makeText(this, "画像が見つかりません", Toast.LENGTH_SHORT).show()
        }
    }
    fun onseiWait(){
        android.os.Handler().postDelayed({
            if (text2 == null) {
                onseiWait()
            } else {
                tts = TextToSpeech(this, this)
            }
        },1000)
    }
    // クラスの中身
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // ロケールの指定
            val locale = Locale.JAPAN
            if (tts!!.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                tts!!.setLanguage(Locale.JAPAN)
            }

            // 音声合成の実行
            tts!!.speak(text2, TextToSpeech.QUEUE_FLUSH, null, "utteranceId")
            if(!isInternetAvailable(this)){
                tts!!.speak("インターネットに接続してください", TextToSpeech.QUEUE_FLUSH, null, "utteranceId")
            }
        }
    }

    //　インターネットに接続しているかを判定
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    // 画像の名前で画像を参照
    fun findImageUriByName(imageName: String): Uri? {
        val contentResolver = this.contentResolver // Activity 内で context は this
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val selection = "${MediaStore.Images.Media.DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf("$imageName.jpg")

        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )

        cursor?.use {
            if (cursor.moveToFirst()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                return Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id.toString())
            }
        }
        return null
    }

    // オプションメニューを作成
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.google.mediapipe.examples.gesturerecognizer.R.menu.menu_introduce, menu) // メニューを読み込む
        return true
    }

    // メニューアイテムが選択されたときの処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            com.google.mediapipe.examples.gesturerecognizer.R.id.action_go_to_main -> {
                tts?.stop()
                tts?.shutdown()
                // MainActivityに遷移
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onBackPressed() {
        finish()
    }

    //Activityがストップした時にバックグラウンド再生をさせないようにする
    override fun onStop() {
        super.onStop()
        if (tts!=null) {
            tts?.stop()
            tts?.shutdown()
        }
    }

    //onStopメソッドの保険
    override fun onDestroy() {
        super.onDestroy()
        if (tts!=null) {
            tts?.stop()
            tts?.shutdown()
        }
    }
}