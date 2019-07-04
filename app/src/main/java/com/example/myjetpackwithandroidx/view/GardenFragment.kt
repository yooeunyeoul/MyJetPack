package com.example.myjetpackwithandroidx.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myjetpackwithandroidx.InjectorUtils.InjectorUtils
import com.example.myjetpackwithandroidx.R
import com.example.myjetpackwithandroidx.adapters.GardenPlantingAdapter
import com.example.myjetpackwithandroidx.adapters.PlantAdapter
import com.example.myjetpackwithandroidx.databinding.FragmentGardenBinding
import com.example.myjetpackwithandroidx.viewmodels.GardenPlantingListViewModel

/**
 * [fragment_garden.xml]
 */
class GardenFragment : Fragment() {

    val viewModel: GardenPlantingListViewModel by viewModels {
        InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding =
            DataBindingUtil.inflate<FragmentGardenBinding>(inflater, R.layout.fragment_garden, container, false)
        val adapter = GardenPlantingAdapter()
        binding.gardenList.adapter = adapter
        subscribeUi(adapter, binding)

        return binding.root
    }

    private fun subscribeUi(adapter: GardenPlantingAdapter, binding: FragmentGardenBinding?) {
        viewModel.gardenPlantingForPlant.observe(viewLifecycleOwner, Observer { plantings ->
            if (!plantings.isNullOrEmpty()) {
                adapter.submitList(plantings)
            }
        })
    }
}