package my.itgungnir.rxmvvm.core.redux

import android.app.Application
import com.google.gson.Gson
import com.tencent.mmkv.MMKV

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class DoPersist

class ReduxPersistUtil<T : Any>(context: Application) {

    private val spKey = "rxmvvm_redux_key"

    private val mmkv: MMKV by lazy {
        MMKV.initialize(context)
        MMKV.mmkvWithID("InterProcessKV", MMKV.MULTI_PROCESS_MODE)
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
            mmkv.encode(spKey, Gson().toJson(this))
        }
    }

    fun currState(): String = mmkv.decodeString(spKey) ?: ""
}