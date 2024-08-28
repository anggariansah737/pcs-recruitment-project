package com.pcs.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

abstract class CoreFragment<binding : ViewDataBinding> : Fragment(), BaseView.Fragment<binding> {

    @LayoutRes
    protected abstract fun getLayoutId(): Int
    open lateinit var binding: binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflateConfigurator(inflater, container)

    private fun inflateConfigurator(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
            this.initializeView()
        }.root
    }

    fun <T> LiveData<T>.observe(function: T.() -> Unit) {
        this.observe(this@CoreFragment) {
            function.invoke(it)
        }
    }

}