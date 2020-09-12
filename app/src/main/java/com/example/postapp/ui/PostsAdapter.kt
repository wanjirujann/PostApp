package com.example.postapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postapp.R
import com.example.postapp.model.Post
import kotlinx.android.synthetic.main.item_posts.view.*

class PostsAdapter(
    var posts: List<Post>
) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_posts, parent, false)
    )

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        var post = posts[position]

        holder.itemView.userId.text = post.userId.toString()
        holder.itemView.ids.text = post.id.toString()
        holder.itemView.title.text = post.title
        holder.itemView.body.text = post.body
    }

    inner class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}