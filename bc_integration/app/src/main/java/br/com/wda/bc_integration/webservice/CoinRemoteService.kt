package br.com.wda.bc_integration.webservice

import br.com.wda.bc_integration.response.BcResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigDecimal

interface CoinRemoteService {

    @GET("/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)")
    suspend fun coinPrice(
    @Query("@moeda", encoded = true) moeda: String,
    @Query("@dataCotacao", encoded = true) dataCotacao: String,
    @Query("top", encoded = true) top: String,
    @Query("orderby", encoded = true) orderby: String,
    @Query("format", encoded = true) format: String,
    @Query("select", encoded = true) select: String) : BcResponse

    @GET("/bc_moeda/rest/converter/{value}/{parity}/{coinFrom}/{coinTo}/{datePrice}")
    suspend fun coinConverter(@Path("value") value: BigDecimal,
                              @Path("parity") parity: Int,
                              @Path("coinFrom") coinFrom: Int,
                              @Path("coinTo") coinTo: Int,
                              @Path("datePrice") datePrice: String): String

}