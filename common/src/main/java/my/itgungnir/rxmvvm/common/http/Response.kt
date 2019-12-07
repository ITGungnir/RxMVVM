package my.itgungnir.rxmvvm.common.http

/**
 * Description:
 *
 * Created by ITGungnir on 2019-12-07
 */
data class Response<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)