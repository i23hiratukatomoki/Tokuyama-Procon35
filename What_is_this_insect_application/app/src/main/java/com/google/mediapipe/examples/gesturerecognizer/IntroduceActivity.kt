package com.google.mediapipe.examples.gesturerecognizer

import android.R
import android.content.Intent
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
import java.util.logging.Handler

class IntroduceActivity : AppCompatActivity() , TextToSpeech.OnInitListener{
    private lateinit var activityIntroduceBinding: ActivityIntroduceBinding
    var tts: TextToSpeech? = null
    private var musi: String? = null

    private var Url: String? = null
    private var text1: String? = null
    private var text2: String? = null
    // private val insect_names = arrayOf("アメンボ", "アリ", "オサムシ")
    var execService: ExecutorService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityIntroduceBinding = ActivityIntroduceBinding.inflate(layoutInflater)
        setContentView(activityIntroduceBinding.root)

        // Intent putExtra()受取用
        val imageUriString = intent.getStringExtra("image_uri")

        // uriを直接受け取る方法
        // val imageUri: Uri? = imageUriString?.let { Uri.parse(it) }

        // これでanswerメソッドから番号を取り出して配列からそれに対応する昆虫の名前を取得

        musi = imageUriString!!.substring(0,imageUriString.length-16)

        Url = "https://ja.wikipedia.org/wiki/" + musi
        execService = Executors.newSingleThreadExecutor()
        activityIntroduceBinding.textView.text = "このむしのしゅるいは" + musi

        val text0 = findViewById<TextView>(com.google.mediapipe.examples.gesturerecognizer.R.id.textView1)
        text0.text = "さんこうにしたWebサイト:wikipedia\n\nURL:$Url"
        Linkify.addLinks(text0, Linkify.ALL)
        val n_furigana = findViewById<FuriganaView>(com.google.mediapipe.examples.gesturerecognizer.R.id.furiganaView)
        val tp = TextPaint()
        tp.setTextSize(36f);
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
            text2 = "この虫の種類は" + musi +  hurigana .readText()
            text1 = /*"カブトムシ（{漢字表記;かんじひょうき}は「{兜虫;かぶとむし}」もしくは「{甲虫;かぶとむし}」[3]、{学名;がくめい}：Trypoxylus dichotomus[4]）は、コウチュウ{目;もく}コガネムシ{科;か}カブトムシ{亜科;あか}カブトムシ{族;ぞく}カブトムシ{属;ぞく} Trypoxylus に{分類;ぶんるい}される{昆虫;こんちゅう}の{種;しゅ}。より{広義;こうぎ}にはカブトムシ{属;ぞく}を{含;ふく}むカブトムシ{亜科;あか} (Dynastinae) に{分類;ぶんるい}される{昆虫;こんちゅう}の{総称;そうしょう}だが、この{項;こう}では{種;しゅ}の{標準和名;ひょうじゅんわめい}としての{狭義;きょうぎ}のカブトムシを{扱;あつか}う。{本州以南;ほんしゅういなん}の{日本;にほん}を{含;ふく}む{東;ひがし}アジアに{分布;ぶんぷ}する。"*/
                hurigana.huriganagaesi()
            n_furigana.post { n_furigana.text_set(tp, text1, mark_s, mark_e) }
        })
        android.os.Handler().postDelayed({
            tts = TextToSpeech(this, this)
        },15000)

        // Toolbarを設定
        val toolbar: Toolbar = findViewById(com.google.mediapipe.examples.gesturerecognizer.R.id.toolbar)
        setSupportActionBar(toolbar)

        // 戻るボタンを表示
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 戻るボタンの動作
        toolbar.setNavigationOnClickListener {
            finish()  // RirekiActivityに戻る
        }

        // MediaStoreの画像名参照
        val imageUri = findImageUriByName(imageUriString!!)

        //上と同じ意味の一行
        //val imageUri = imageUriString.let { findImageUriByName(it) }

        // uriをimageViewで表示させる
        if (imageUri != null) {
            // 画像を表示する、または他の処理を行う
            val imageView = findViewById<ImageView>(com.google.mediapipe.examples.gesturerecognizer.R.id.musiimage)
            imageView.setImageURI(imageUri)
        } else {
            // 画像が見つからなかった場合の処理
            Toast.makeText(this, "画像が見つかりません", Toast.LENGTH_SHORT).show()
        }

        // 受け取った文字列を表示する
        //val itemName = intent.getStringExtra("ITEM_NAME")
        // val itemNameTextView: TextView = findViewById(com.google.mediapipe.examples.gesturerecognizer.R.id.itemNameTextView)
        // itemNameTextView.text = imageUriString
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
        }
    }





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
}