package com.sid.numberhighlighter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sid.numberhighlighter.databinding.ItemNumberBinding

class NumberAdapter : ListAdapter<NumberModel, NumberAdapter.NumberViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val binding = ItemNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val number = getItem(position)
        holder.bind(number)
    }

    inner class NumberViewHolder(private val binding: ItemNumberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(number: NumberModel) {
            binding.numberText.text = number.value.toString()
            val backgroundColor = if (number.isHighlighted) {
                Color.YELLOW
            } else {
                Color.WHITE
            }
            binding.numberText.setBackgroundColor(backgroundColor)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<NumberModel>() {
        override fun areItemsTheSame(oldItem: NumberModel, newItem: NumberModel) = oldItem.value == newItem.value
        override fun areContentsTheSame(oldItem: NumberModel, newItem: NumberModel) = oldItem == newItem
    }
}
