package com.mythio.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mythio.weather.databinding.ItemSearchLocationBinding
import com.mythio.weather.network.response.LocationResponse

class SearchLocationAdapter :
    ListAdapter<LocationResponse, SearchLocationAdapter.LocationResponseViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<LocationResponse>() {
        override fun areItemsTheSame(oldItem: LocationResponse, newItem: LocationResponse): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: LocationResponse, newItem: LocationResponse): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.region == newItem.region
        }
    }

    class LocationResponseViewHolder(private var binding: ItemSearchLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(location: LocationResponse) {
            binding.searchResults = location
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationResponseViewHolder {
        return LocationResponseViewHolder(ItemSearchLocationBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: LocationResponseViewHolder, position: Int) {
        val location = getItem(position)
        holder.bind(location)
    }
}