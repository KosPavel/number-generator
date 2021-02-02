package com.kospavel.numbergenerator.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.kospavel.numbergenerator.SequenceType
import com.kospavel.numbergenerator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = SequencesCollectionAdapter(
            this,
            listOf(
                NumbersFragment.getFragment(SequenceType.PRIME),
                NumbersFragment.getFragment(SequenceType.FIBONACCI),
            )
        )

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setText(SequenceType.values()[position].nameId)
        }.attach()

    }
}