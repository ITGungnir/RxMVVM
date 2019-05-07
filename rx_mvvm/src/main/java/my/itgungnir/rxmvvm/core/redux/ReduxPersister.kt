package my.itgungnir.rxmvvm.core.redux

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReduxPersister<T>(context: Application) {

    private val spKey = "rxmvvm_redux_key"

    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences("rx_mvvm_sp", Context.MODE_PRIVATE)
    }

    fun serialize(value: T) =
        sp.edit().putString(spKey, Gson().toJson(value)).apply()

    fun deserialize(): T? =
        Gson().fromJson(sp.getString(spKey, ""), object : TypeToken<T>() {}.type)

    fun currState(): String =
        sp.getString(spKey, "")!!
}