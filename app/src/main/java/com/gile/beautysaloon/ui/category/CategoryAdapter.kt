package com.gile.beautysaloon.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gile.beautysaloon.R
import com.gile.beautysaloon.databinding.CategoryItemBinding
import com.gile.beautysaloon.model.ChallengeServiceData

class CategoryAdapter(private val list:List<ChallengeServiceData>, private val listener:ICategoryListener) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(val item :CategoryItemBinding):RecyclerView.ViewHolder(item.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item.apply {
            tvName.text = "${list[position].name}"
            tvPrice.text = "$${list[position].price}"
            Glide.with(holder.itemView.context).load(list[position].image)
                .placeholder(R.drawable.dummy_image).dontAnimate()
                .into(categoryImage)
            llItem.setOnClickListener {
                listener.onCategoryClicked(list[position])
            }
        }

    }

    override fun getItemCount(): Int {
            return list.size
    }

    interface ICategoryListener{
        fun onCategoryClicked(challengeServiceData:ChallengeServiceData)
    }
}