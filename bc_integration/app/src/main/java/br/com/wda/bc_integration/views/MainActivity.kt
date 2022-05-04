package br.com.wda.bc_integration.views

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import androidx.core.widget.doAfterTextChanged
import br.com.wda.bc_integration.databinding.ActivityMain2Binding
import br.com.wda.bc_integration.databinding.ActivityMainBinding
import br.com.wda.bc_integration.enums.Coin
import br.com.wda.bc_integration.extensions.createDialog
import br.com.wda.bc_integration.extensions.createProgressDialog
import br.com.wda.bc_integration.extensions.hideSoftKeyboard
import br.com.wda.bc_integration.extensions.text
import br.com.wda.bc_integration.utils.MoneyTextWatcher
import br.com.wda.bc_integration.utils.Numeric
import br.com.wda.bc_integration.utils.getDate
import br.com.wda.bc_integration.viewModel.ViewModelCoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private val viewlModel by viewModel<ViewModelCoin>()
    private val dialog by lazy { createProgressDialog() }

    private var codCoinFrom by Delegates.notNull<Int>()
    private var codCoinTo by Delegates.notNull<Int>()

    private lateinit var binding: ActivityMain2Binding
    private val btnConverter by lazy {
        binding.btnConverter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
        setAdapters()
        bindListeners()
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
        codCoinFrom = Coin.AFN.cod

        binding.tvTo.setText(Coin.USD.country, false)
        codCoinTo = Coin.MGA.cod

        binding.tilFrom.setStartIconOnClickListener { it.hideSoftKeyboard() }
        binding.tvFrom.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->

                codCoinFrom = list[position].cod

            }

        //binding.tilTo.setEndIconOnClickListener { it.hideSoftKeyboard() }
        binding.tvTo.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                codCoinTo = list[position].cod
            }
    }


    private fun bindListeners() {
        binding.tilValue.editText?.doAfterTextChanged {

            btnConverter.isEnabled = it != null && it.toString().isNotEmpty()
        }

        btnConverter.setOnClickListener {
            it.hideSoftKeyboard()

            viewlModel.getCoinConverter(
                Numeric.Numeric.textForBigDecimal(binding.tilValue.text),
                1, codCoinFrom,
                codCoinTo, getDate("yyyy-MM-dd")
            )

        }
    }

    private fun actionStateObserver() {
        viewlModel.state.observe(this) {
            when (it) {
                ViewModelCoin.State.Loading -> dialog.show()
                is ViewModelCoin.State.Error -> {
                    dialog.dismiss()
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                }
                is ViewModelCoin.State.Sucess -> sucess(it)
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
        Log.e("COINVALUE", it.bcResponse.cotacaoCompra.toString())

    }
}
