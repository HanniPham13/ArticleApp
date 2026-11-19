package com.ziaplex.articleapp

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.emptyList

class ArticleListFragment : Fragment(R.layout.fragment_article_list) {

    private lateinit var recycler: RecyclerView
    private lateinit var emptyState: View
    private lateinit var errorState: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recyclerArticles)
        emptyState = view.findViewById(R.id.emptyState)
        errorState = view.findViewById(R.id.errorState)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        loadArticles()
    }

    override fun onResume(){
        super.onResume()
        loadArticles()
    }

    private fun loadArticles() {
        val articles = (activity as? MainActivity)?.getArticles() ?: emptyList()
        recycler.adapter = ArticleAdapter(articles) { article ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ArticleDetailFragment.newInstance(article))
                .addToBackStack(null)
                .commit()
        }
        recycler.isVisible = articles.isNotEmpty()
        emptyState.isVisible = articles.isEmpty()
        errorState.isVisible = false
    }
}