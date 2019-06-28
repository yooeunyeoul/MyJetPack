package com.example.myjetpackwithandroidx.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myjetpackwithandroidx.data.Plant
import com.example.myjetpackwithandroidx.databinding.ListItemPlantBinding
import com.example.myjetpackwithandroidx.view.PlantListFragmentDirections

class PlantAdapter : ListAdapter<Plant, PlantAdapter.ViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemPlantBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = getItem(position)
        holder.apply {
            bind(View.OnClickListener {
                val direction =
                    PlantListFragmentDirections.actionPlantListFragmentToPlantDetailFragment(plantId = plant.plantId)
                it.findNavController().navigate(direction)
            }, plant)

        }
    }

    class ViewHolder internal constructor
        (private val binding: ListItemPlantBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Plant) {
            binding.apply {
                clickListener = listener
                plant = item
//                executePendingBindings()
            }
        }


    }
}

class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }

}
