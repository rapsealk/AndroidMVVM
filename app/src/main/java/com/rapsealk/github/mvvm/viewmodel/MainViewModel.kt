package com.rapsealk.github.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rapsealk.github.mvvm.model.GithubUser
import com.rapsealk.github.mvvm.repository.GithubUserRepository

/**
 * Created by rapsealk on 2019-10-24..
 */
class MainViewModel : ViewModel() {

    private val mRepository: GithubUserRepository by lazy {
        GithubUserRepository.getInstance()
    }
    private val mGithubUsers: MutableLiveData<List<GithubUser>> = mRepository.getGithubUsers()
    private val mIsUpdating: MutableLiveData<Boolean> = MutableLiveData()

    public val githubUsers: LiveData<List<GithubUser>>
        get() = mGithubUsers

    public val isUpdating: LiveData<Boolean>
        get() = mIsUpdating

    fun searchUsers(query: String) {
        mIsUpdating.value = true
        val disposable = mRepository.searchUsers(query)
            .subscribe { data ->
                Log.d(TAG, "Data: $data")
                //mGithubUsers.postValue(data.items)
                val users = githubUsers.value?.apply {
                    this as ArrayList
                    clear()
                    addAll(data.items)
                }
                mGithubUsers.value = users
                //mGithubUsers.value = data.items
                mIsUpdating.value = false
            }
    }

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }
}