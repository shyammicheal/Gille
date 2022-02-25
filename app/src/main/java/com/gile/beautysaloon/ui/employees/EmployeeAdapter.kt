package com.gile.beautysaloon.ui.employees

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gile.beautysaloon.R
import com.gile.beautysaloon.databinding.CategoryItemBinding
import com.gile.beautysaloon.databinding.ListItemEmployeesBinding
import com.gile.beautysaloon.model.ChallengeEmployeeData
import com.gile.beautysaloon.model.ChallengeServiceData

class EmployeeAdapter(private val list:List<ChallengeEmployeeData>, private val listener:IEmployeeListener) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {
    class ViewHolder(val item :ListItemEmployeesBinding):RecyclerView.ViewHolder(item.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemEmployeesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item.apply {
            tvName.text = "${list[position].name}"
            imgEmployee.clipToOutline=true
            Glide.with(holder.itemView.context).load(list[position].image)
                .placeholder(R.drawable.dummy_image).dontAnimate()
                .into(imgEmployee)
            itemView.setOnClickListener {
                list[position].selected = !list[position].selected
                listener.onEmployeeClicked(list[position])
                notifyDataSetChanged()
            }

            if (list[position].selected) {
                itemView.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_color_primary_border)
                imgTick.visibility = View.VISIBLE
            } else {
                imgTick.visibility = View.GONE
                holder.itemView.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_color_grey_border)
            }
        }

    }

    interface IEmployeeListener{
        fun onEmployeeClicked(challengeEmployeeData:ChallengeEmployeeData)
    }

    override fun getItemCount(): Int {
            return list.size
    }
}