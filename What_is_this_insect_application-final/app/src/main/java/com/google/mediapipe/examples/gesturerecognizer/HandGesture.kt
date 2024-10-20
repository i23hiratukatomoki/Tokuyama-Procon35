package com.google.mediapipe.examples.gesturerecognizer

import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult

class HandGesture{
    // 初期値の定数
    private val org_rec = floatArrayOf(10f, 10f, 0f, 0f) // [left,top,right,bottom]
    private val org_tak =
        booleanArrayOf(false, false, false) // [0:最低一回パー, 1:撮影可能か, 2:直前にグー]
    // mediapipeの判別結果を取得する変数
    private var results: GestureRecognizerResult? = null
    // HandGesture内部で使うための変数
    private var _istake: BooleanArray = org_tak
    private var _picrec: FloatArray = org_rec
    // MainActivityで取得するための変数
    val currentIsTake: BooleanArray get() = _istake
    val currentPicrec: FloatArray get() = _picrec

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
    private fun pictak1(){
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
            _istake[2] = false
        }
        // ハンドサイン：グー　であるか
        val closed_fist = results!!.gestures().any { gesture ->
            gesture.get(0).categoryName() == "Closed_Fist"
        }
        // パー→グーのあとに別のハンドジェスチャーがあっても判定できるようにする条件
        if(closed_fist){
            _istake[2] = true
        }
    }

    // 人差し指を立てるだけ
    private fun pictak2(){
        // ハンドサインをしているか
        val isp = results!!.gestures().any { gesture ->
            gesture.get(0).categoryName() == "Pointing_Up"
        }
        if(isp){
            // ランドマークの取得
            val land_x = results!!.landmarks()[0][8].x()
            val land_y = results!!.landmarks()[0][8].y()
            val left = land_x - 0.1f
            val top = land_y -  0.3f
            val right = land_x +0.1f
            val bottom = land_y
            _picrec = floatArrayOf(left, top, right, bottom)
            _istake[0] = true
        }else{
            _istake[0] = false
        }
    }

    private fun isTakePicture(hand: Boolean){
        // 撮影範囲を指定されている　かつ　ハンドサインがグーである　かつ　撮影範囲内に手が入っていない
        if (_istake[0] && _istake[2] && (hand || !isFing())){
            _istake[1] = true
        }
    }

    // MainActivityでhandgestureの判別を実行させる
    fun handgesture(mode: Boolean){
        if(results != null){
            if(mode){
                pictak2()
                if(_istake[0]){
                    _istake[1]=true
                }
            }else{
                pictak1()
                isTakePicture(false)
            }
        }else{
            if(!mode){
                isTakePicture(true)
            }
        }
    }

    // mediapipeの判別結果をセット
    fun setResults(gestureRecognizerResult: GestureRecognizerResult?){
        results = gestureRecognizerResult
    }
}