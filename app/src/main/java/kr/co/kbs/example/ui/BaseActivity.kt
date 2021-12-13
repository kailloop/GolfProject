package kr.co.kbs.example.ui

import android.app.Activity
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kr.co.kbs.example.util.Util

abstract class BaseActivity : FragmentActivity(), View.OnClickListener {

    abstract fun createActivity()
    abstract fun pauseActivity()
    abstract fun resumeActivity()
    abstract fun destroyActivity()

    abstract fun viewClick(v:View?)


    // *****************************************************************************
    // LIFE CYCLE
    // *****************************************************************************
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Util.MY_LOG("Execute onCreate()")
        this.createActivity()
    }

    override fun onPause() {
        super.onPause()
        Util.MY_LOG("Execute onPause()")
    }

    override fun onResume() {
        super.onResume()
        Util.MY_LOG("Execute onResume()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Util.MY_LOG("Execute onDestroy()")
    }

    // *****************************************************************************
    // EVENT
    // *****************************************************************************
    override fun onClick(p0: View?) {
        this.viewClick(p0)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev!!.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (view != null && view is EditText) {
                val r = Rect()
                view.getGlobalVisibleRect(r)
                val rawX = ev!!.rawX.toInt()
                val rawY = ev!!.rawY.toInt()
                if (!r.contains(rawX, rawY)) {
                    view.clearFocus()
                    val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    var editorActionListener = OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                v.clearFocus()
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
            }
        false
    }

    fun ViewPager2.removeOverScroll() { // ViewPager2 remove OverScrollMode
        (getChildAt(0) as? RecyclerView)?.overScrollMode = View.OVER_SCROLL_NEVER
    }


}