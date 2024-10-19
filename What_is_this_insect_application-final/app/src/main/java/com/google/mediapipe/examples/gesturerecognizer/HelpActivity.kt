package com.google.mediapipe.examples.gesturerecognizer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Intent からレイアウトリソースIDを取得して適用
        val layoutResId = intent.getIntExtra("layoutResId", R.layout.activity_default_help)
        setContentView(layoutResId)

        // Toolbarを設定
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // 戻るボタンを表示
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 戻るボタンの動作を設定
        toolbar.setNavigationOnClickListener {
            finish() // 現在のアクティビティを終了し、MainActivityに戻る
        }
    }
}
