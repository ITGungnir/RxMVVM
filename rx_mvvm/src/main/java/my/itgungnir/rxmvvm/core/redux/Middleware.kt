package my.itgungnir.rxmvvm.core.redux

interface Middleware {

    fun apply(state: Any, action: Action): Action
}