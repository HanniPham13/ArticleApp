package com.ziaplex.articleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip

class ArticleAdapter(
    private val articles: List<Article>,
    private val onClick: (Article) -> Unit = {}
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chipCategory: Chip = view.findViewById(R.id.chipCategory)
        val textTitle: TextView = view.findViewById(R.id.textTitle)
        val textByline: TextView = view.findViewById(R.id.textByline)
        val textBodyPreview: TextView = view.findViewById(R.id.textBodyPreview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.chipCategory.text = article.category
        holder.textTitle.text = article.title
        holder.textByline.text = "${article.author} · ${article.date}"
        holder.textBodyPreview.text = article.body
        holder.itemView.setOnClickListener { onClick(article) }
    }

    override fun getItemCount(): Int = articles.size
}