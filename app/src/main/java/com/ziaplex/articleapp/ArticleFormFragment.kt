package com.ziaplex.articleapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ArticleFormFragment : Fragment(R.layout.fragment_article_form) {

    private var articleId: Long = -1
    private var category: String = "Technology"
    private var date: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        articleId = args?.getLong(ARG_ID, -1) ?: -1
        category = args?.getString(ARG_CATEGORY) ?: "Technology"
        date = args?.getString(ARG_DATE) ?: SimpleDateFormat("MMM d, yyyy", Locale.getDefault()).format(Date())

        val titleEditText = view.findViewById<EditText>(R.id.titleEditText)
        val authorEditText = view.findViewById<EditText>(R.id.authorEditText)
        val bodyEditText = view.findViewById<EditText>(R.id.bodyEditText)

        val titleInputLayout = view.findViewById<TextInputLayout>(R.id.titleTextInputLayout)
        val authorInputLayout = view.findViewById<TextInputLayout>(R.id.authorTextInputLayout)
        val bodyInputLayout = view.findViewById<TextInputLayout>(R.id.bodyTextInputLayout)

        val saveButton = view.findViewById<MaterialButton>(R.id.saveButton)

        args?.getString(ARG_TITLE)?.let { titleEditText.setText(it) }
        args?.getString(ARG_AUTHOR)?.let { authorEditText.setText(it) }
        args?.getString(ARG_BODY)?.let { bodyEditText.setText(it) }

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val author = authorEditText.text.toString().trim()
            val body = bodyEditText.text.toString().trim()


            if (title.isEmpty()) {
                titleInputLayout.error = getString(R.string.error_title_required)
                return@setOnClickListener
            } else {
                titleInputLayout.error = null
            }

            if (author.isEmpty()) {
                authorInputLayout.error = getString(R.string.error_author_required)
                return@setOnClickListener
            } else {
                authorInputLayout.error = null
            }

            if (body.isEmpty()) {
                bodyInputLayout.error = getString(R.string.error_body_required)
                return@setOnClickListener
            } else {
                bodyInputLayout.error = null
            }

            val id = if (articleId != -1L) articleId else System.currentTimeMillis()
            val updatedArticle = Article(
                id = id,
                category = category,
                title = title,
                author = author,
                date = date,
                body = body
            )

            (activity as? MainActivity)?.saveArticle(updatedArticle)
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

        fun newInstance(article: Article? = null) = ArticleFormFragment().apply {
            article?.let {
                arguments = Bundle().apply {
                    putLong(ARG_ID, it.id)
                    putString(ARG_CATEGORY, it.category)
                    putString(ARG_TITLE, it.title)
                    putString(ARG_AUTHOR, it.author)
                    putString(ARG_DATE, it.date)
                    putString(ARG_BODY, it.body)
                }
            }
        }
    }
}