package com.mythio.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mythio.weather.databinding.ItemRecentBinding
import com.mythio.weather.model.entity.Location
import kotlinx.android.synthetic.main.item_recent.view.*

class RecentLocationAdapter(
    private val onClickListener: OnClickListener
) : ListAdapter<Location, RecentLocationAdapter.LocationResponseViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.region == newItem.region
        }
    }

    class LocationResponseViewHolder(private var binding: ItemRecentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(location: Location) {
            binding.searchResults = location
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationResponseViewHolder {
        return LocationResponseViewHolder(ItemRecentBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: LocationResponseViewHolder, position: Int) {
        val location = getItem(position)
        holder.itemView.delete_btn.setOnClickListener {
            onClickListener.delete(location)
        }
        holder.itemView.setOnClickListener {
            onClickListener.onClick(location)
        }
        holder.bind(location)
    }

    interface OnClickListener {
        fun onClick(location: Location)
        fun delete(location: Location)
    }
}