package com.kospavel.numbergenerator.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kospavel.numbergenerator.Number
import com.kospavel.numbergenerator.R
import com.kospavel.numbergenerator.databinding.ItemNumberViewBinding

class MainAdapter(private val loadNext: () -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = emptyList<Number>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = ItemNumberViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return NumberViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            val number = items[position] as com.kospavel.numbergenerator.Number
            holder as NumberViewHolder
            holder.bind(number = number)
            if (number.loadNext) {
                loadNext()
            }
        }
    }

    override fun getItemCount(): Int = items.size

    class NumberViewHolder(private val binding: ItemNumberViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(number: com.kospavel.numbergenerator.Number) {
            binding.numberValue.text = String.format(number.value.toString())
            val color = if (number.white!!) {
                R.color.white
            } else {
                R.color.black
            }
            binding.numberBackground.setCardBackgroundColor(
                ContextCompat.getColor(
                    binding.numberBackground.context,
                    color
                )
            )
        }
    }

}

class SequencesCollectionAdapter(
    activity: MainActivity,
    private val pages: List<Fragment>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }
}