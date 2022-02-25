package com.gile.beautysaloon.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gile.beautysaloon.R
import com.gile.beautysaloon.databinding.CategoryItemBinding
import com.gile.beautysaloon.databinding.ListItemCartEmployeeBinding
import com.gile.beautysaloon.databinding.ListItemCartServiceBinding
import com.gile.beautysaloon.databinding.ListItemEmployeesBinding
import com.gile.beautysaloon.model.ChallengeEmployeeData
import com.gile.beautysaloon.model.ChallengeServiceData

class CartServiceAdapter(private val list:List<ChallengeServiceData>) : RecyclerView.Adapter<CartServiceAdapter.ViewHolder>() {
    class ViewHolder(val item :ListItemCartServiceBinding):RecyclerView.ViewHolder(item.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemCartServiceBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item.apply {
            tvName.text = "${list[position].name}"
            tvPrice.text = "$${list[position].price}"
        }
    }

    override fun getItemCount(): Int {
            return list.size
    }
}