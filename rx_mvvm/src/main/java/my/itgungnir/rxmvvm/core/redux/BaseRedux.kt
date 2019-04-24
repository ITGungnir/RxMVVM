package my.itgungnir.rxmvvm.core.redux

import android.app.Application
import androidx.lifecycle.MutableLiveData
import my.itgungnir.rxmvvm.core.*
import kotlin.reflect.KProperty1

open class BaseRedux<T>(
    private val context: Application,
    private val initialState: T,
    private val reducer: Reducer<T>,
    private val middlewareList: List<Middleware<T>>
) {

    private val spKey = "rxmvvm_redux_key"

    private val spUtil: ReduxPersister<T> by lazy {
        ReduxPersister<T>(context)
    }

    private val currState = MutableLiveData<T>().apply {
        value = spUtil.deserialize(spKey) ?: initialState
    }

    fun dispatch(action: Action, shouldSave: Boolean = false) {
        when (val newState = reducer.reduce(currState.value!!, apply(action, 0))) {
            currState.value -> Unit
            else -> {
                currState.value = newState
                if (shouldSave) {
                    spUtil.serialize(spKey, newState)
                }
            }
        }
    }

    fun <A> pick(
        prop1: KProperty1<T, A>
    ) = currState.map { state: T -> Tuple1(prop1.get(state)) }
        .distinctUntilChanged()

    fun <A, B> pick(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>
    ) = currState.map { state -> Tuple2(prop1.get(state), prop2.get(state)) }
        .distinctUntilChanged()

    fun <A, B, C> pick(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>,
        prop3: KProperty1<T, C>
    ) = currState.map { state -> Tuple3(prop1.get(state), prop2.get(state), prop3.get(state)) }
        .distinctUntilChanged()

    fun <A, B, C, D> pick(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>,
        prop3: KProperty1<T, C>,
        prop4: KProperty1<T, D>
    ) = currState.map { state -> Tuple4(prop1.get(state), prop2.get(state), prop3.get(state), prop4.get(state)) }
        .distinctUntilChanged()

    private fun apply(action: Action, index: Int): Action {
        if (index >= middlewareList.size) {
            return action
        }
        return apply(middlewareList[index].apply(currState.value!!, action), index + 1)
    }
}