package com.kospavel.numbergenerator.di

import com.kospavel.numbergenerator.ui.NumbersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(numbersFragment: NumbersFragment)
}