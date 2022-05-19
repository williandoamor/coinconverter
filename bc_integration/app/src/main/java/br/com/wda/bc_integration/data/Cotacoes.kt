package br.com.wda.bc_integration.data

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.math.BigDecimal

@Root(strict = false, name = "cotacoes")
data class Cotacoes @JvmOverloads constructor(

    @field:Element(name = "dataHoraCotacao", required = true)
    @param:Element(name = "dataHoraCotacao")
    @SerializedName("dataHoraCotacao")
    var dataHoraCotacao: String = "",

    @field:Element(name = "horaBoletim", required = false)
    @param:Element(name = "horaBoletim")
    @SerializedName("horaBoletim")
    var horaBoletim: String = "",

    @field:Element(name = "numero", required = false)
    @param:Element(name = "numero")
    @SerializedName("numero")
    var numero: String = "",

    @field:Element(name = "paridadeCompra", required = false)
    @param:Element(name = "paridadeCompra")
    @SerializedName("paridadeCompra")
    var paridadeCompra: BigDecimal = BigDecimal("0"),

    @field:Element(name = "paridadeVenda", required = false)
    @param:Element(name = "paridadeVenda")
    @SerializedName("paridadeVenda")
    var paridadeVenda: BigDecimal = BigDecimal("0"),

    @field:Element(name = "taxaCompra", required = false)
    @param:Element(name = "taxaCompra")
    @SerializedName("taxaCompra")
    var taxaCompra: BigDecimal = BigDecimal("0"),

    @field:Element(name = "taxaVenda", required = false)
    @param:Element(name = "taxaVenda")
    @SerializedName("taxaVenda")
    var taxaVenda: BigDecimal = BigDecimal("0"),

    @field:Element(name = "tipoCotacao", required = false)
    @param:Element(name = "tipoCotacao")
    @SerializedName("tipoCotacao")
    var tipoCotacao: String = "",

)