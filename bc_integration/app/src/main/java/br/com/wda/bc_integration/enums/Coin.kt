package br.com.wda.bc_integration.enums

import java.util.*

enum class Coin(val country: String, val cod: Int, val locale: Locale) {

    /*Afeganistão*/
    AFN("Afegane/AFN",5, Locale("en","AF")),
    /*Madagáscar*/
    MGA("Ariary/MGA",406, Locale("mg","MA")),
    /*Panama*/
    PBA("Balboa/PBA",20, Locale("es-pa","PM")),
    /*Tailândia*/
    THB("Baht/THB",15, Locale("th","TH")),
    /*Etiópia*/
    ETB("Birr etíope/ETB",9, Locale("am","ET")),
    /*Venezuela*/
    VES("Bolivar Soberano Venezuelano/VES",27, Locale("es-ve", "VE")),
    /*Bolivia*/
    BOB("Boliviano/BOB",30, Locale("es-bo","BL")),
    /*Gana*/
    GHS("Cedi/GHS",35, Locale("en","GH")),
    /*Costa Rica*/
    CRC("Colon da Costa Rica/CRC",40, Locale("es-cr","CR")),
    /*El Salvador*/
    SVC("Colon de El Salvador/SVC",45, Locale("es-sv","ES")),
    /*Nicarágua*/
    NIO("Cordoba Ouro/NIO",51, Locale("es-ni", "NU")),
    /*Dinamarca*/
    DKK("Coroa dinamarquesa/DKK",55, Locale("da", "DA")),
    /*Islandia*/
    ISK("Coroa islandesa/ISK",60, Locale("is","IC")),
    /*Noruega*/
    NOK("Coroa norueguesa/NOK", 65,Locale("no", "NO")),
    /*Suecia*/
    SEK("Coroa sueca/SEK", 70, Locale("sv","SW")),
    /*Republica Checa*/
    CZK("Coroa tcheca/CZK",75, Locale("ce","CZ")),
    /*Gâmbia*/
    GMD("Dalasi/GMD", 90, Locale("en","GA")),
    /*Argélia*/
    DZD("Dinar argelino/DZD",95, Locale("ar-dz","AG")),
    /*Estados Unidos*/
    USD("Dólar dos Estados Unidos/USD",220, Locale.US),
    /*Sérvia*/
    RSD("Dinar sérvio/RSD", 133, Locale("sr","SRB")),
    /*Bahrein*/
    BHD("Dinar do Bahrein/BHD",105, Locale("ar","BHR")),
    /*Iraque*/
    IQD("Dinar iraquiano/IQD",115, Locale("ar-iq","IZ")),
    /*Jordania*/
    JOD("Dinar jordaniano/JOD",125, Locale("ar-jo","JO")),
    /*Kuwait*/
    KWD("Dinar do Kuwait/KWD",100, Locale("ar-kw","KW")),
    /*Líbia*/
    LYD("Dinar da Líbia/LYD",130, Locale("ar-ly","LY")),
    /*Macedónia do Norte*/
    MKD("Dinar macedonio/MKD",132, Locale("mk","MK")),
    /*Tunísia*/
    TND("Dinar tunisiano/TND",135,Locale("ar-tn","TN")),
    /*instrumento monetário internacional,*/

    /*Australia*/
    //AUD(Locale("en","AU")),
    /*Canada*/
    //CAD(Locale.CANADA),
    /*Uniao Europeia*/
    //EUR(Locale.GERMAN),
    /*Suica*/
    //CHF(Locale("fr","CH")),
    /*Japao*/
    //JPY(Locale.JAPAN),
    /*Reunio Unidado*/
    //GBP(Locale("en","GB")),
    /*Brasil*/
    BRL("Brazil/BRL",790, Locale("pt-br", "BR"));


    companion object {
        fun getByName(name: String) = values().find {
            it.name == name ?: BRL
        }
    }

    override fun toString(): String {
        return this.country
    }
}