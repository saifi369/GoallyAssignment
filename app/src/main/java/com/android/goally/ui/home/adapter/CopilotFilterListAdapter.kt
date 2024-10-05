package com.android.goally.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.goally.databinding.LayoutFilterSheetItemBinding
import com.android.goally.ui.home.model.FilterItem

class CopilotFilterListAdapter(
    private val itemList: List<FilterItem>,
    private val onCopilotClick: (FilterItem) -> Unit
) :
    RecyclerView.Adapter<CopilotFilterListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            LayoutFilterSheetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val filterItem = itemList[position]

        with(holder.binding) {
            tvCount.text = filterItem.count.toString()
            rbSelection.text = filterItem.title
            rbSelection.isChecked = filterItem.isSelected

            rbSelection.setOnCheckedChangeListener { _, _ ->
                onCopilotClick(filterItem)
            }
        }
    }

    override fun getItemCount() = itemList.size

    class MyViewHolder(val binding: LayoutFilterSheetItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}