package com.example.learningproject.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.R
import com.example.learningproject.databinding.DiaryItemBinding
import com.example.learningproject.home.model.Post

class DiaryAdapter(private val list: List<Post>) : RecyclerView.Adapter<DiaryAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: DiaryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            binding.model = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.diary_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int {
        return list.size
    }
}