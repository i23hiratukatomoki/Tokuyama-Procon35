package com.google.mediapipe.examples.gesturerecognizer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RirekiAdapter(
    private val itemList: List<String>,
    private val onClick: (String) -> Unit,
    private val onLongClick: (Int) -> Unit  // 長押しイベントを処理するための関数を追加
) : RecyclerView.Adapter<RirekiAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textViewItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rireki, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.textView.text = item

        // 通常のクリック処理
        holder.itemView.setOnClickListener {
            onClick(item)
        }

        // 長押しによる削除確認ダイアログの表示
        holder.itemView.setOnLongClickListener {
            onLongClick(position)
            true
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
