package br.com.wda.bc_integration.repository

import android.os.RemoteException
import br.com.wda.bc_integration.enums.Coin
import br.com.wda.bc_integration.response.ErrorResponse
import br.com.wda.bc_integration.webservice.CoinRemoteService
import com.google.gson.Gson
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.math.BigDecimal

class CoinRepository(private val service: CoinRemoteService) {

    suspend fun converterCoin(coin: Int, dateCotacao: String) = flow {
        try {
            val coinReturn = service.coinPrice(
                "%27$coin%27", "%27$dateCotacao%27",
                "1", "dataHoraCotacao desc", "json", "cotacaoCompra,cotacaoVenda,dataHoraCotacao"
            )
            emit(coinReturn)
        } catch (e: HttpException) {
            val json = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(json, ErrorResponse::class.java)
            throw RemoteException(errorResponse.message)

        }
    }

    suspend fun coinSales(coin: Int, parity: Int, datePrice: String) = flow {
        try {
            emit(service.coinSales(coin, parity, datePrice))
        }catch (exception: HttpException){
            val json = exception.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(json, ErrorResponse::class.java)
            throw RemoteException(errorResponse.message)
        }
    }

    suspend fun coinConverter(value: BigDecimal, parity: Int,
                              coinFrom: Int, coinTo: Int,
                              datePrice: String) = flow {
        try {
            emit(service.coinConverter(value, parity, coinFrom, coinTo, datePrice))
        } catch (e: HttpException) {
            val json = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(json, ErrorResponse::class.java)
            throw RemoteException(errorResponse.message)

        }
    }
}