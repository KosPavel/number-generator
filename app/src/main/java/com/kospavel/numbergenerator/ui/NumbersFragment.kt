package com.kospavel.numbergenerator.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kospavel.numbergenerator.R
import com.kospavel.numbergenerator.SequenceType
import com.kospavel.numbergenerator.base.BaseFragment
import com.kospavel.numbergenerator.databinding.FragmentRecyclerViewBinding
import com.kospavel.numbergenerator.vm.SequenceViewModel
import com.kospavel.numbergenerator.vm.SequenceViewModelFactory

class NumbersFragment : BaseFragment<FragmentRecyclerViewBinding>(
    R.layout.fragment_recycler_view,
    FragmentRecyclerViewBinding::inflate
) {

    private lateinit var vm: SequenceViewModel
    private lateinit var vmf: SequenceViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val type: SequenceType = requireArguments().getSerializable(TYPE_KEY) as SequenceType

        vmf = SequenceViewModelFactory(type)
        vm = ViewModelProvider(this, vmf).get(SequenceViewModel::class.java)

        val mainAdapter = MainAdapter(more = { vm.loadMore() }, less = { vm.loadLess() })
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mainAdapter
            setHasFixedSize(true)
        }

        vm.items.observe(viewLifecycleOwner) {
            mainAdapter.items = it
            mainAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        private const val TYPE_KEY = "type"

        fun getFragment(type: SequenceType): NumbersFragment {
            return NumbersFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(TYPE_KEY, type)
                }
            }
        }
    }

}