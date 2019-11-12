package my.itgungnir.rxmvvm.core.redux

import android.app.Application
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import my.itgungnir.rxmvvm.core.*
import kotlin.reflect.KProperty1

abstract class BaseRedux<T : Any>(
    private val context: Application,
    private val initialState: T,
    private val reducer: Reducer<T>
) {

    private var middlewareList = listOf<Middleware<T>>()

    private val spUtil: ReduxPersistUtil<T> by lazy {
        ReduxPersistUtil<T>(context)
    }

    private val currState = MutableLiveData<T>().apply { value = currState() }

    fun currState(): T = deserializeToCurrState(spUtil.currState()) ?: initialState

    fun dispatch(action: Action, middlewareList: List<Middleware<T>> = listOf()) {
        this.middlewareList = middlewareList
        when (val newState = reducer.reduce(currState(), apply(action, 0))) {
            null -> Unit
            currState() -> Unit
            else -> {
                when (Looper.getMainLooper() == Looper.myLooper()) {
                    true -> currState.value = newState
                    else -> currState.postValue(newState)
                }
                spUtil.serialize(newState)
            }
        }
    }

    private fun dispatch(action: Action) = dispatch(action, middlewareList)

    private fun apply(action: Action, index: Int): Action {
        if (index >= middlewareList.size) {
            return action
        }
        return apply(middlewareList[index].apply(currState(), action, ::dispatch), index + 1)
    }

    fun <A> pick(
        prop1: KProperty1<T, A>
    ) = currState.map {
        Tuple1(prop1.get(it))
    }.distinctUntilChanged()

    fun <A, B> pick(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>
    ) = currState.map {
        Tuple2(prop1.get(it), prop2.get(it))
    }.distinctUntilChanged()

    fun <A, B, C> pick(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>,
        prop3: KProperty1<T, C>
    ) = currState.map {
        Tuple3(prop1.get(it), prop2.get(it), prop3.get(it))
    }.distinctUntilChanged()

    fun <A, B, C, D> pick(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>,
        prop3: KProperty1<T, C>,
        prop4: KProperty1<T, D>
    ) = currState.map {
        Tuple4(prop1.get(it), prop2.get(it), prop3.get(it), prop4.get(it))
    }.distinctUntilChanged()

    fun <A, B, C, D, E> pick(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>,
        prop3: KProperty1<T, C>,
        prop4: KProperty1<T, D>,
        prop5: KProperty1<T, E>
    ) = currState.map {
        Tuple5(prop1.get(it), prop2.get(it), prop3.get(it), prop4.get(it), prop5.get(it))
    }.distinctUntilChanged()

    fun <A, B, C, D, E, F> pick(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>,
        prop3: KProperty1<T, C>,
        prop4: KProperty1<T, D>,
        prop5: KProperty1<T, E>,
        prop6: KProperty1<T, F>
    ) = currState.map {
        Tuple6(prop1.get(it), prop2.get(it), prop3.get(it), prop4.get(it), prop5.get(it), prop6.get(it))
    }.distinctUntilChanged()

    fun <A, B, C, D, E, F, G> pick(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>,
        prop3: KProperty1<T, C>,
        prop4: KProperty1<T, D>,
        prop5: KProperty1<T, E>,
        prop6: KProperty1<T, F>,
        prop7: KProperty1<T, G>
    ) = currState.map {
        Tuple7(prop1.get(it), prop2.get(it), prop3.get(it), prop4.get(it), prop5.get(it), prop6.get(it), prop7.get(it))
    }.distinctUntilChanged()

    /**
     * 将字符串解析成<T>对应的具体类型的对象
     */
    abstract fun deserializeToCurrState(json: String): T?
}