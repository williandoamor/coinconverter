package br.com.wda.bc_integration.utils

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class Numeric {
    object Numeric {
        /**
         * @param Recebe
         * uma String com o valor
         * @return Retorna o valor em BigDecimal
         */
        fun textForBigDecimal(text: String?): BigDecimal {
            var text = text
            var valor = BigDecimal(0)
            return try {

                /*
                  * Caso o text estiver vazio ou null, então retorna um valor zerado
                  */
                if (text != null && text.trim { it <= ' ' } != "") {

                    /*
                      * Verificar se tem ponto e virgula
                      */
                    if (text.contains(".") && text.contains(",")) {

                        // Remove o ponto. Ex.: 1.234,56 fica 1234,56
                        text = text.replace(".", "")

                        // Substitui a virgula por ponto. Ex.: 1234,56 fica 1234.56
                        text = text.replace(",", ".")
                        valor = BigDecimal(text)
                    } else if (text.contains(".") && !text.contains(",")) valor =
                        BigDecimal(text) else if (!text.contains(".") && text.contains(",")) {

                        // Substitui a virgula por ponto. Ex.: 1,23 fica 1.23
                        text = text.replace(",", ".")
                        valor = BigDecimal(text)
                    } else valor = BigDecimal(text)
                }
                valor
            } catch (e: Exception) {
                BigDecimal(0)
            }
        }

        /**
         * @param Recebe
         * uma String com o valor
         * @return Retorna o valor em formato int
         */
        fun textForInt(text: String?): Int {
            var valor = 0
            return try {

                /*
                  * Caso o text estiver vazio ou null, então retorna um valor zerado
                  */
                if (text != null && text.trim { it <= ' ' } != "") {

                    /*
                      * Verificar se tem ponto e não tem virgula
                      */
                    valor = if (text.contains(".") && !text.contains(",")) text.substring(
                        0,
                        text.indexOf(".")
                    ).toInt() else if (!text.contains(".") && text.contains(",")) text.substring(
                        0,
                        text.indexOf(",")
                    ).toInt() else text.toInt()
                }
                valor
            } catch (e: Exception) {
                0
            }
        }

        fun format(valor: BigDecimal?, casasDecimais: Int): String {
            return formatar(valor, casasDecimais)
        }

        fun format(valor: String?, casasDecimais: Int): String {
            return formatar(textForBigDecimal(valor), casasDecimais)
        }

        /**
         * Recebe um valor em formato BigDecimal e converte para String, já de
         * acordo com a quantidade de casas decimais
         */
        private fun formatar(valor: BigDecimal?, casasDecimais: Int): String {

            /*
		 * Setar o modelo da formatação do valor
		 */
            val formato = ("###,###,##0."
                    + String.format("%0" + casasDecimais + "d", 0))

            /*
		 * Definir o local de aplicação do formato
		 */
            val local = Locale("pt", "BR")

            /*
		 * Setar os separadores decimais
		 */
            val simb = DecimalFormatSymbols(local)
            simb.setDecimalSeparator(',')
            simb.setGroupingSeparator('.')

            /*
		 * Formatar o valor recebido
		 */
            val format = DecimalFormat(formato, simb)
            return if (valor != null) {

                /*
                  * Setar a escala
                  */
                valor.setScale(8, BigDecimal.ROUND_HALF_UP)
                format.format(valor)
            } else {
                format.format(BigDecimal(0))
            }
        }

        /*
	 * Verifica se a String é um número inteiro válido
	 */
        fun isInt(value: String): Boolean {
            return try {
                value.toInt()
                true
            } catch (e: NumberFormatException) {
                false
            }
        }

        /*
	 * Verifica se o valor é um número inteiro válido
	 */
        fun isInt(value: Double): Boolean {
            return try {

                /*
                  * Se o resto da divisão por 1 for zero é inteiro
                  */
                value % 1 == 0.0
            } catch (e: NumberFormatException) {
                false
            }
        }

        /**
         * @param Recebe
         * uma String com o valor
         * @return Retorna o valor em Double
         */
        fun textForDouble(text: String?): Double {
            var text = text
            var valor = java.lang.Double.valueOf(0.0)
            return try {

                /*
                  * Caso o text estiver vazio ou null, então retorna um valor zerado
                  */
                if (text != null && text.trim { it <= ' ' } != "") {

                    /*
                      * Verificar se tem ponto e virgula
                      */
                    if (text.contains(".") && text.contains(",")) {

                        // Remove o ponto. Ex.: 1.234,56 fica 1234,56
                        text = text.replace(".", "")

                        // Substitui a virgula por ponto. Ex.: 1234,56 fica 1234.56
                        text = text.replace(",", ".")
                        valor = java.lang.Double.valueOf(text)
                    } else if (text.contains(".") && !text.contains(",")) valor =
                        java.lang.Double.valueOf(text) else if (!text.contains(".") && text.contains(
                            ","
                        )
                    ) {

                        // Substitui a virgula por ponto. Ex.: 1,23 fica 1.23
                        text = text.replace(",", ".")
                        valor = java.lang.Double.valueOf(text)
                    } else valor = java.lang.Double.valueOf(text)
                }
                valor
            } catch (e: Exception) {
                java.lang.Double.valueOf(0.0)
            }
        }
    }
}