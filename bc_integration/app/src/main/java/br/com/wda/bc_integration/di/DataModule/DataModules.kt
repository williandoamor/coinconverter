package br.com.wda.bc_integration.di.DataModule

import android.util.Log
import br.com.wda.bc_integration.repository.CoinRepository
import br.com.wda.bc_integration.webservice.CoinRemoteService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object DataModules {

    private const val HTTP_TAG = "OkHttp"

    fun load() {
        loadKoinModules(networkModules() + repositoryModule())
    }

    private fun networkModules(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(HTTP_TAG, ": $it")
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }
            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<CoinRemoteService>(get(), get())
            }

        }
    }

    private fun repositoryModule(): Module {
        return module {
            single {
                CoinRepository(get())
            }
        }
    }


    private inline fun <reified T> createService(
        client: OkHttpClient,
        factory: GsonConverterFactory
    ): T {
        return Retrofit.Builder()
            .baseUrl("https://www3.bcb.gov.br")
            .client(client)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(T::class.java)

    }
}