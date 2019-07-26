package com.mythio.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mythio.weather.databinding.ItemSearchLocationBinding
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

    class LocationResponseViewHolder(private var binding: ItemSearchLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(location: Location) {
            binding.searchResults = location
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationResponseViewHolder {
        return LocationResponseViewHolder(ItemSearchLocationBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: LocationResponseViewHolder, position: Int) {
        val location = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(location)
        }
        holder.bind(location)
    }

    class OnClickListener(val clickListener: (url: Location) -> Unit) {
        fun onClick(url: Location) = clickListener(url)
    }
}