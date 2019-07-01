package com.example.myjetpackwithandroidx.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myjetpackwithandroidx.InjectorUtils.InjectorUtils
import com.example.myjetpackwithandroidx.adapters.PlantAdapter
import com.example.myjetpackwithandroidx.databinding.FragmentPlantListBinding
import com.example.myjetpackwithandroidx.viewmodels.PlantListViewModel
/**
 * [fragment_plant_list.xml]
 */
class PlantListFragment : Fragment() {

    private val viewModel: PlantListViewModel by viewModels {
        InjectorUtils.providePlantListViewModelFactory(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = PlantAdapter()
        binding.plantList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun subscribeUi(adapter: PlantAdapter) {
        viewModel.plants.observe(viewLifecycleOwner, Observer { plantList ->
            if (plantList != null) adapter.submitList(plantList)
        })
    }
}