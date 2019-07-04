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
import androidx.navigation.fragment.navArgs
import com.example.myjetpackwithandroidx.InjectorUtils.InjectorUtils
import com.example.myjetpackwithandroidx.R
import com.example.myjetpackwithandroidx.databinding.FragmentPlantDetailBinding
import com.example.myjetpackwithandroidx.viewmodels.PlantDetailViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * [fragment_plant_detail.xml]
 */
class PlantDetailFragment : Fragment() {

    private val args: PlantDetailFragmentArgs by navArgs()
    private lateinit var shareText: String

    private val plantDetailViewModel: PlantDetailViewModel by viewModels {
        InjectorUtils.providePlantDetailViewModelFactory(requireActivity(), args.plantId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentPlantDetailBinding>(
            inflater,
            R.layout.fragment_plant_detail,
            container,
            false
        ).apply {
            viewModel = plantDetailViewModel
            lifecycleOwner = this@PlantDetailFragment

            fab.setOnClickListener { view ->
                plantDetailViewModel.addPlantToGarden()
                Snackbar.make(view, "식물이 가든에 추가되었습니다.", Snackbar.LENGTH_LONG).show()

            }
        }

        plantDetailViewModel.plant.observe(this, Observer { plant ->
            shareText = plant?.name ?: ""
        })


        return binding.root
    }
}