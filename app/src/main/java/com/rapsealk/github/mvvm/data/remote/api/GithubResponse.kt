package com.rapsealk.github.mvvm.data.remote.api

import com.rapsealk.github.mvvm.model.GithubUser

/**
 * Created by rapsealk on 2019-10-26..
 */
class GithubResponse {

    data class UsersResponse(
        val total_count: Int,
        val incomplete_results: Boolean,
        val items: List<GithubUser>
    )
}