package com.google.mediapipe.examples.gesturerecognizer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.reflect.TypeToken
import java.util.Locale
import com.google.gson.Gson

class RirekiActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RirekiAdapter
    private lateinit var searchView: SearchView
    private val itemList = mutableListOf<String>()
    private val filteredItemList = mutableListOf<String>() // フィルタリング用のリスト
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rireki)

        // Toolbarを設定
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // 戻るボタンを表示
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 戻るボタンの動作を設定
        toolbar.setNavigationOnClickListener {
            finish() // 現在のアクティビティを終了し、MainActivityに戻る
        }

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        // RecyclerViewの設定
        adapter = RirekiAdapter(filteredItemList, onClick = { itemName ->
            val intent = Intent(this, IntroduceActivity::class.java)
            intent.putExtra("image_uri", itemName)
            startActivity(intent)
        }, onLongClick = { position ->
            showDeleteConfirmationDialog(position)
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // データをロードしてRecyclerViewを初期化
        loadItems()

        // MainActivityから新しいアイテムを取得して追加
        // SharedPreferencesから新しいアイテムを取得して追加
        val newItem = loadNewItemFromSharedPreferences()
        if (newItem != null) {
            addItem(newItem)
            clearNewItemFromSharedPreferences()  // 使ったら消す
        }

        // SearchViewのリスナーを設定
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })
    }
    // オプションメニューを作成
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_introduce, menu) // メニューを読み込む
        return true
    }
    // メニューアイテムが選択されたときの処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_go_to_main -> {
                // MainActivityに遷移
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_go_to_help -> {
                // HelpActivityに遷移
                val intent = Intent(this, HelpActivity::class.java)
                intent.putExtra("layoutResId", R.layout.activity_help_screen2)  // レイアウトリソースIDを渡す
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // SharedPreferencesから新しいアイテムを読み込む
    private fun loadNewItemFromSharedPreferences(): String? {
        val sharedPreferences = getSharedPreferences("RirekiPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("NEW_ITEM", null)
    }

    // SharedPreferencesから新しいアイテムを削除する
    private fun clearNewItemFromSharedPreferences() {
        val sharedPreferences = getSharedPreferences("RirekiPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("NEW_ITEM")
        editor.apply()
    }
    // 要素をリストに追加してRecyclerViewを更新
    private fun addItem(item: String) {
        itemList.add(0,item)
        filter(null) // フィルタリストを更新
        saveItems()
    }

    // フィルタリング用のメソッド
    private fun filter(query: String?) {
        filteredItemList.clear()
        if (query.isNullOrEmpty()) {
            filteredItemList.addAll(itemList)  // 全アイテムを表示
        } else {
            val searchQuery = query.lowercase(Locale.getDefault())
            filteredItemList.addAll(itemList.filter { it.lowercase(Locale.getDefault()).contains(searchQuery) })
        }
        adapter.notifyDataSetChanged()  // フィルタリング後にアダプターを更新
    }

    // データを保存するメソッド (JSONで保存)
    private fun saveItems() {
        val sharedPreferences = getSharedPreferences("RirekiPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = gson.toJson(itemList)  // リストをJSONに変換
        editor.putString("ITEM_LIST_JSON", json)
        editor.apply()
    }

    // データをロードするメソッド (JSONから復元)
    private fun loadItems() {
        val sharedPreferences = getSharedPreferences("RirekiPrefs", MODE_PRIVATE)
        val json = sharedPreferences.getString("ITEM_LIST_JSON", null)

        if (json != null) {
            val type = object : TypeToken<MutableList<String>>() {}.type
            val savedItems: MutableList<String> = gson.fromJson(json, type)  // JSONからリストに変換
            itemList.clear()
            itemList.addAll(savedItems)
            filter(null)  // 全アイテムを表示
        }
    }

    // 削除確認ダイアログを表示するメソッド
    private fun showDeleteConfirmationDialog(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("かくにん")
        builder.setMessage("このりれきをけしますか？")

        builder.setPositiveButton("けす") { dialog, _ ->
            removeItem(position)
            dialog.dismiss()
        }

        builder.setNegativeButton("キャンセル") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // 要素をリストから削除するメソッド
    private fun removeItem(position: Int) {
        val itemToRemove = filteredItemList[position]  // フィルタリングされたリストからアイテムを取得
        val actualPosition = itemList.indexOf(itemToRemove)  // 元のリストでの実際の位置を取得

        if (actualPosition >= 0) {
            itemList.removeAt(actualPosition)  // 元のリストから削除
            filter(null)  // フィルタリストを更新してリスト全体を再表示
            saveItems()  // データを保存
        }
    }
}


