package com.mythio.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mythio.weather.databinding.ItemRecentBinding
import com.mythio.weather.model.entity.Location
import kotlinx.android.synthetic.main.item_recent.view.*

class RecentLocationAdapter(
    private val onClickListener: OnClickListener
) : ListAdapter<Location, DataBindingViewHolder<Location>>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.region == newItem.region
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<Location> {
        return DataBindingViewHolder(ItemRecentBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<Location>, position: Int) {
        val location = getItem(position)
        holder.itemView.delete_btn.setOnClickListener {
            onClickListener.onClickDelete(location)
        }
        holder.itemView.setOnClickListener {
            onClickListener.onClick(location)
        }
        holder.bind(location)
    }

    interface OnClickListener {
        fun onClick(location: Location)
        fun onClickDelete(location: Location)
    }
}