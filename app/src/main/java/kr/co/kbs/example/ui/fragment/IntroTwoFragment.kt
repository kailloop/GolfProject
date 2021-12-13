package kr.co.kbs.example.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.kbs.example.R
import kr.co.kbs.example.databinding.FragmentIntroTwoBinding
import kr.co.kbs.example.ui.BaseFragment

class IntroTwoFragment:BaseFragment() {

    private val b by lazy { FragmentIntroTwoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_intro_two, container, false)
    }

    override fun viewClick(v: View?) {

    }
}