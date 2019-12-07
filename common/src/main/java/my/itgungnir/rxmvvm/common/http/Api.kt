package my.itgungnir.rxmvvm.common.http

import io.reactivex.Single
import my.itgungnir.rxmvvm.common.bean.ArticleListDTO
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Description:
 *
 * Created by ITGungnir on 2019-12-07
 */
interface Api {

    /**
     * 获取首页文章列表
     */
    @GET("/article/list/{pageNo}/json")
    fun getHomeArticleList(@Path("pageNo") pageNo: Int): Single<Response<ArticleListDTO>>
}