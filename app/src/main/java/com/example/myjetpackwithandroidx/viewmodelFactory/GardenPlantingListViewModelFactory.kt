package com.example.myjetpackwithandroidx.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myjetpackwithandroidx.repository.GardenPlantingRepository
import com.example.myjetpackwithandroidx.repository.PlantRepository
import com.example.myjetpackwithandroidx.viewmodels.GardenPlantingListViewModel
import com.example.myjetpackwithandroidx.viewmodels.PlantDetailViewModel
import com.example.myjetpackwithandroidx.viewmodels.PlantListViewModel

class GardenPlantingListViewModelFactory internal constructor(
   private val gardenPlantingRepository: GardenPlantingRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        GardenPlantingListViewModel(gardenPlantingRepository) as T

}