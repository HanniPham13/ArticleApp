package com.ziaplex.articleapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip

class ArticleDetailFragment : Fragment(R.layout.fragment_article_detail) {

    private lateinit var article: Article

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = requireArguments()

        article = Article(
            id = args.getLong(ARG_ID),
            category = args.getString(ARG_CATEGORY) ?: "",
            title = args.getString(ARG_TITLE) ?: "",
            author = args.getString(ARG_AUTHOR) ?: "",
            date = args.getString(ARG_DATE) ?: "",
            body = args.getString(ARG_BODY) ?: ""
        )

        view.findViewById<Chip>(R.id.chipCategory).text = article.category
        view.findViewById<TextView>(R.id.textTitle).text = article.title
        view.findViewById<TextView>(R.id.textByline).text = getString(
            R.string.article_byline,
            article.author,
            article.date
        )
        view.findViewById<TextView>(R.id.textBody).text = article.body

        view.findViewById<MaterialButton>(R.id.buttonEdit).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ArticleFormFragment.newInstance(article))
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<MaterialButton>(R.id.buttonDelete).setOnClickListener {
            (activity as? MainActivity)?.deleteArticle(article.id)
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        private const val ARG_ID = "id"
        private const val ARG_CATEGORY = "category"
        private const val ARG_TITLE = "title"
        private const val ARG_AUTHOR = "author"
        private const val ARG_DATE = "date"
        private const val ARG_BODY = "body"

        fun newInstance(article: Article) = ArticleDetailFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_ID, article.id)
                putString(ARG_CATEGORY, article.category)
                putString(ARG_TITLE, article.title)
                putString(ARG_AUTHOR, article.author)
                putString(ARG_DATE, article.date)
                putString(ARG_BODY, article.body)
            }
        }
    }
}