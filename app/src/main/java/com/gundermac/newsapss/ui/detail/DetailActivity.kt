package com.gundermac.newsapss.ui.detail

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.gundermac.newsapss.core.data.source.remote.model.ArticleModel
import com.gundermac.newsapss.databinding.ActivityDetailBinding
import com.gundermac.newsapss.databinding.CustomToolbarBinding

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var bindinToolbar: CustomToolbarBinding
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
}