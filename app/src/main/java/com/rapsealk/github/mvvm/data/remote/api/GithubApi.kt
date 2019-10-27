package com.rapsealk.github.mvvm.data.remote.api

import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by rapsealk on 2019-10-26..
 */
interface GithubApi {
    /**
     * @see https://developer.github.com/v3/search/#search-users
     */
    @Headers("User-Agent: Github-Android-MVVM")
    @GET("search/users")
    fun searchUsers(
        @Query("q") q: String,
        @Query("sort") sort: String? = null,
        @Query("order") order: String = "desc"  // "asc"
    ): Single<GithubResponse.UsersResponse>
}