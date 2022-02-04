package br.com.wda.bc_integration.enums

import java.util.*

enum class Coin(val locale: Locale) {
    /*Dinamarca*/
    DKK(Locale("da", "DKK")),
    /*Noruega*/
    NOK(Locale("no", "NO")),
    /*Suecia*/
    SEK(Locale("sv","SE")),
    /*Estados Unidos*/
    USD(Locale.US),
    /*Australia*/
    AUD(Locale("en","AU")),
    /*Canada*/
    CAD(Locale.CANADA),
    /*Uniao Europeia*/
    EUR(Locale.GERMAN),
    /*Suica*/
    CHF(Locale("fr","CH")),
    /*Japao*/
    JPY(Locale.JAPAN),
    /*Reunio Unidado*/
    GBP(Locale("en","GB")),
    /*Brasil*/
    BRL(Locale("pt", "BR"));


    companion object {
        fun getByName(name: String) = values().find {
            it.name == name ?: BRL
        }
    }
}