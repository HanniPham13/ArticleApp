package com.ziaplex.articleapp

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArticleListFragment : Fragment(R.layout.fragment_article_list) {

    private val viewModel: ArticleViewModel by viewModels()
    private lateinit var recycler: RecyclerView
    private lateinit var emptyState: View
    private lateinit var errorState: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recyclerArticles)
        emptyState = view.findViewById(R.id.emptyState)
        errorState = view.findViewById(R.id.errorState)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        // Observe ViewModel articles Flow
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.articles.collect { articles ->
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
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadArticles()
    }
}