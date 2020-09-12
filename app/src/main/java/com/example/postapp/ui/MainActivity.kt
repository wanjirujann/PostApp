package com.example.postapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postapp.R
import com.example.postapp.repository.PostsRepository
import com.example.postapp.viewmodel.PostsViewModel
import com.example.postapp.viewmodel.PostsViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var postsViewModel: PostsViewModel
    lateinit var postsViewModelFactory: PostsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postsViewModelFactory = PostsViewModelFactory(PostsRepository())
        postsViewModel =
            ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)
        postsViewModel.getPosts()
        postsViewModel.postsLiveData.observe(this, Observer { posts ->

            recycler_post.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                hasFixedSize()
                adapter =PostsAdapter(posts)
            }

            Toast.makeText(baseContext, "${posts.size} posts fetched", Toast.LENGTH_LONG).show()
        })

        postsViewModel.postsFailedLiveData.observe(this, Observer { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        })
    }
}