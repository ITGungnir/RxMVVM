package my.itgungnir.rxmvvm.core.redux

interface Reducer<T> {

    fun reduce(state: T, action: Action): T
}