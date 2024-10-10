package com.google.mediapipe.examples.gesturerecognizer

import android.util.Log
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import kotlin.math.min
import kotlin.math.max

class HandGesture{
    /*呼び出し方法
    val hanges = HandGesture()
    */
    val org_rec = floatArrayOf(10f, 10f, 0f, 0f) // 初期値（適当）
    val org_tak =
        //booleanArrayOf(false, false, false, false) // [0:ハンドサイン, 1:直前にハンドサイン, 2:指が入っているか, 3:撮影可能か]
        booleanArrayOf(false, false) // [0:直前にハンドサイン, 1:撮影可能か]
    private var results: GestureRecognizerResult? = null

    private var _istake: BooleanArray = org_tak
    private var _picrec: FloatArray = org_rec
    val currentIsTake: BooleanArray get() = _istake
    val currentPicrec: FloatArray get() = _picrec

    // x,yの最大値・最小値を格納　撮影範囲とする
    private fun rec_mm(x: Float, y: Float, rec: FloatArray): FloatArray {
        rec[0] = min(rec[0], x)  // xmin l
        rec[1] = min(rec[1], y)  // ymin b
        rec[2] = max(rec[2], x)  // xmax r
        rec[3] = max(rec[3], y)  // ymax t
        return rec
    }

    //規定の撮影範囲か
    private fun picrec_rule(picrec: FloatArray): Boolean {
        val LOWEST = 0.2f
        val XMAX = 1f
        val YMAX = 1f
        val xlen = picrec[2] - picrec[0]
        val ylen = picrec[3] - picrec[1]
        val isOk = xlen * ylen >= (LOWEST * LOWEST) && xlen in LOWEST..XMAX && ylen in LOWEST..YMAX
        return if (isOk) {
            true
        } else {
            false
        }
    }
    // 指が入っていないか TODO Mainで実装　指が一番下のときのbitmapを取得
    /*private fun picrec_fing(picrec: FloatArray, brect: FloatArray): Boolean {
        val isX1 = picrec[0] <= brect[2]
        val isX2 = brect[0] <= picrec[2]
        val isY1 = picrec[1] <= brect[3]
        val isY2 = brect[1] <= picrec[3]
        return if ((isX1 && isX2) && (isY1 && isY2)) {
            false
        } else {
            true
        }
    }*/

    // 初期化
    fun reset_rec() {
        _picrec = org_rec
        _istake = org_tak
    }

    // 撮影の範囲と撮影許可を管理 ver1
    /*private fun pictak(isp: Boolean){
        // ジェスチャーが Pointing_Up かどうかをチェック
        isTake[0] = isp
        if (!isTake[2]) {
            if (isTake[0]) {
                // ランドマークの取得
                // val landmark_x = resultBundle.results.first().landmarks().get(0).get(8).x()
                // val landmark_y = resultBundle.results.first().landmarks().get(0).get(8).y()
                val landmark_x = results!!.landmarks().get(0).get(8).x()
                val landmark_y = results!!.landmarks().get(0).get(8).y()
                picrec = rec_mm(landmark_x, landmark_y, picrec)
                isTake[1] = true
                return
            }
            if (isTake[1]) {
                isTake[1] = false
                if (!picrec_rule(picrec)) {
                    reset_rec()
                    return
                }
                /*下と同じもの(TODO)まとめる*/
                /*
                val brect = calc_bounding_rect(landmarks)
                isTake_tmp[2] = picrec_fing(picrec_tmp, brect)
                if (!isTake_tmp[2]) {
                    isTake_tmp[3] = true
                }*/
                /**/
            }
            return
        }
        /*上と同じもの*/
        /*val brect = calc_bounding_rect(landmarks)
        isTake_tmp[2] = picrec_fing(picrec_tmp, brect)
        if (!isTake_tmp[2]) {
            isTake_tmp[3] = true
        }*/
        /**/
        return
    }*/

    // 手のlandmarkの外接矩形の計算ver3
    /*private fun calc_bounding_rect(): FloatArray {
        var brect = org_rec
        for (landmark in results!!.landmarks()) {
            for (normalizedLandmark in landmark) {
                brect = rec_mm(normalizedLandmark.x(), normalizedLandmark.y(), brect)
            }
        }
        return brect
    }*/

    // 撮影の範囲と撮影許可を管理 ver2
    private fun pictak(isp: Boolean){
        if (_istake[1]){
            reset_rec()
        }
        if (isp) {
            // ランドマークの取得
            // val landmark_x = resultBundle.results.first().landmarks().get(0).get(8).x()
            // val landmark_y = resultBundle.results.first().landmarks().get(0).get(8).y()
            val landmark_x = results!!.landmarks().get(0).get(8).x()
            val landmark_y = results!!.landmarks().get(0).get(8).y()
            _picrec = rec_mm(landmark_x, landmark_y, _picrec)
            _istake[0] = true
            return
        }
        if (_istake[0]){
            _istake[0] = false
            if (!picrec_rule(_picrec)) {
                reset_rec()
                return
            }
            _istake[1] = true
        }
    }

    // ハンドジェスチャーを認識して、撮影判定可能
    fun handgesture(isp: Boolean){
        pictak(isp)
    }

    // mediapipeのライブラリをセット
    fun setResults(gestureRecognizerResult: GestureRecognizerResult){
        results = gestureRecognizerResult
    }
}