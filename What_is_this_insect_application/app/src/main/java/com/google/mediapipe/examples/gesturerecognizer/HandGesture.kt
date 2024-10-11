package com.google.mediapipe.examples.gesturerecognizer

/*呼び出し方法
val hanges = HandGesture()
*/

import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import android.util.Log

class HandGesture{
    // 初期値の定数
    private val org_rec = floatArrayOf(10f, 10f, 0f, 0f) // [left,top,right,bottom]
    private val org_tak =
        booleanArrayOf(false, false) // [0:直前にハンドサイン, 1:撮影可能か]
    // mediapipeの判別結果を取得する変数
    private var results: GestureRecognizerResult? = null
    // HandGesture内部で使うための変数
    private var _istake: BooleanArray = org_tak
    private var _picrec: FloatArray = org_rec
    // MainActivityで取得するための変数
    val currentIsTake: BooleanArray get() = _istake
    val currentPicrec: FloatArray get() = _picrec

    /*data class _picrec(
        val left: Float,
        val bottom: Float,
        val right: Float,
        val top: Float
    )*/

    // 指が撮影範囲に入っていないか
    private fun isFing(): Boolean {
        for (landmark in results!!.landmarks()) {
            for (normalizedLandmark in landmark) {
                if(normalizedLandmark.x()>=_picrec[0] && normalizedLandmark.x()<=_picrec[2]
                    && normalizedLandmark.y()>=_picrec[1] && normalizedLandmark.y()<=_picrec[3]){
                    return true
                }
            }
        }
        return false
    }

    // 初期化
    fun reset_rec() {
        _picrec = org_rec
        _istake = org_tak
    }

    // パーで撮影範囲を指定、グーで枠外に出ると撮影する
    private fun pictak(){
        // 直前に撮影したならば初期状態にリセット
        if (_istake[1]){
            reset_rec()
        }
        // ハンドサイン：パー　であるか
        val open_palm = results!!.gestures().any { gesture ->
            gesture.get(0).categoryName() == "Open_Palm"
        }
        if (open_palm) {
            // ランドマークの取得
            var left = results!!.landmarks().get(0).get(4).x()
            val top = results!!.landmarks().get(0).get(12).y()
            var right = results!!.landmarks().get(0).get(20).x()
            val bottom = results!!.landmarks().get(0).get(0).y()
            if(left > right){ // 手の甲が手前に見える場合
                val tmp = left
                left = right
                right = tmp
            }
            _picrec = floatArrayOf(left, top, right, bottom)
            _istake[0] = true
        }
        // ハンドサイン：グー　であるか
        val closed_fist = results!!.gestures().any { gesture ->
            gesture.get(0).categoryName() == "Closed_Fist"
        }
        // 撮影範囲を指定されている　かつ　ハンドサインがグーである　かつ　撮影範囲内に手が入っていない
        if (_istake[0] && closed_fist && !isFing()){
            _istake = booleanArrayOf(false, true)
        }
    }

    // MainActivityでhandgestureの判別を実行させる
    fun handgesture(){
        pictak()
    }

    // mediapipeの判別結果をセット
    fun setResults(gestureRecognizerResult: GestureRecognizerResult){
        results = gestureRecognizerResult
    }
}