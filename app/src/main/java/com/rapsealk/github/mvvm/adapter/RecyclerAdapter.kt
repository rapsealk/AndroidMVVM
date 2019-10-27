package com.rapsealk.github.mvvm.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rapsealk.github.mvvm.R
import com.rapsealk.github.mvvm.model.GithubUser

/**
 * Created by rapsealk on 2019-10-24..
 */
class RecyclerAdapter(private val mItems: List<GithubUser>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun getItemCount(): Int = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_github_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val githubUser = mItems[position]
        Log.d("onBindViewHolder", "position: $position, githubUser: $githubUser")
        holder.email.text = githubUser.url
        holder.nickname.text = githubUser.login
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val email: TextView = view.findViewById(R.id.email)
        val nickname: TextView = view.findViewById(R.id.name)
    }
}