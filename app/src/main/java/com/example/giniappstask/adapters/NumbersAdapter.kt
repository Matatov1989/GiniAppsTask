package com.example.giniappstask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.giniappstask.R

class NumbersAdapter(private val numbers: List<Int>) :
    ListAdapter<Int, NumberViewHolder>(NumberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_number, parent, false)
        return NumberViewHolder(view, numbers)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val number = getItem(position)
        holder.bind(number)
    }
}

class NumberViewHolder(itemView: View, val numbers: List<Int>) : RecyclerView.ViewHolder(itemView) {
    private val numberTextView: TextView = itemView.findViewById(R.id.numberTextView)

    fun bind(number: Int) {
        numberTextView.text = number.toString()

        if (numbers.contains(number * -1) && number != 0) {

            val layoutParams = numberTextView.layoutParams
            layoutParams.height = itemView.context.resources.getDimensionPixelSize(R.dimen.item_height_150dp)
            numberTextView.setBackgroundColor(itemView.context.resources.getColor(R.color.red))
            numberTextView.layoutParams = layoutParams

        } else {
            val layoutParams = numberTextView.layoutParams
            layoutParams.height = 100
            numberTextView.setBackgroundColor(itemView.context.resources.getColor(R.color.orange))
            numberTextView.layoutParams = layoutParams
        }
    }
}

class NumberDiffCallback : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
}
