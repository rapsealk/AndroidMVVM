package com.rapsealk.github.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rapsealk.github.mvvm.adapter.RecyclerAdapter
import com.rapsealk.github.mvvm.model.GithubUser
import com.rapsealk.github.mvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Integer.max

class MainActivity : AppCompatActivity() {

    private val mViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private lateinit var mSearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_24px)
            // Title
            title = getString(R.string.app_name)
            setDisplayShowTitleEnabled(true)
        }

        // Model-View-ViewModel (MVVM)
        mViewModel.githubUsers.observe(this, Observer {
            recyclerView.adapter?.notifyDataSetChanged()
            Log.d(TAG, "githubUsers.size: ${it.size}")
            recyclerView.smoothScrollToPosition(Math.max(0, it.size-1))
        })

        mViewModel.isUpdating.observe(this, Observer {
            progressBar.visibility = if (it) ProgressBar.VISIBLE else ProgressBar.GONE
        })

        // Init RecyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = RecyclerAdapter(
                mViewModel.githubUsers.value ?: emptyList()
            )
        }
    }

    override fun onBackPressed() {
        /* if (mSearchView.isShown) {
            mSearchView.onActionViewCollapsed()
        } else */ super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, toolbar.menu)
        // SearchView on ActionBar
        mSearchView = (toolbar.menu.findItem(R.id.menu_search).actionView as SearchView).apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean = false

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.apply {
                        mViewModel.searchUsers(this)
                        mSearchView.clearFocus()
                    } ?: Toast.makeText(this@MainActivity, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    return false
                }
            })
            setOnSearchClickListener {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
            setOnCloseListener {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                return@setOnCloseListener false
            }
            queryHint = getString(R.string.query_hint)
            isIconified = true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {

            }
            R.id.menu_search -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
