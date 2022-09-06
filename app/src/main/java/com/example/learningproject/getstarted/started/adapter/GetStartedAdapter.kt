package com.example.learningproject.getstarted.started.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.R
import com.example.learningproject.databinding.FragmentOneBinding

class GetStartedAdapter(private val list: List<Int>) :
    RecyclerView.Adapter<GetStartedAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: FragmentOneBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.imgOne.setImageResource(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        return ViewHolder(DataBindingUtil.inflate(view, R.layout.fragment_one, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int {
        return list.size
    }
}