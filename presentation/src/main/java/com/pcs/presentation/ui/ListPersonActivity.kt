package com.pcs.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pcs.domain.model.Person
import com.pcs.presentation.R
import com.pcs.presentation.BR
import com.pcs.presentation.adapter.RecyclerViewAdapter
import com.pcs.presentation.base.BaseActivity
import com.pcs.presentation.databinding.ActivityDetailPersonBinding
import com.pcs.presentation.databinding.ActivityListPersonBinding
import com.pcs.presentation.databinding.ItemPersonBinding
import com.pcs.presentation.utils.DateUtils
import com.pcs.presentation.viewmodels.ListPersonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListPersonActivity : BaseActivity<ActivityListPersonBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_list_person
    private lateinit var adapterPerson: RecyclerViewAdapter<Person, ItemPersonBinding>
    private val viewModel by viewModels<ListPersonViewModel>()

    override fun ActivityListPersonBinding.initializeView(savedInstanceState: Bundle?) {
        setupToolbar()
        setupFetcher()
        setupAdapter()
        setupObserver()
    }

    private fun setupFetcher() {
        viewModel.getPersons()
    }

    private fun ActivityListPersonBinding.setupObserver() {
        with(viewModel) {
            data.observe {
                if (this != null) {
                    adapterPerson.updateList(this)
                }
            }

            isLoading.observe {
                if (this != null) {
                    setupLoading(this)
                }
            }
        }
    }

    private fun ActivityListPersonBinding.setupToolbar() {
        with(toolbarListPerson) {
            setupActionBar(
                toolbar = toolbarSupport, toolbarTextTitle, appbarListPerson, hasBackButton = false
            )
            toolbarTextTitle.text = getString(R.string.app_name)
        }
    }

    private fun ActivityListPersonBinding.setupLoading(isLoading: Boolean) {
        if (isLoading) {
            pbListPerson.visibility = View.VISIBLE
            rvListPerson.visibility = View.GONE
        } else {
            pbListPerson.visibility = View.GONE
            rvListPerson.visibility = View.VISIBLE
        }
    }

    private fun ActivityListPersonBinding.setupAdapter() {
        adapterPerson = RecyclerViewAdapter(
            arrayListOf(),
            R.layout.item_person,
            BR.person,
        ) { itemPerson, person ->

            Glide.with(this@ListPersonActivity)
                .load(person.avatar)
                .into(itemPerson.ivIcon)

            itemPerson.tvBirthDate.text = DateUtils.formatDate(person.createdAt)

            itemPerson.root.setOnClickListener {
                startActivity(
                    Intent(this@ListPersonActivity, DetailPersonActivity::class.java)
                        .putExtra(DetailPersonActivity.KEY_PERSON, person)
                )
            }
        }

        rvListPerson.apply {
            layoutManager = LinearLayoutManager(this@ListPersonActivity)
            adapter = adapterPerson
        }
    }

}