package kr.co.kbs.example.ui.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Handler
import android.os.Message
import android.view.Gravity
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kr.co.kbs.example.R
import kr.co.kbs.example.adapter.MainEventAdapter
import kr.co.kbs.example.adapter.MainJournalAdapter
import kr.co.kbs.example.databinding.ActivityMainBinding
import kr.co.kbs.example.ui.BaseActivity
import kr.co.kbs.example.util.ImageLoader
import kr.co.kbs.example.util.Util
import java.lang.Exception

class MainActivity : BaseActivity(), DrawerLayout.DrawerListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var eventAdapter: MainEventAdapter
    private lateinit var journalAdapter: MainJournalAdapter
    private var isDrawerDo: Boolean = false

    // *****************************************************************************
    // LIFE CYCLE
    // *****************************************************************************
    override fun createActivity() {
        setContentView(binding.root)

        initUI()
    }

    override fun pauseActivity() {

    }

    override fun resumeActivity() {

    }

    override fun destroyActivity() {

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        setupMainEvent()
        setupJournal()
        super.onWindowFocusChanged(hasFocus)
    }

    // *****************************************************************************
    // INIT UI
    // *****************************************************************************
    private fun initUI() {
        binding.btnHamb.setOnClickListener(this)

        binding.txtSearch.setOnEditorActionListener(editorActionListener)

        initDrawer()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupMainEvent() {  // MainEvent 사진 넣기
        val imgSize: ArrayList<Int> = arrayListOf()
        imgSize.add(binding.mainEvent.width)
        imgSize.add(binding.mainEvent.height)

        // 메인 이벤트 이미지
        val mainDatas: ArrayList<String> = arrayListOf()

        mainDatas.add("https://lh3.googleusercontent.com/proxy/W34tRukYrMf0pftSBNS6cNgdeY7kCg8VWqrotTM_YaXJ4ZEDlJPoJTFIfS_j4eqdwu-h2hTkpyk1Fpn8S0eCs2C1VPO5hzZZabkEGzEBaHPbhkLH")
        mainDatas.add("https://lh3.googleusercontent.com/proxy/W34tRukYrMf0pftSBNS6cNgdeY7kCg8VWqrotTM_YaXJ4ZEDlJPoJTFIfS_j4eqdwu-h2hTkpyk1Fpn8S0eCs2C1VPO5hzZZabkEGzEBaHPbhkLH")
        mainDatas.add("https://lh3.googleusercontent.com/proxy/W34tRukYrMf0pftSBNS6cNgdeY7kCg8VWqrotTM_YaXJ4ZEDlJPoJTFIfS_j4eqdwu-h2hTkpyk1Fpn8S0eCs2C1VPO5hzZZabkEGzEBaHPbhkLH")
        mainDatas.add("https://lh3.googleusercontent.com/proxy/Cjvqd1UztO0WpeSIuFUJiYC80tyeaX7lqkbs-kqF_0Pc8571Bm-2P6Sl2O0SwkIcQKTALHZeAVp9lFt-HYN-WTi-9pesF54qYnYnk7LfBvbOdGxae1u20w")

        try {
            eventAdapter = MainEventAdapter(this@MainActivity, mainDatas, imgSize)
            eventAdapter.apply {
                setHasStableIds(false)
            }

            binding.mainEvent.apply {
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageScrollStateChanged(state: Int) {
                        super.onPageScrollStateChanged(state)
                    }

                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                        if (positionOffsetPixels == 0) {
                            binding.mainEvent.setCurrentItem(position)
                        }
                    }

                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)

                    }
                })

                adapter = eventAdapter
                removeOverScroll()
                offscreenPageLimit = mainDatas.size - 1
            }

            eventAdapter.notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            Util.MY_LOG("종료")
        }
    }

    private fun setupJournal() {    // Journal 이미지 셋팅
        val itemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    val fromPos: Int = viewHolder.adapterPosition
                    val toPos: Int = target.adapterPosition
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                }
            }

        val mainDatas: ArrayList<Int> = arrayListOf()
        for (i in 0..9) {
            mainDatas.add(R.drawable.img_main_event_two)
        }

        journalAdapter = MainJournalAdapter(this, mainDatas)
        journalAdapter.apply {
            setHasStableIds(false)
        }

        binding.lstJournal.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = journalAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
        }

        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.lstJournal)
    }

    // *****************************************************************************
    // DRAWER FUNC
    // *****************************************************************************
    private fun initDrawer() {
        binding.dlMain.addDrawerListener(this)
        lockDrawer()
    }

    private fun lockDrawer() {
        binding.dlMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT)
    }

    private fun toggleDrawer(drawerGravity: Int) {
        if (isDrawerDo) {
            return
        }

        if (drawerGravity == Gravity.RIGHT) {
            if (binding.dlMain.isDrawerOpen(Gravity.RIGHT)) {
                closeDrawer(Gravity.RIGHT)
            } else {
                openDrawer(Gravity.RIGHT)
            }
        }
    }

    private fun openDrawer(drawerGravity: Int) {
        if ((drawerGravity == Gravity.RIGHT) && (binding.dlMain.isDrawerOpen(Gravity.RIGHT))) {
            return
        }

        if (isDrawerDo) {
            return
        } else {
            isDrawerDo = true
        }

        if (drawerGravity == Gravity.RIGHT) {
            if (!binding.dlMain.isDrawerOpen(Gravity.RIGHT)) {
                binding.dlMain.openDrawer(Gravity.RIGHT)
                binding.dlMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.RIGHT)
            }
        }
    }

    private fun closeDrawer(drawerGravity: Int) {
        if (drawerGravity == Gravity.RIGHT && (!binding.dlMain.isDrawerOpen(Gravity.RIGHT))) {
            return
        }

        if (isDrawerDo) {
            return
        } else {
            isDrawerDo = true
        }

        if (drawerGravity == Gravity.RIGHT) {
            if (binding.dlMain.isDrawerOpen(Gravity.RIGHT)) {
                binding.dlMain.closeDrawer(Gravity.RIGHT)
                binding.dlMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT)
            }
        }
    }

    // *****************************************************************************
    // EVENT
    // *****************************************************************************
    override fun viewClick(v: View?) {
        when (v!!.id) {
            binding.btnHamb.id ->
                openDrawer(Gravity.RIGHT)
        }
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

    }

    override fun onDrawerOpened(drawerView: View) {
        isDrawerDo = false
    }

    override fun onDrawerClosed(drawerView: View) {
        lockDrawer()
        isDrawerDo = false
    }

    override fun onDrawerStateChanged(newState: Int) {
        if (newState == DrawerLayout.STATE_IDLE) {
            isDrawerDo = false
        }
    }
}