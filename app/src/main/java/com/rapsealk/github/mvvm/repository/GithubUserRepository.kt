package com.rapsealk.github.mvvm.repository

import androidx.lifecycle.MutableLiveData
import com.rapsealk.github.mvvm.data.remote.api.GithubClient
import com.rapsealk.github.mvvm.data.remote.api.GithubResponse
import com.rapsealk.github.mvvm.model.GithubUser
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by rapsealk on 2019-10-25..
 */
class GithubUserRepository {

    companion object {
        private val TAG = GithubUserRepository::class.java.simpleName

        private val repository by lazy { GithubUserRepository() }

        fun getInstance() = repository
    }

    private val mGithubService by lazy { GithubClient.getInstance() }

    private val data = arrayListOf(GithubUser("login", 1, "node_id",
        "avatar_url", "gravatar_url", "url", "html_url",
        "followers_url", "subscriptions_url", "organizations_url",
        "repos_url", "received_events_url", GithubUser.UserType.User, false))//ArrayList<GithubUser>()

    fun getGithubUsers() = MutableLiveData<List<GithubUser>>().apply { value = data }

    fun searchUsers(query: String): Single<GithubResponse.UsersResponse> {
        return mGithubService.searchUsers(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                /*
            .subscribe { githubUsers ->
                data.apply {
                    clear()
                    addAll(githubUsers)
                }
            }
            */
    }
}