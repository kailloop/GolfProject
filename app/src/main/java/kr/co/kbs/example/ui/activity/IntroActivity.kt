package kr.co.kbs.example.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kr.co.kbs.example.adapter.IntroSlideAdapter
import kr.co.kbs.example.databinding.ActivityIntroBinding
import kr.co.kbs.example.ui.BaseActivity
import kr.co.kbs.example.ui.fragment.IntroOneFragment
import kr.co.kbs.example.ui.fragment.IntroThreeFragment
import kr.co.kbs.example.ui.fragment.IntroTwoFragment
import kr.co.kbs.example.util.Util
import kotlin.properties.Delegates

class IntroActivity : FragmentActivity(), View.OnClickListener {

    val b by lazy { ActivityIntroBinding.inflate(layoutInflater) }
    private lateinit var pagerAdapter: IntroSlideAdapter

    // *****************************************************************************
    // LIFE CYCLE
    // *****************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        initUI()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    // *****************************************************************************
    // INIT UI
    // *****************************************************************************

    private fun initUI() {
        pagerAdapter = IntroSlideAdapter(this)

        val oneFragment: IntroOneFragment = IntroOneFragment()
        val twoFragment: IntroTwoFragment = IntroTwoFragment()
        val threeFragment: IntroThreeFragment = IntroThreeFragment(this)

        pagerAdapter.addFragment(oneFragment)
        pagerAdapter.addFragment(twoFragment)
        pagerAdapter.addFragment(threeFragment)

        b.pager.adapter = pagerAdapter
        b.pager.apply {
            registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback() {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Util.MY_LOG("현재 페이지 : " + "${position+1}")
                    if(position == 0){
                        b.dividerStatus.setProgress(33, true)
                    }else if(position == 1) {
                        b.dividerStatus.setProgress(66, true)
                    }else if(position == 2 ){
                        b.dividerStatus.setProgress(100, true)
                    }
                }
            })
            removeOverScroll()
        }
    }



    fun ViewPager2.removeOverScroll() { // ViewPager2 remove OverScrollMode
        (getChildAt(0) as? RecyclerView)?.overScrollMode = View.OVER_SCROLL_NEVER
    }

    // *****************************************************************************
    // CHANGE SCREEN FUNCTION
    // *****************************************************************************
    public fun goMainActivity() {  // 메인화면으로 이동
        Util.MY_LOG("goMainActivity()")
        val it: Intent = Intent(this, MainActivity::class.java)
        it.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(it)
        overridePendingTransition(0, 0)
        finish()
    }

    // *****************************************************************************
    // EVENT
    // *****************************************************************************
    override fun onClick(p0: View?) {

    }
}