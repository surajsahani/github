package com.example.github.ui.trendings

import androidx.recyclerview.widget.RecyclerView


class DataBindingViewHolder(private val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bindVariable(variableId: Int, obj: Any) {
        binding.setVariable(variableId, obj)
        binding.executePendingBindings()
    }
}
