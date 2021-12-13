package kr.co.kbs.example.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.kbs.example.ui.activity.IntroActivity
import kr.co.kbs.example.util.Util

class IntroSlideAdapter(fm: IntroActivity) : FragmentStateAdapter(fm){

    var fragments: ArrayList<Fragment> = ArrayList()
    var activity:IntroActivity = fm

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    fun addFragment(fragment:Fragment) {
        fragments.add(fragment)
        notifyItemInserted(fragments.size-1)
    }

    fun removeFragment() {
        fragments.removeLast()
        notifyItemRemoved(fragments.size)
    }
}