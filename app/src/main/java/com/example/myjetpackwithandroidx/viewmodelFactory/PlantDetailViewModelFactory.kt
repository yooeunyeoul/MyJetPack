package com.example.myjetpackwithandroidx.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myjetpackwithandroidx.repository.GardenPlantingRepository
import com.example.myjetpackwithandroidx.repository.PlantRepository
import com.example.myjetpackwithandroidx.viewmodels.PlantDetailViewModel
import com.example.myjetpackwithandroidx.viewmodels.PlantListViewModel

class PlantDetailViewModelFactory internal constructor(
    private val plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PlantDetailViewModel(plantRepository, gardenPlantingRepository, plantId) as T

}