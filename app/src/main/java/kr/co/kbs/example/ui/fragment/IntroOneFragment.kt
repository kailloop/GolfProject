package kr.co.kbs.example.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.kbs.example.R
import kr.co.kbs.example.databinding.FragmentIntroOneBinding
import kr.co.kbs.example.ui.BaseFragment

class IntroOneFragment : BaseFragment() {

    private val b by lazy { FragmentIntroOneBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_intro_one, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun viewClick(v: View?) {

    }


}