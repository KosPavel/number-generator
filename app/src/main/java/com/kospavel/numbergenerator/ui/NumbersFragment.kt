package com.kospavel.numbergenerator.ui

import android.os.Bundle
import android.view.View
import com.kospavel.numbergenerator.R
import com.kospavel.numbergenerator.base.BaseFragment
import com.kospavel.numbergenerator.databinding.FragmentRecyclerViewBinding
import com.kospavel.numbergenerator.vm.SequenceViewModel
import com.kospavel.numbergenerator.vm.SequenceViewModelFactory

class NumbersFragment : BaseFragment<FragmentRecyclerViewBinding>(
    R.layout.fragment_recycler_view,
    FragmentRecyclerViewBinding::inflate
) {

    private lateinit var vm: SequenceViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val type: SequenceType = requireArguments().getSerializable(TYPE_KEY) as SequenceType
        vm = SequenceViewModelFactory(type).create(SequenceViewModel::class.java)
    }

    companion object {
        private const val TYPE_KEY = "type"
        private val instances = mutableMapOf<String, NumbersFragment>()

        fun newFragment(type: SequenceType): NumbersFragment {
            if (!instances.containsKey(type.name)) {
                val instance = NumbersFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(TYPE_KEY, type)
                    }
                }
                instances[type.name] = instance
            }
            return instances[type.name]!!
        }
    }

}