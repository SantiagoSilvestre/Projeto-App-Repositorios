package br.com.me.san.apprepositorios

import android.app.Application
import br.com.me.san.apprepositorios.data.di.DataModule
import br.com.me.san.apprepositorios.domain.di.DomainModule
import br.com.me.san.apprepositorios.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()

    }

}