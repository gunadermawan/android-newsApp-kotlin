package com.gundermac.newsapss.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.gundermac.newsapss.R
import com.gundermac.newsapss.core.data.source.remote.model.ArticleModel
import com.gundermac.newsapss.core.data.source.remote.model.CategoryModel
import com.gundermac.newsapss.databinding.CustomToolbarBinding
import com.gundermac.newsapss.databinding.FragmentHomeBinding
import com.gundermac.newsapss.ui.adapter.CategoryAdapter
import com.gundermac.newsapss.ui.adapter.NewsAdapter
import com.gundermac.newsapss.ui.detail.DetailActivity
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

        bindingToolbar.container.inflateMenu(R.menu.menu_search)
        bindingToolbar.title = viewModel.title

        val menu = binding.toolbar.container.menu
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                firstLoad()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.query = it }
                return true
            }

        })

        binding.rvCategory.adapter = categoryAdapter
        viewModel.category.observe(viewLifecycleOwner) {
            NewsAdapter.VIEW_TYPE = if (it!!.isEmpty()) 1 else 2
            firstLoad()
        }
        binding.rvListNews.adapter = newsAdapter
        viewModel.news.observe(viewLifecycleOwner) {
            Timber.i(it.articles.toString())
            binding.imageviewTextAlert.visibility =
                if (it.articles.isEmpty()) View.VISIBLE else View.GONE
            binding.textAlert.visibility =
                if (it.articles.isEmpty()) View.VISIBLE else View.GONE
            if (viewModel.page == 1) newsAdapter.clear()
            newsAdapter.add(it.articles)
        }
        viewModel.message.observe(viewLifecycleOwner) {

        }
        binding.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0)!!.measuredHeight - v.measuredHeight) {
                if (viewModel.page <= viewModel.total && viewModel.loadingMore.value == false) viewModel.fetch()
            }
        }
    }

    private fun firstLoad() {
        binding.nestedScrollView.scrollTo(0, 0)
        viewModel.page = 1
        viewModel.total = 1
        viewModel.fetch()
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
                startActivity(
                    Intent(requireActivity(), DetailActivity::class.java)
                        .putExtra("intent_detail_news", article)
                )
            }
        })
    }
}