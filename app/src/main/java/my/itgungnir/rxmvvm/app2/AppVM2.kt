package my.itgungnir.rxmvvm.app2

import android.arch.lifecycle.MutableLiveData
import my.itgungnir.rxmvvm.core.mvvm.BaseVM

class AppVM2 : BaseVM() {

    val randomNumberState = MutableLiveData<Int>()

    fun generateRandomNumber() {
        this.randomNumberState.value = (1..100).random()
    }
}