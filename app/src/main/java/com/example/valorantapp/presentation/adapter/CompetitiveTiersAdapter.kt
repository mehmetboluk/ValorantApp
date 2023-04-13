package com.example.valorantapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.valorantapp.BR
import com.example.valorantapp.R
import com.example.valorantapp.databinding.RiAgentsBinding
import com.example.valorantapp.databinding.RiCompetitiveTiersBinding
import com.example.valorantapp.domain.model.Agent
import com.example.valorantapp.domain.model.Tier
import kotlin.properties.Delegates

class CompetitiveTiersAdapter(): RecyclerView.Adapter<CompetitiveTiersAdapter.TiersViewHolder>() {

    inner class TiersViewHolder(val binding: RiCompetitiveTiersBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Tier>() {
        override fun areItemsTheSame(oldItem: Tier, newItem: Tier) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Tier, newItem: Tier) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this, diffCallBack)

    var tiersList: List<Tier>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiersViewHolder {
        val view = DataBindingUtil.inflate<RiCompetitiveTiersBinding>(LayoutInflater.from(parent.context),R.layout.ri_competitive_tiers,parent,false)
        return TiersViewHolder(view)
    }

    override fun onBindViewHolder(holder: TiersViewHolder, position: Int) {
        val item = tiersList[position]
        holder.binding.data = item
    }

    override fun getItemCount(): Int = tiersList.size

}