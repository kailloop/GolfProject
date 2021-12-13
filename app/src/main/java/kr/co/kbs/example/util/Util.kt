package kr.co.kbs.example.util

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import kr.co.kbs.example.MyApplication
import kr.co.kbs.example.R

object Util {
    fun MY_LOG(content:String) {
        Log.e("MY_LOG", content)
    }

    fun getDiviceSize(activity: Activity):FloatArray {
        var display = activity.windowManager.defaultDisplay
        var outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val density = activity.resources.displayMetrics.density
        val dpHeight = outMetrics.heightPixels / density
        val dpWidth = outMetrics.widthPixels / density

        var result:FloatArray = FloatArray(2)
        result[0] = dpWidth
        result[1] = dpHeight

        return result
    }
}