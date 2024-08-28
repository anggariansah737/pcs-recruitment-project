package com.pcs.presentation.base

import android.animation.ObjectAnimator
import android.animation.StateListAnimator
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import com.google.android.material.appbar.AppBarLayout
import com.pcs.presentation.R

abstract class CoreActivity<binding : ViewDataBinding> : AppCompatActivity(),
    BaseView.Activity<binding> {

    @LayoutRes
    protected abstract fun getLayoutId(): Int
    open lateinit var binding: binding
    private var toolbarTitle: AppCompatTextView? = null

    companion object {
        const val PROPERTY_ELEVATION = "elevation"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = this
        binding = DataBindingUtil.setContentView(activity, getLayoutId())
        binding.apply {
            this.lifecycleOwner = activity
            this.initializeView(savedInstanceState)
        }
    }

    fun <T> LiveData<T>.observe(function: T.() -> Unit) {
        this.observe(this@CoreActivity) {
            function.invoke(it)
        }
    }

    protected open fun setupActionBar(
        toolbar: Toolbar,
        toolbarTitle: AppCompatTextView,
        appBarLayout: AppBarLayout,
        hasBackButton: Boolean = true
    ) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(hasBackButton)
        }

        val stateListAnimator = StateListAnimator()
        stateListAnimator.addState(
            IntArray(0),
            ObjectAnimator.ofFloat(appBarLayout, PROPERTY_ELEVATION, 0F)
        )

        this@CoreActivity.toolbarTitle = toolbarTitle

        if (hasBackButton) {
            toolbar.navigationIcon = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back)
            toolbar.contentInsetStartWithNavigation = 0
        } else {
            toolbar.navigationIcon = null
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}