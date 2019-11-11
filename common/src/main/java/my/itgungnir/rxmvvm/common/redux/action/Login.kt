package my.itgungnir.rxmvvm.common.redux.action

import my.itgungnir.rxmvvm.core.redux.Action

data class Login(val username: String, val password: String) : Action