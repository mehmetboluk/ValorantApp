package com.example.valorantapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.valorantapp.R
import com.example.valorantapp.data.model.weapons.Skin
import com.example.valorantapp.databinding.RiSkinsBinding

class WeaponSkinAdapter(private val skin:(Skin) -> Unit): RecyclerView.Adapter<WeaponSkinAdapter.WeaponSkinViewHolder>() {

    inner class WeaponSkinViewHolder(val binding: RiSkinsBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Skin>() {
        override fun areItemsTheSame(oldItem: Skin, newItem: Skin) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Skin, newItem: Skin) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this, diffCallBack)

    var skinList: List<Skin>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponSkinViewHolder {
        val view = DataBindingUtil.inflate<RiSkinsBinding>(LayoutInflater.from(parent.context),R.layout.ri_skins,parent,false)
        return WeaponSkinViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeaponSkinViewHolder, position: Int) {
        val item = skinList[position]
        holder.binding.data = item
        holder.itemView.setOnClickListener {
            skin.invoke(item)
        }
    }

    override fun getItemCount(): Int = skinList.size
}