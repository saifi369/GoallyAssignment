package com.android.goally.ui.copilotdetail

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.android.goally.BaseActivity
import com.android.goally.data.model.api.response.copilot.ActivityX
import com.android.goally.databinding.ActivityCopilotDetailBinding
import com.android.goally.ui.copilotdetail.adapter.CopilotDetailListAdapter
import com.android.goally.ui.home.model.CopilotListItem
import com.android.goally.ui.utils.COPILOT_ACTIVITY_INTENT_EXTRA_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CopilotDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityCopilotDetailBinding
    private lateinit var copilotDetailListAdapter: CopilotDetailListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCopilotDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val copilot = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(
                COPILOT_ACTIVITY_INTENT_EXTRA_KEY,
                CopilotListItem::class.java
            )
        } else {
            intent.getParcelableExtra(COPILOT_ACTIVITY_INTENT_EXTRA_KEY)
        }
        setUpToolbar(copilot?.name ?: "")
        if (!copilot?.activities.isNullOrEmpty()) {
            setUpAdapter(copilot?.activities!!)
        }
    }

    private fun setUpToolbar(title: String) = with(binding) {
        setSupportActionBar(layoutToolbar.customToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        layoutToolbar.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        layoutToolbar.tvTitle.text = title
    }

    private fun setUpAdapter(list: List<ActivityX>) {
        copilotDetailListAdapter = CopilotDetailListAdapter(list)
        binding.rvDetailList.adapter = copilotDetailListAdapter
    }

    companion object {
        fun getCallingIntent(context: Context, copilot: CopilotListItem): Intent {
            return Intent(context, CopilotDetailActivity::class.java).apply {
                putExtra(COPILOT_ACTIVITY_INTENT_EXTRA_KEY, copilot)

            }
        }
    }
}