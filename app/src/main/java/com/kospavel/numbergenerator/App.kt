package com.kospavel.numbergenerator

import android.app.Application
import android.content.Context
import com.kospavel.numbergenerator.di.AppComponent
import com.kospavel.numbergenerator.di.AppModule
import com.kospavel.numbergenerator.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    init {
        initGraph()
    }

    private fun initGraph() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        fun get(context: Context): App =
            context.applicationContext as App
    }

}