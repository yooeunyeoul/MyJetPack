package com.example.myjetpackwithandroidx.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR.viewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myjetpackwithandroidx.R
import com.example.myjetpackwithandroidx.data.Plant
import com.example.myjetpackwithandroidx.data.PlantAndGardenPlantings
import com.example.myjetpackwithandroidx.databinding.ListItemGardenPlantingBinding
import com.example.myjetpackwithandroidx.databinding.ListItemPlantBinding
import com.example.myjetpackwithandroidx.view.GardenFragmentDirections
import com.example.myjetpackwithandroidx.view.PlantListFragmentDirections
import com.example.myjetpackwithandroidx.view.PlantListFragment
import com.example.myjetpackwithandroidx.viewmodels.PlantAndGardenPlantingViewModel


class GardenPlantingAdapter :
    ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder>(GardenPlantDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_garden_planting
                , parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { plantAndGardenPlantings ->
            with(holder) {
                itemView.tag = plantAndGardenPlantings
                bind(View.OnClickListener {
                    var direction =
                        GardenFragmentDirections.actionGardenFragmentToPlantDetailFragment(plantId = plantAndGardenPlantings.plant.plantId)
                    it.findNavController().navigate(direction)
                }, plantAndGardenPlantings)
            }
        }

    }

    class ViewHolder internal constructor
        (private val binding: ListItemGardenPlantingBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, plantings: PlantAndGardenPlantings) {
            binding.apply {
                clickListener = listener
                viewModel = PlantAndGardenPlantingViewModel(plantings)
                executePendingBindings()
            }
        }
    }
}

class GardenPlantDiffCallback : DiffUtil.ItemCallback<PlantAndGardenPlantings>() {
    override fun areItemsTheSame(oldItem: PlantAndGardenPlantings, newItem: PlantAndGardenPlantings): Boolean {
        return oldItem.plant.plantId == newItem.plant.plantId
    }

    override fun areContentsTheSame(oldItem: PlantAndGardenPlantings, newItem: PlantAndGardenPlantings): Boolean {
        return oldItem.plant == newItem.plant
    }

}
