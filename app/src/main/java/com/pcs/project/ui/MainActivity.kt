package com.pcs.project.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.pcs.presentation.base.BaseActivity
import com.pcs.presentation.ui.ListPersonActivity
import com.pcs.project.R
import com.pcs.project.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun ActivityMainBinding.initializeView(savedInstanceState: Bundle?) {
        Handler().postDelayed({
            startActivity(Intent(this@MainActivity, ListPersonActivity::class.java))
            finish()
        }, 2000)
    }
}