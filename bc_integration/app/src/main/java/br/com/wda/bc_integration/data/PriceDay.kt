package br.com.wda.bc_integration.data

import br.com.wda.bc_integration.enums.Coin
import java.math.BigDecimal
import java.time.LocalDate

data class PriceDay(
    var coin: Coin,
    var localData: LocalDate,
    var purchaseValue: BigDecimal,
    var saleValue: BigDecimal
)
