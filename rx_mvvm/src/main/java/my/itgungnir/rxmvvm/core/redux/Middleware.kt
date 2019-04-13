package my.itgungnir.rxmvvm.core.redux

interface Middleware<T> {

    fun apply(state: T, action: Action): Action
}