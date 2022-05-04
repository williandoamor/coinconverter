package br.com.wda.bc_integration.utils

import androidx.room.TypeConverter
import java.lang.Exception
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class Converter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromBigDecimal(value: BigDecimal?): String {

            try {
                /*Setar o modelo da formatação do valor*/
                val formato = ("###,###,##0."
                        + String.format("%0 d", 0))

                /*Definir o local de aplicação do formato*/
                val local = Locale("pt", "BR")

                /*Setar os separadores decimais*/
                val simb = DecimalFormatSymbols(local)
                simb.decimalSeparator = ','
                simb.groupingSeparator = '.'

                /*Formatar o valor recebido*/
                val format = DecimalFormat(formato, simb)

                return if (value != null) {
                    /*Setar a escala*/
                    value.setScale(2, BigDecimal.ROUND_HALF_UP)
                    format.format(value)

                } else {
                    format.format(BigDecimal(0))
                }
            } catch (exception: Exception) {
                return ""
            }


        }

        @TypeConverter
        @JvmStatic
        fun toBigDecimal(value: String?): BigDecimal {
            var text: String? = value
            var valor = BigDecimal(0)

            return try {
                /*Caso o text estiver vazio ou null,
                 então retorna um valor zerado*/
                if (text != null && text.trim { it <= ' ' } != "") {

                    /*Verificar se tem ponto e virgula*/
                    if (text.contains(".") && text.contains(",")) {

                        // Remove o ponto. Ex.: 1.234,56 fica 1234,56
                        text = text.replace(".", "")
                        valor = BigDecimal(text)
                    } else if (text.contains(".") && !text.contains(",")) {
                        valor = BigDecimal(text)
                    } else if (!text.contains(".") && text.contains(",")) {

                        //Substitui a virgula por ponto. Ex.: 1,23 fica 1.23
                        text = text.replace(",", ".")
                        valor = BigDecimal(text)
                    } else {
                        valor = BigDecimal(text)
                    }
                }

                valor
            } catch (exception: Exception) {
                BigDecimal(0)
            }
        }
    }
}