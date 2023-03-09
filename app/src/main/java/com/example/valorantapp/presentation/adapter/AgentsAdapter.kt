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
import com.example.valorantapp.domain.model.Agent
import kotlin.properties.Delegates

class AgentsAdapter(private val itemClick: (agent: Agent) -> Unit ): RecyclerView.Adapter<AgentsAdapter.AgentsViewHolder>() {

    inner class AgentsViewHolder(val binding: RiAgentsBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Agent>() {
        override fun areItemsTheSame(oldItem: Agent, newItem: Agent) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Agent, newItem: Agent) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this, diffCallBack)

    var agentList: List<Agent>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentsViewHolder {
        val view = DataBindingUtil.inflate<RiAgentsBinding>(LayoutInflater.from(parent.context),R.layout.ri_agents,parent,false)
        return AgentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AgentsViewHolder, position: Int) {
        val item = agentList[position]
        holder.binding.data = item
        holder.itemView.setOnClickListener {
            itemClick.invoke(item)
        }
    }

    override fun getItemCount(): Int = agentList.size

}