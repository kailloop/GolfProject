package kr.co.kbs.example.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.kbs.example.R
import kr.co.kbs.example.databinding.FragmentIntroThreeBinding
import kr.co.kbs.example.ui.BaseFragment
import kr.co.kbs.example.ui.activity.IntroActivity

class IntroThreeFragment(val base:IntroActivity) : BaseFragment(){

    private val binding by lazy { FragmentIntroThreeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    // *****************************************************************************
    // INIT UI
    // *****************************************************************************
    private fun initUI() {
        binding.btnMain.setOnClickListener(this)
    }

    override fun viewClick(v: View?) {
        when(v!!.id) {
            binding.btnMain.id -> base.goMainActivity()
        }
    }
}