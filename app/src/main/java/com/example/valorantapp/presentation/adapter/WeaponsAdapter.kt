package com.example.valorantapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.valorantapp.R
import com.example.valorantapp.databinding.RiMapsBinding
import com.example.valorantapp.databinding.RiWeaponsBinding
import com.example.valorantapp.domain.model.Map
import com.example.valorantapp.domain.model.Weapon

class WeaponsAdapter(private val onWeaponsClickListener : (Weapon) -> Unit): RecyclerView.Adapter<WeaponsAdapter.WeaponsViewHolder>() {

    inner class WeaponsViewHolder(val binding: RiWeaponsBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Weapon>() {
        override fun areItemsTheSame(oldItem: Weapon, newItem: Weapon) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Weapon, newItem: Weapon) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this, diffCallBack)

    var weaponList: List<Weapon>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponsViewHolder {
        val binding = DataBindingUtil.inflate<RiWeaponsBinding>(LayoutInflater.from(parent.context), R.layout.ri_weapons, parent,false)
        return WeaponsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeaponsViewHolder, position: Int) {
        val item = weaponList[position]
        holder.binding.data = item
        holder.itemView.setOnClickListener {
            onWeaponsClickListener(item)
        }
    }

    override fun getItemCount(): Int = weaponList.size
}