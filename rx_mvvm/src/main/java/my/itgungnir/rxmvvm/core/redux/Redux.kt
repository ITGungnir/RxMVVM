package my.itgungnir.rxmvvm.core.redux

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class Redux private constructor(private val reducer: Reducer) {

    companion object {

        var instance: Redux? = null

        // Redux序列化(json)到SharedPreferences中的key
        private val spKey = "rxmvvm_redux_key"

        private var initialState: Any? = null

        private var middlewareList: List<Middleware> = listOf()

        private lateinit var spUtil: PersistUtil

        private lateinit var currState: MutableLiveData<Any>

        fun init(
            context: Application, // Application
            storeClazz: Class<*>, // 自定义的Store的类型
            initialState: Any, // 默认的Store数据
            reducer: Reducer, // 自定义的Reducer
            middlewareList: List<Middleware> // 中间件列表
        ) = instance ?: synchronized(this) {
            instance ?: Redux(reducer = reducer).also {
                this.instance = it
                this.initialState = initialState
                this.middlewareList = middlewareList
                this.spUtil = PersistUtil().apply { init(context) }
                this.currState = MutableLiveData<Any>().apply {
                    value = spUtil.deserialize(spKey, storeClazz) ?: initialState
                }
            }
        }
    }

    /**
     * 发送一个Action
     * shouldSave：是否需要序列化到本地
     */
    fun dispatch(action: Action, shouldSave: Boolean = false) {
        when (val newState = reducer.reduce(currState.value!!, apply(action, 0))) {
            currState.value -> { // ignore
            }
            else -> {
                currState.value = newState
                if (shouldSave) {
                    spUtil.serialize(spKey, newState)
                }
            }
        }
    }

    /**
     * 从Store中选择某一部分，方便局部监听
     */
    fun <T> pick(func: (Any) -> T): LiveData<T> =
        Transformations.map(currState) {
            func.invoke(it)
        }

    /**
     * 经过中间件列表处理Action
     */
    private fun apply(action: Action, index: Int): Action {
        if (index >= middlewareList.size) {
            return action
        }
        return apply(middlewareList[index].apply(currState.value!!, action), index + 1)
    }

    /**
     * 数据持久化工具（SharedPreferences）
     */
    private class PersistUtil {

        lateinit var sp: SharedPreferences

        /**
         * 序列化
         */
        fun serialize(key: String, value: Any) =
            sp.edit().putString(key, Gson().toJson(value)).apply()

        /**
         * 反序列化
         */
        fun deserialize(key: String, clazz: Class<*>): Any? =
            Gson().fromJson(sp.getString(key, ""), clazz)

        fun init(context: Application) {
            sp = context.getSharedPreferences("rx_mvvm_sp", Context.MODE_PRIVATE)
        }
    }
}