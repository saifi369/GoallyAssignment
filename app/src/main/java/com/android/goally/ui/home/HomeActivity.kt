package com.android.goally.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.goally.BaseActivity
import com.android.goally.R
import com.android.goally.databinding.ActivityHomeBinding
import com.android.goally.databinding.LayoutFilterSheetBinding
import com.android.goally.ui.copilotdetail.CopilotDetailActivity
import com.android.goally.ui.home.adapter.CopilotFilterListAdapter
import com.android.goally.ui.home.adapter.CopilotListAdapter
import com.android.goally.ui.home.model.CopilotFilterItem
import com.android.goally.ui.home.model.FilterType
import com.google.android.material.sidesheet.SideSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val filterSheetBinding by lazy {
        LayoutFilterSheetBinding.inflate(layoutInflater)
    }

    private lateinit var copilotListAdapter: CopilotListAdapter
    private lateinit var filterSheetAdapter: CopilotFilterListAdapter

    private val viewModel: HomeScreenVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.layoutToolbar.customToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setupViews()
        setupObservers()
        setupAdapters()
    }

    private fun setupAdapters() {
        copilotListAdapter = CopilotListAdapter(mutableListOf()) { copilot ->
            startActivity(CopilotDetailActivity.getCallingIntent(this, copilot))
        }
        binding.rvList.adapter = copilotListAdapter
        filterSheetAdapter = CopilotFilterListAdapter(emptyList()) {
            viewModel.applyFilter(it)
        }
    }

    private fun setupViews() {
        binding.run {
            setupCopilotRecyclerView()
            tvFolderFilter.setOnClickListener {
                viewModel.getCopilotFolderFilterItems()?.let {
                    showFilterSheet(it, FilterType.FOLDER)
                }
            }
            tvScheduleFilter.setOnClickListener {
                viewModel.getCopilotScheduleFilterItems()?.let {
                    showFilterSheet(it, FilterType.SCHEDULE)
                }
            }
        }
    }

    private fun showFilterSheet(copilotFilterItem: CopilotFilterItem, filterType: FilterType) {
        val sideSheetDialog = SideSheetDialog(this).apply {
            filterSheetBinding.root.parent?.let {
                (it as? ViewGroup)?.removeView(filterSheetBinding.root)
            }
            setContentView(filterSheetBinding.root)
        }
        with(filterSheetBinding) {
            when (filterType) {
                FilterType.FOLDER -> {
                    tvTitleFolder.visibility = View.VISIBLE
                    tvTitleSchedule.visibility = View.INVISIBLE
                }

                FilterType.SCHEDULE -> {
                    tvTitleSchedule.visibility = View.VISIBLE
                    tvTitleFolder.visibility = View.INVISIBLE
                }
            }
            rvFilters.adapter = CopilotFilterListAdapter(copilotFilterItem.items) { selectedItem ->
                viewModel.applyFilter(selectedItem)
                sideSheetDialog.dismiss()
            }
        }
        sideSheetDialog.show()
    }

    private fun ActivityHomeBinding.setupCopilotRecyclerView() {
        val dividerItemDecoration =
            DividerItemDecoration(rvList.context, LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(
                this@HomeActivity,
                R.drawable.copilot_item_divider
            )!!
        )
        rvList.addItemDecoration(dividerItemDecoration)
    }

    private fun setupObservers() {
        generalViewModel.getAuthenticationLive().observe(this) {
            it?.let {
                binding.layoutToolbar.tvTitle.text =
                    getString(R.string.you_are_logged_in_as, it.name)
            }
        }
        viewModel.coPilotsList.observe(this) {
            binding.layoutShimmer.root.isVisible = false

            if (it.isNullOrEmpty()) {
                binding.tvFolderFilter.visibility = View.VISIBLE
            } else {
                binding.tvFolderFilter.visibility = View.GONE
                copilotListAdapter.submitList(it)
                binding.listGroup.isVisible = true
            }
        }
        viewModel.showFilterTag.observe(this) {
            with(binding) {
                tvFolderFilterTag.visibility =
                    if ((it.first == FilterType.FOLDER) && it.second) View.VISIBLE else View.INVISIBLE
                tvScheduleFilterTag.visibility =
                    if ((it.first == FilterType.SCHEDULE) && it.second) View.VISIBLE else View.INVISIBLE
            }
        }
    }

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}