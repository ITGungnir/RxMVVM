package my.itgungnir.rxmvvm.core.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import my.itgungnir.rxmvvm.core.*
import kotlin.reflect.KProperty1

open class BaseViewModel<T : State>(initialState: T) : ViewModel() {

    private val state = MutableLiveData<T>().apply { value = initialState }

    fun setState(reducer: T.() -> T) {
        state.value = reducer(state.value!!)
    }

    fun getState(): T = state.value!!

    fun <A> pick(prop1: KProperty1<T, A>) = Transformations.map(state) {
        Tuple1(prop1.get(it))
    }.distinctUntilChanged()

    fun <A, B> pick(prop1: KProperty1<T, A>, prop2: KProperty1<T, B>) = Transformations.map(state) {
        Tuple2(prop1.get(it), prop2.get(it))
    }.distinctUntilChanged()

    fun <A, B, C> pick(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>,
        prop3: KProperty1<T, C>
    ) = Transformations.map(state) {
        Tuple3(prop1.get(it), prop2.get(it), prop3.get(it))
    }.distinctUntilChanged()

    fun <A, B, C, D> pick(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>,
        prop3: KProperty1<T, C>,
        prop4: KProperty1<T, D>
    ) = Transformations.map(state) {
        Tuple4(prop1.get(it), prop2.get(it), prop3.get(it), prop4.get(it))
    }.distinctUntilChanged()

    fun <A, B, C, D, E> pick(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>,
        prop3: KProperty1<T, C>,
        prop4: KProperty1<T, D>,
        prop5: KProperty1<T, E>
    ) = Transformations.map(state) {
        Tuple5(prop1.get(it), prop2.get(it), prop3.get(it), prop4.get(it), prop5.get(it))
    }.distinctUntilChanged()

    fun <A, B, C, D, E, F> pick(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>,
        prop3: KProperty1<T, C>,
        prop4: KProperty1<T, D>,
        prop5: KProperty1<T, E>,
        prop6: KProperty1<T, F>
    ) = Transformations.map(state) {
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
    ) = Transformations.map(state) {
        Tuple7(prop1.get(it), prop2.get(it), prop3.get(it), prop4.get(it), prop5.get(it), prop6.get(it), prop7.get(it))
    }.distinctUntilChanged()
}