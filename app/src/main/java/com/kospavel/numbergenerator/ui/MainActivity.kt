package com.kospavel.numbergenerator.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.kospavel.numbergenerator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            this.replace(
                binding.fragmentContainer.id,
                NumbersFragment.newFragment(SequenceType.SIMPLE)
            )
        }.commit()

        binding.tabLayout.apply {
            for (seqType in SequenceType.values()) {
                addTab(this.newTab().setText(seqType.nameId))
            }

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    for (seqType in SequenceType.values()) {
                        if (getText(seqType.nameId) == tab?.text) {
                            val fragment = NumbersFragment.newFragment(seqType)
                            supportFragmentManager.beginTransaction().apply {
                                this.replace(binding.fragmentContainer.id, fragment)
                            }.commit()
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }

    }
}