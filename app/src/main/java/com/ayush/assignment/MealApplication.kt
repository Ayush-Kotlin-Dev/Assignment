package com.ayush.assignment

import android.app.Application
import com.ayush.assignment.di.appModule
import com.ayush.assignment.di.dataModule
import com.ayush.assignment.di.domainModule
import com.ayush.assignment.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MealApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger(Level.ERROR) // Use ERROR level to avoid Koin Android logger issues
            androidContext(this@MealApplication)
            modules(
                appModule,
                dataModule,
                domainModule,
                viewModelModule
            )
        }
    }
}