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
import com.gile.beautysaloon.databinding.ListItemEmployeesBinding
import com.gile.beautysaloon.model.ChallengeEmployeeData
import com.gile.beautysaloon.model.ChallengeServiceData

class CartEmployeeAdapter(private val list:List<ChallengeEmployeeData>) : RecyclerView.Adapter<CartEmployeeAdapter.ViewHolder>() {
    class ViewHolder(val item :ListItemCartEmployeeBinding):RecyclerView.ViewHolder(item.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemCartEmployeeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item.apply {
            imgEmployee.clipToOutline=true
            Glide.with(holder.itemView.context).load(list[position].image)
                .placeholder(R.drawable.dummy_image).dontAnimate()
                .into(imgEmployee)
        }
    }

    override fun getItemCount(): Int {
            return list.size
    }
}