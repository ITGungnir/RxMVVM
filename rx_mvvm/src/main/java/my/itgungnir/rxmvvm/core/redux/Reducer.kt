package my.itgungnir.rxmvvm.core.redux

interface Reducer {

    fun reduce(state: Any, action: Action): Any
}