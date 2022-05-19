package br.com.wda.bc_integration.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModel
import br.com.wda.bc_integration.R
import br.com.wda.bc_integration.databinding.ActivityMainBinding
import br.com.wda.bc_integration.enums.Coin
import br.com.wda.bc_integration.extensions.*
import br.com.wda.bc_integration.utils.MoneyTextWatcher
import br.com.wda.bc_integration.utils.Numeric
import br.com.wda.bc_integration.utils.getDate
import br.com.wda.bc_integration.utils.getSubstractDate
import br.com.wda.bc_integration.viewModel.ViewModelCoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.BigDecimal
import java.math.MathContext
import java.util.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private val viewlModel by viewModel<ViewModelCoin>()
    private val dialog by lazy { createProgressDialog() }

    private var codCoinFrom by Delegates.notNull<Int>()
    private var codCoinTo by Delegates.notNull<Int>()

    private lateinit var cotacaoDe: String
    private lateinit var cotacaoPara: String

    private lateinit var locateDe: Locale
    private lateinit var locatePara: Locale

    private lateinit var binding: ActivityMainBinding
    private val btnConverter by lazy {
        binding.btnConverter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
        setAdapters()
        bindListeners()
        actionStateObserverCotacao()
        actionStateObserver()
        actionInsertMask()
    }

    private fun actionInsertMask() {
      binding.tilValue.editText?.addTextChangedListener(MoneyTextWatcher(binding.tilValue,
          Locale.getDefault()))
    }

    private fun initview() {
        if (binding.tilValue.isNotEmpty()) {
            btnConverter.isEnabled = false
        }
    }


    private fun setAdapters() {

        val list = Coin.values()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

        binding.tvFrom.setAdapter(adapter)
        binding.tvTo.setAdapter(adapter)

        binding.tvFrom.setText(Coin.BRL.country, false)
        codCoinFrom = Coin.BRL.cod
        cotacaoDe = Coin.BRL.country + " " + "("+Coin.BRL.cod+")"
        locateDe = Coin.BRL.locale

        binding.tvTo.setText(Coin.USD.country, false)
        codCoinTo = Coin.USD.cod
        cotacaoPara = Coin.USD.country + " " + "("+Coin.USD.cod+")"
        locatePara = Coin.USD.locale

        binding.tilFrom.setStartIconOnClickListener { it.hideSoftKeyboard() }
        binding.tvFrom.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->

                codCoinFrom = list[position].cod
                cotacaoDe = list[position].country + "("+list[position].cod+")"
                locateDe = list[position].locale

            }

        binding.tvTo.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                codCoinTo = list[position].cod
                cotacaoPara = list[position].country + "("+list[position].cod+")"
                locatePara = list[position].locale
            }
    }


    private fun bindListeners() {
        binding.tilValue.editText?.doAfterTextChanged {

            btnConverter.isEnabled = it != null && it.toString().isNotEmpty()
        }

        btnConverter.setOnClickListener {
            it.hideSoftKeyboard()

            viewlModel.getCoinSales(
                1,
                codCoinTo,
                getDate("yyyy-MM-dd")
            )

        }
    }

    private fun actionStateObserverCotacao() {
        viewlModel.state.observe(this) {
            when (it) {
                ViewModelCoin.State.Loading ->{
                    createDialog{
                        setMessage("Buscando cotação para $cotacaoPara")
                    }
                }
                is ViewModelCoin.State.Error -> {
                    dialog.dismiss()
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                }
                is ViewModelCoin.State.SalesSucess -> {
                   dialog.dismiss()
                   cotacao(it)
                    viewlModel.getCoinConverter(
                        Numeric.Numeric.textForBigDecimal(binding.tilValue.text),
                        it.cotacao.cotacoes.paridadeVenda.intValueExact(),
                        codCoinFrom,
                        codCoinTo,
                        it.cotacao.cotacoes.dataHoraCotacao.substring(0,10)
                    )
                }
            }
        }

    }

    private fun actionStateObserver() {
        viewlModel.state.observe(this) {
            when (it) {
                ViewModelCoin.State.Loading ->{
                    dialog.show()
                    createDialog{
                        setMessage("Cotação encontrada, convertendo Moeda. Por favor Aguarde!")
                    }
                }

                is ViewModelCoin.State.Error -> {
                    dialog.dismiss()
                    binding.llResult.visibility = View.GONE
                    binding.llBottom.visibility = View.GONE
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                }

                is ViewModelCoin.State.Sucess -> {
                    sucess(it)
                    binding.llResult.visibility = View.VISIBLE
                    binding.llBottom.visibility = View.VISIBLE
                }

                ViewModelCoin.State.Saved -> {
                    dialog.dismiss()
                    createDialog {
                        setMessage("Sucesso!")
                    }.show()
                }
            }
        }

    }

    private fun sucess(it: ViewModelCoin.State.Sucess) {
        dialog.dismiss()
        binding.tvConversionFrom.text = resources.getString(R.string.conversion_from) + cotacaoPara
        binding.tvVlrConversionFrom.text = resources.getString(R.string.conversion_result_from)
        binding.tvValueConversionFrom.text = BigDecimal(it.valorConvertido).formatCurrencyLocale(locatePara)

    }

    private fun cotacao(it: ViewModelCoin.State.SalesSucess){

        binding.tvConversionTo.text = resources.getString(R.string.conversion_to) +" "+ cotacaoDe
        binding.tvVlrConversionTo.text = resources.getString(R.string.value_coin_to_converter)
        binding.tvValueConversionTo.text = BigDecimal(binding.tilValue.text).formatCurrencyLocale(locateDe)


        binding.tvConversionFrom.text = resources.getString(R.string.conversion_result_from) + cotacaoPara
        binding.tvVlrConversionFrom.text = resources.getString(R.string.conversion_result_from)
        binding.tvValueConversionFrom.text = it.cotacao.cotacoes.taxaCompra.formatCurrencyLocale(locatePara)

        val ano = it.cotacao.cotacoes.dataHoraCotacao.substring(0, 4)
        val mes = it.cotacao.cotacoes.dataHoraCotacao.substring(5, 7)
        val dia = it.cotacao.cotacoes.dataHoraCotacao.substring(8, 10)
        val dataCotacao = "$dia/$mes/$ano"

        binding.tvDatePrice.text = resources.getString(R.string.date_price) +" "+ dataCotacao

        binding.tvTaxaTo.text = it.cotacao.cotacoes.paridadeVenda.intValueExact().toString() +" "+
                cotacaoDe +"="+
                  it.cotacao.cotacoes.paridadeVenda.divide(it.cotacao.cotacoes.taxaVenda, MathContext.DECIMAL32).setScale(8, BigDecimal.ROUND_HALF_EVEN) +" "+
                  cotacaoPara


        binding.tvTaxaFrom.text = it.cotacao.cotacoes.paridadeVenda.intValueExact().toString() +" "+
                cotacaoPara +"="+
                it.cotacao.cotacoes.taxaVenda.setScale(4, BigDecimal.ROUND_HALF_EVEN) +" "+
                cotacaoDe
    }
}
