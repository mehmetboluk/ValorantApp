package com.example.valorantapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.valorantapp.R
import com.example.valorantapp.data.model.agents.Ability
import com.example.valorantapp.databinding.RiAbilityBinding

class AgentAbilityAdapter(private val ability:(Ability) -> Unit): RecyclerView.Adapter<AgentAbilityAdapter.AgentAbilityViewHolder>() {

    inner class AgentAbilityViewHolder(val binding: RiAbilityBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Ability>() {
        override fun areItemsTheSame(oldItem: Ability, newItem: Ability) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Ability, newItem: Ability) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this, diffCallBack)

    var abilityList: List<Ability>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentAbilityViewHolder {
        val view = DataBindingUtil.inflate<RiAbilityBinding>(LayoutInflater.from(parent.context),R.layout.ri_ability,parent,false)
        return AgentAbilityViewHolder(view)
    }

    override fun onBindViewHolder(holder: AgentAbilityViewHolder, position: Int) {
        val item = abilityList[position]
        holder.binding.data = item
        holder.itemView.setOnClickListener {
            ability.invoke(item)
        }
    }

    override fun getItemCount(): Int = abilityList.size
}