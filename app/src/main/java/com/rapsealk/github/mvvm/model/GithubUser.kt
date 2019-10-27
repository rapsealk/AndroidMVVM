package com.rapsealk.github.mvvm.model

/**
 * Created by rapsealk on 2019-10-25..
 */
data class GithubUser(
    val login: String,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val received_events_url: String,
    val type: UserType,
    val site_admin: Boolean
) {
    enum class UserType(type: String) {
        User("User")
    }
}