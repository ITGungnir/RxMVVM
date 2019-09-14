package my.itgungnir.rxmvvm.core.redux

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.Locale.filter
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.javaField

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class DoPersist

class ReduxPersistUtil<T : Any>(context: Application) {

    val spKey = "rxmvvm_redux_key"

    val sp: SharedPreferences by lazy {
        context.getSharedPreferences("rx_mvvm_sp", Context.MODE_PRIVATE)
    }

    @Suppress("UNCHECKED_CAST")
    fun serialize(value: T) {
        value.javaClass.declaredFields.filter { prop ->
            prop.annotations.any { it.annotationClass == DoPersist::class }
        }.map {
            it.isAccessible = true
            it.name to it.get(value)
        }.fold(hashMapOf<String, Any?>()) { map, pair ->
            map.apply { put(pair.first, pair.second) }
        }.apply {
            sp.edit().putString(spKey, Gson().toJson(this)).apply()
        }
    }

    fun currState(): String = sp.getString(spKey, "") ?: ""
}