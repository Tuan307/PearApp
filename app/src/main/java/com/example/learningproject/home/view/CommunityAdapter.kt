package com.example.learningproject.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.CommunityPostItemBinding
import com.example.learningproject.home.model.CommunityPost
import com.example.learningproject.home.model.MyDiffUtil

class CommunityAdapter(private val listener: (CommunityPost) -> Unit) :
    RecyclerView.Adapter<CommunityAdapter.ViewHolder>() {

    private var list = emptyList<CommunityPost>()

    inner class ViewHolder(val binding: CommunityPostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CommunityPost) {
            binding.apply {
                txtCommunityDate.text = item.date
                txtItemName.text = item.publisher
                txtCommunityDiary.text = item.text

                txtCommunityDiary.setOnClickListener {
                    this@CommunityAdapter.listener(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CommunityPostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(newPostList: List<CommunityPost>) {
        val diffUtil = MDiffUtil(oldList = list, newList = newPostList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list = newPostList
        diffResult.dispatchUpdatesTo(this)
    }

    class MDiffUtil(
        private var oldList: List<CommunityPost>,
        private var newList: List<CommunityPost>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }
    }
}