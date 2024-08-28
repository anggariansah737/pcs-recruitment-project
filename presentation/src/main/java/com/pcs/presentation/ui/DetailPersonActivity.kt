package com.pcs.presentation.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import com.pcs.domain.model.Person
import com.pcs.presentation.R
import com.pcs.presentation.base.BaseActivity
import com.pcs.presentation.databinding.ActivityDetailPersonBinding

class DetailPersonActivity : BaseActivity<ActivityDetailPersonBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_detail_person
    private var person: Person? = null

    companion object {
        const val KEY_PERSON = "KEY_PERSON"
    }

    override fun ActivityDetailPersonBinding.initializeView(savedInstanceState: Bundle?) {
        getExtraParams()
        setupToolbar()
        setupDetail()
    }

    private fun getExtraParams() {
        person = intent?.getSerializableExtra(KEY_PERSON) as Person
    }

    private fun ActivityDetailPersonBinding.setupToolbar() {
        with(toolbarDetailPerson) {
            setupActionBar(
                toolbar = toolbarSupport, toolbarTextTitle, appbarDetailPerson
            )
            toolbarTextTitle.text = getString(R.string.label_title_toolbar_detail)
        }
    }

    private fun ActivityDetailPersonBinding.setupDetail() {
        Glide.with(this@DetailPersonActivity).load(person?.avatar).into(ivIcon)

        val (firstName, lastName) = splitName(person?.name ?: "")
        tvFirstName.text = firstName
        tvLastName.text = lastName
        tvAddress.text =
            "${person?.addressNo} ${person?.street} ${person?.county} ${person?.zipCode} ${person?.country}"
    }

    private fun splitName(fullName: String): Pair<String, String> {
        val nameParts = fullName.trim().split(" ")
        val lastName = nameParts.last()
        val firstName = nameParts.dropLast(1).joinToString(" ")
        return Pair(firstName, lastName)
    }

}