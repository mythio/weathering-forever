package com.mythio.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mythio.weather.databinding.ItemSearchBinding
import com.mythio.weather.model.entity.Location

class SearchLocationAdapter(
    private val onClickListener: OnClickListener
) : ListAdapter<Location, SearchLocationAdapter.LocationResponseViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.region == newItem.region
        }
    }

    class LocationResponseViewHolder(private var binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(location: Location) {
            binding.searchResults = location
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationResponseViewHolder {
        return LocationResponseViewHolder(ItemSearchBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: LocationResponseViewHolder, position: Int) {
        val location = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(location)
        }
        holder.bind(location)
    }

    interface OnClickListener {
        fun onClick(location: Location)
    }
}