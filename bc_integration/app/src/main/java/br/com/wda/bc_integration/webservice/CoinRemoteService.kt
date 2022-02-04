package br.com.wda.bc_integration.webservice

import retrofit2.http.GET

interface CoinRemoteService {

    @GET("https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaDia(moeda='%s',dataCotacao='%s')?{$}top=1&{$}orderby=dataHoraCotacao%%20desc&{$}format=json&{$}select=cotacaoCompra,cotacaoVenda")
    suspend fun coinPrice()

}