package br.com.me.san.apprepositorios.domain.di

import br.com.me.san.apprepositorios.data.di.DataModule
import br.com.me.san.apprepositorios.domain.ListUserRepositoriesUserCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {
    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory { ListUserRepositoriesUserCase(get()) }
        }
    }
}