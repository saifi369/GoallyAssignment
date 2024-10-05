package com.android.goally.ui.copilotdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.android.goally.R
import com.android.goally.data.model.api.response.copilot.ActivityX
import com.android.goally.databinding.CopilotDetailListItemBinding
import com.bumptech.glide.Glide

class CopilotDetailListAdapter(
    private val copilotList: List<ActivityX>,
) :
    RecyclerView.Adapter<CopilotDetailListAdapter.CopilotDetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CopilotDetailsViewHolder {
        val binding =
            CopilotDetailListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CopilotDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CopilotDetailsViewHolder, position: Int) {
        val copilot = copilotList[position]

        with(holder.binding) {
            tvTitle.text = copilot.name

            Glide.with(ivTitle)
                .load(copilot.imgURL)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(ivTitle)

            root.background = if (position % 2 == 0) {
                AppCompatResources.getDrawable(root.context, android.R.color.white)
            } else {
                AppCompatResources.getDrawable(root.context, R.color.activityDetailItemColor)
            }
        }
    }

    override fun getItemCount() = copilotList.size

    class CopilotDetailsViewHolder(val binding: CopilotDetailListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}