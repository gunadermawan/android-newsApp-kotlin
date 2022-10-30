package com.gundermac.newsapss.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gundermac.newsapss.core.data.source.remote.model.ArticleModel
import com.gundermac.newsapss.databinding.AdapterNewsBinding
import com.gundermac.newsapss.utils.DateUtil

class NewsAdapter(
    private val articles: ArrayList<ArticleModel>,
    private val listener: OnAdapterListener,
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(val binding: AdapterNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleModel) {
            binding.article = article
            binding.formatDate = DateUtil()
        }
    }

    interface OnAdapterListener {
        fun onClick(article: ArticleModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
        holder.binding.title.text = article.title
        holder.binding.publishedAt.text = article.publishedAt
        holder.itemView.setOnClickListener {
            listener.onClick(article)
        }
    }

    override fun getItemCount() = articles.size


    fun add(data: List<ArticleModel>) {
        articles.clear()
        articles.addAll(data)
        notifyDataSetChanged()
    }
}