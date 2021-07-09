package com.example.businesscard.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet


import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.businesscard.databinding.ItemBuinessCardBinding
import com.example.businesscard.domain.BusinessCard

class BusinessCardAdapter: ListAdapter<BusinessCard, BusinessCardAdapter.viewHolder>(DiffCallback()) {
    var listenershare:(View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
     val inflater =LayoutInflater.from(parent.context)
     val binding = ItemBuinessCardBinding.inflate(inflater, parent, false)

        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       holder.bind(getItem(position))
    }




    inner class viewHolder(
        private val binding: ItemBuinessCardBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item : BusinessCard){
            binding.txtName.text = item.nome
            binding.txtPhone.text = item.telefone
            binding.txtEmail.text = item.email
            binding.txtEntepriseName.text = item.empresa
            binding.mcvContent.setBackgroundColor(Color.parseColor(item.fundoPersonalizado))
            binding.mcvContent.setOnClickListener{
                listenershare(it)
            }




        }
    }


    class DiffCallback:DiffUtil.ItemCallback<BusinessCard>(){
        override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard) = oldItem == newItem

        override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) = oldItem.id == newItem.id


    }

}