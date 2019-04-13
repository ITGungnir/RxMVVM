package my.itgungnir.rxmvvm.core.redux

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReduxPersister<T>(context: Application) {

    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences("rx_mvvm_sp", Context.MODE_PRIVATE)
    }

    fun serialize(key: String, value: T) =
        sp.edit().putString(key, Gson().toJson(value)).apply()

    fun deserialize(key: String): T? =
        Gson().fromJson(sp.getString(key, ""), object : TypeToken<T>() {}.type)
}