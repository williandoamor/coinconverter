package br.com.wda.bc_integration.response

import java.math.BigDecimal

data class BcResponse (
    var paridadeCompra: BigDecimal = BigDecimal("0"),
    var paridadeVenda: BigDecimal = BigDecimal("0"),
    var cotacaoCompra: BigDecimal = BigDecimal("0"),
    var cotacaoVenda: BigDecimal = BigDecimal("0"),
    var dataHoraCotacao: String = "",
    var tipoBoletim: String = ""
)