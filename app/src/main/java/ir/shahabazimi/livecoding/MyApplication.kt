package ir.shahabazimi.livecoding

import android.app.Application
import ir.shahabazimi.data.di.dataModule
import ir.shahabazimi.domain.di.domainModule
import ir.shahabazimi.livecoding.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule, dataModule, domainModule)
        }
    }
}