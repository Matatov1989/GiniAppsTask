package com.example.giniappstask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.giniappstask.R

class NumbersAdapter(private val numbers: List<Int>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_RED = 1
        private const val VIEW_ORANGE = 2
    }


    class ViewHolderRed(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numberTextViewRed: TextView = itemView.findViewById(R.id.numberTextView)
    }

    class ViewHolderOrange(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numberTextViewOrange: TextView = itemView.findViewById(R.id.numberTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_RED -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_number_red, parent, false)
                ViewHolderRed(view)
            }
            VIEW_ORANGE -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_number_orange, parent, false)
                ViewHolderOrange(view)
            }
            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderRed -> {
                holder.numberTextViewRed.text = numbers[position].toString()
            }
            is ViewHolderOrange -> {
                holder.numberTextViewOrange.text = numbers[position].toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return numbers.size
    }

    override fun getItemViewType(position: Int): Int {
        val number = numbers[position]

        return if (numbers.contains(number * -1) && number != 0) VIEW_RED
        else VIEW_ORANGE
    }
}
