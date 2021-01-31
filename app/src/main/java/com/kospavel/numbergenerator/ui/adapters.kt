package com.kospavel.numbergenerator.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kospavel.numbergenerator.Content
//import com.kospavel.numbergenerator.LoadNext
//import com.kospavel.numbergenerator.LoadPrevious
import com.kospavel.numbergenerator.R
import com.kospavel.numbergenerator.databinding.ItemNumberViewBinding

class MainAdapter(private val loadNext: () -> Unit, private val loadPrevious: () -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = emptyList<Content>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                val itemBinding = ItemNumberViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                NumberViewHolder(itemBinding)
            }
            2 -> {
                val layout =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_loading, parent, false) as LinearLayout
                LoadMoreViewHolder(layout)
            }
            else -> {
                val layout =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_loading, parent, false) as LinearLayout
                LoadLessViewHolder(layout)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            1 -> {
                holder.itemView.apply {
                    val number = items[position] as com.kospavel.numbergenerator.Number
                    holder as NumberViewHolder
                    holder.bind(number = number)
                    if (number.loadNext) {
                        loadNext()
                    }
                    if (number.loadPrev) {
                        loadPrevious()
                    }
                }
            }
//            2 -> {
//                loadNext()
//            }
//            3 -> {
//                loadPrevious()
//            }
            else -> {
                Log.i("qwerty", "${items[position]}")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is com.kospavel.numbergenerator.Number -> 1
//            is LoadNext -> 2
//            is LoadPrevious -> 3
            else -> 0
        }
    }

    override fun getItemCount(): Int = items.size

    class NumberViewHolder(private val binding: ItemNumberViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(number: com.kospavel.numbergenerator.Number) {
            binding.numberValue.text = number.value.toString()
            val color = if (number.white) {
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

    class LoadMoreViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup)
    class LoadLessViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup)

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