package com.ziaplex.articleapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {


        private val articles = mutableListOf<Article>()
    }
    private fun showDetail(article: Article) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, ArticleDetailFragment.newInstance(article))
            .addToBackStack(null)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar =
            findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.topToolbar)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ArticleListFragment())
                .commit()
        }

        findViewById<View>(R.id.fabAddArticle).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ArticleFormFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val showFragment = supportFragmentManager.backStackEntryCount > 0

            findViewById<View>(R.id.fabAddArticle).isVisible = !showFragment

            supportActionBar?.setDisplayHomeAsUpEnabled(showFragment)
            if (!showFragment) {
            }
        }
    }

    fun getArticles(): List<Article> = articles

    fun saveArticle(article: Article) {
        val index = articles.indexOfFirst { it.id == article.id }
        if (index != -1) {
            articles[index] = article
        } else {
            articles.add(0, article)
        }
    }

    fun deleteArticle(articleId: Long) {
        articles.removeAll { it.id == articleId }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            return true
        }
        return super.onSupportNavigateUp()
    }
}