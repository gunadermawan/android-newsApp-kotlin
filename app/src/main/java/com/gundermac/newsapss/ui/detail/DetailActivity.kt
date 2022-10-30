package com.gundermac.newsapss.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.gundermac.newsapss.R
import com.gundermac.newsapss.core.data.source.remote.model.ArticleModel
import com.gundermac.newsapss.databinding.ActivityDetailBinding
import com.gundermac.newsapss.databinding.CustomToolbarBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var bindinToolbar: CustomToolbarBinding
    private val viewModel: DetailViewModel by viewModel()
    private val detail by lazy {
        intent.getSerializableExtra("intent_detail_news") as ArticleModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindinToolbar = binding.toolbar
        setContentView(binding.root)

        setSupportActionBar(bindinToolbar.container)
        supportActionBar!!.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }
        detail.let {
            viewModel.find(it)
            val web = binding.webView
            web.loadUrl(it.url!!)
            web.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.pbTop.visibility = View.GONE
                }
            }
            val setting = binding.webView.settings
            setting.javaScriptCanOpenWindowsAutomatically = false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bookmark, menu)
        val menuBookmark = menu!!.findItem(R.id.action_bookmark)
        menuBookmark.setOnMenuItemClickListener {
            viewModel.bookmark(detail)
            true
        }
        viewModel.isBookmark.observe(this) {
            if (it == 0) menuBookmark.setIcon(R.drawable.ic_bookmark_false)
            else menuBookmark.setIcon(R.drawable.ic_bookmark_true)
        }
        return super.onCreateOptionsMenu(menu)
    }
}