package com.gile.beautysaloon.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gile.beautysaloon.R
import com.gile.beautysaloon.databinding.CartItemBinding
import com.gile.beautysaloon.databinding.CategoryItemBinding
import com.gile.beautysaloon.model.CartModel
import com.gile.beautysaloon.model.ChallengeServiceData

class CartAdapter(private val list:MutableList<CartModel>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(val item :CartItemBinding):RecyclerView.ViewHolder(item.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item.apply {
            rvEmployees.apply {
                adapter = CartEmployeeAdapter(list[position].selectedEmployees)
            }
            rvService.apply {
                adapter = CartServiceAdapter(list[position].challengeServiceData)
            }
        }

    }

    override fun getItemCount(): Int {
            return list.size
    }

}