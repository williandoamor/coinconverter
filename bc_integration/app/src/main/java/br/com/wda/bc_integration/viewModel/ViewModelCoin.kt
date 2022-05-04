package br.com.wda.bc_integration.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.wda.bc_integration.repository.CoinRepository
import br.com.wda.bc_integration.response.BcResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.math.BigDecimal

class ViewModelCoin(private val repository: CoinRepository) : ViewModel() {

    private val TAG: String = "ViewModelCoin"

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    /*
    * Performs the quotation of the currency passed by the user*/
   fun getCoinPrice(coin: Int, dataCotacao: String) {
        viewModelScope.launch {
            repository.converterCoin(coin, dataCotacao)
                .flowOn(Dispatchers.Main)
                .onStart {
                    _state.postValue(State.Loading)
                }
                .catch {
                    Log.e(TAG, it.toString())
                    _state.postValue(State.Error(it))
                }
                .collect {
                    _state.postValue(State.Sucess(it))
                    Log.e("COIN ", it.toString())
                }
        }
    }

    /*
    * Performs the quotation of the currency passed by the user*/
    fun getCoinConverter(value: BigDecimal, parity: Int,
                         coinFrom: Int, coinTo: Int,
                         datePrice: String) {
        viewModelScope.launch {
            repository.coinConverter(value, parity, coinFrom, coinTo, datePrice)
                .flowOn(Dispatchers.Main)
                .onStart {
                    _state.postValue(State.Loading)
                }
                .catch {
                    Log.e(TAG, it.toString())
                    _state.postValue(State.Error(it))
                }
                .collect {
                   // _state.postValue(State.Sucess(it))
                    Log.e("COIN ", it.toString())
                }
        }
    }


    sealed class State {
        object Loading : State()
        object Saved : State()

        data class Sucess(val bcResponse: BcResponse) : State()
        data class Error(val error: Throwable) : State()
    }
}