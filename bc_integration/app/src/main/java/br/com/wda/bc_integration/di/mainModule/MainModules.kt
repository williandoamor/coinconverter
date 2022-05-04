package br.com.wda.bc_integration.di.mainModule

import br.com.wda.bc_integration.viewModel.ViewModelCoin
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object MainModules {


    fun load(){
        loadKoinModules(mainConnection())
    }

    private fun mainConnection(): Module {
      return module {

          viewModel{ViewModelCoin(get())}
      }
    }
}