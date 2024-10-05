package com.android.goally.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.goally.R
import com.android.goally.databinding.CopilotListItemBinding
import com.android.goally.ui.home.model.CopilotListItem
import com.bumptech.glide.Glide

class CopilotListAdapter(
    private val copilotList: MutableList<CopilotListItem>,
    private val onCopilotClick: (CopilotListItem) -> Unit
) :
    RecyclerView.Adapter<CopilotListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CopilotListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val copilot = copilotList[position]

        with(holder.binding) {
            tvTitle.text = copilot.name
            tvDays.text = copilot.displaySchedule
            tvFolderName.text = copilot.folderName

            Glide.with(ivTitle)
                .load(copilot.imageUrl)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(ivTitle)

            root.setOnClickListener {
                onCopilotClick(copilot)
            }
        }
    }

    override fun getItemCount() = copilotList.size

    fun submitList(list: List<CopilotListItem>) {
        copilotList.clear()
        copilotList.addAll(list)
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: CopilotListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}