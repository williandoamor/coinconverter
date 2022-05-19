package br.com.wda.bc_integration.data

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false, name = "Cotacao")
data class Cotacao @JvmOverloads constructor (

    @field:Element(name = "codigoMoeda", required = true)
    @param:Element(name = "codigoMoeda")
    @SerializedName("codigoMoeda")
    var codigoMoeda: String = "",

    @field:Element(name = "cotacaoBoletim", required = false)
    @param:Element(name = "cotacaoBoletim")
    @SerializedName("cotacaoBoletim")
    var cotacaoBoletim: Boolean = false,

    @field:Element(name = "cotacaoContabilidade", required = false)
    @param:Element(name = "cotacaoContabilidade")
    @SerializedName("cotacaoContabilidade")
    var cotacaoContabilidade: Boolean = false,

    @field:Element(name = "cotacoes", required = true)
    @param:Element(name = "cotacoes")
    @SerializedName("cotacoes")
    var cotacoes: Cotacoes = Cotacoes(),

    @field:Element(name = "data", required = true)
    @param:Element(name = "data")
    @SerializedName("data")
    var data: String = "",

    @field:Element(name = "tipoMoeda", required = false)
    @param:Element(name = "tipoMoeda")
    @SerializedName("tipoMoeda")
    var tipoMoeda: String = "",
)
