package com.example.github.ui.trendings


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.github.R
import com.example.github.domain.Repo


class ReposRecyclerAdapter(val repos: MutableList<Repo>)
    : RecyclerView.Adapter<DataBindingViewHolder>() {
    var onRepoItemClickListener: ((Repo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            DataBindingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.row_repo, parent, false)
        return DataBindingViewHolder(binding)
    }

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        holder.bindVariable(BR.repo, repos[position])
        holder.itemView.setOnClickListener { _ ->
            onRepoItemClickListener?.let { it(repos[holder.adapterPosition]) }
        }
    }

}