package my.itgungnir.rxmvvm.app5

import my.itgungnir.rxmvvm.common.http.HttpClient
import my.itgungnir.rxmvvm.common.http.handleResult
import my.itgungnir.rxmvvm.common.http.io2Main
import my.itgungnir.rxmvvm.core.mvvm.BaseViewModel

/**
 * Description:
 *
 * Created by ITGungnir on 2019-12-07
 */
class AppViewModel5 : BaseViewModel<AppState5>(initialState = AppState5()) {

    fun refreshData() =
        HttpClient.instance
            .getHomeArticleList(1)
            .handleResult()
            .io2Main()
            .map { it.datas.map { article -> article.title } }
            .doOnSubscribe {
                setState {
                    copy(
                        refreshing = true
                    )
                }
            }
            .doFinally {
                setState {
                    copy(
                        refreshing = false
                    )
                }
            }
            .subscribe({
                setState {
                    copy(
                        dataList = it,
                        error = null
                    )
                }
            }, {
                setState {
                    copy(
                        dataList = dataList,
                        error = it
                    )
                }
            })
}