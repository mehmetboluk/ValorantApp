package com.example.valorantapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.valorantapp.R
import com.example.valorantapp.databinding.RiMapsBinding
import com.example.valorantapp.domain.model.Map

class MapsAdapter(private val onMapsClickListener : (Map) -> Unit): RecyclerView.Adapter<MapsAdapter.MapsViewHolder>() {

    inner class MapsViewHolder(val binding: RiMapsBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Map>() {
        override fun areItemsTheSame(oldItem: Map, newItem: Map) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Map, newItem: Map) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this, diffCallBack)

    var mapList: List<Map>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapsViewHolder {
        val binding = DataBindingUtil.inflate<RiMapsBinding>(LayoutInflater.from(parent.context), R.layout.ri_maps, parent,false)
        return MapsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MapsViewHolder, position: Int) {
        val item = mapList[position]
        holder.binding.data = item
        holder.itemView.setOnClickListener {
            onMapsClickListener(item)
        }
    }

    override fun getItemCount(): Int = mapList.size
}