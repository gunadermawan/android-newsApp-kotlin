package com.gundermac.newsapss.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gundermac.newsapss.core.data.source.remote.model.ArticleModel
import com.gundermac.newsapss.core.data.source.remote.model.CategoryModel
import com.gundermac.newsapss.databinding.CustomToolbarBinding
import com.gundermac.newsapss.databinding.FragmentHomeBinding
import com.gundermac.newsapss.ui.adapter.CategoryAdapter
import com.gundermac.newsapss.ui.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: HomeViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        data binding
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        bindingToolbar.title = viewModel.title

        binding.rvCategory.adapter = categoryAdapter
        viewModel.category.observe(viewLifecycleOwner) {
            Timber.i(it)
            viewModel.fetch()
        }
        binding.rvListNews.adapter = newsAdapter
        viewModel.news.observe(viewLifecycleOwner) {
            Timber.i(it.articles.toString())
            binding.imageviewTextAlert.visibility =
                if (it.articles.isEmpty()) View.VISIBLE else View.GONE
            binding.textAlert.visibility =
                if (it.articles.isEmpty()) View.VISIBLE else View.GONE
            newsAdapter.add(it.articles)
        }
        viewModel.message.observe(viewLifecycleOwner) {

        }
    }

    private val categoryAdapter by lazy {
        CategoryAdapter(viewModel.categories, object : CategoryAdapter.OnAdapterListener {
            override fun onClick(category: CategoryModel) {
                viewModel.category.postValue(category.id)
            }
        })
    }
    private val newsAdapter by lazy {
        NewsAdapter(arrayListOf(), object : NewsAdapter.OnAdapterListener {
            override fun onClick(article: ArticleModel) {
            }
        })
    }
}