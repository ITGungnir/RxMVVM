package my.itgungnir.rxmvvm.common.http

import my.itgungnir.rxmvvm.common.HTTP_BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Description:
 *
 * Created by ITGungnir on 2019-12-07
 */
class HttpClient private constructor() {

    companion object {
        val instance by lazy { HttpClient().getApi() }
    }

    fun getApi() =
        Retrofit.Builder()
            .baseUrl(HTTP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Api::class.java)
}