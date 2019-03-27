package my.itgungnir.rxmvvm.app3

import android.arch.lifecycle.MutableLiveData
import my.itgungnir.rxmvvm.core.mvvm.BaseVM

class AppFragVM3 : BaseVM() {

    val randomNumberState = MutableLiveData<Int>()

    fun generateRandomNumber() {
        this.randomNumberState.value = (1..100).random()
    }
}