package com.gundermac.newsapss.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gundermac.newsapss.R
import com.gundermac.newsapss.core.data.source.remote.model.CategoryModel
import com.gundermac.newsapss.databinding.AdapterCategoryBinding

class CategoryAdapter(
    private val categories: List<CategoryModel>,
    private val listener: OnAdapterListener,
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private val items = arrayListOf<TextView>()

    class ViewHolder(val binding: AdapterCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnAdapterListener {
        fun onClick(category: CategoryModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.binding.category.text = category.name
        items.add(holder.binding.category)
        holder.itemView.setOnClickListener {
            listener.onClick(category)
            setColor(holder.binding.category)
        }
        setColor(items[0])
    }

    override fun getItemCount() = categories.size

    // to change color if category clicked
    private fun setColor(textView: TextView) {
        items.forEach { it.setBackgroundResource(R.color.blue_500) }
        textView.setBackgroundResource(android.R.color.darker_gray)
    }


}