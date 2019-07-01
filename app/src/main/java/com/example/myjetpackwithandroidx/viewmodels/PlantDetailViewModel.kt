package com.example.myjetpackwithandroidx.viewmodels

import androidx.lifecycle.*
import com.example.myjetpackwithandroidx.data.Plant
import com.example.myjetpackwithandroidx.repository.GardenPlantingRepository
import com.example.myjetpackwithandroidx.repository.PlantRepository

class PlantDetailViewModel internal constructor(
    plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
) : ViewModel() {

    val isPlanted: LiveData<Boolean>

    init {
        val gardenPlantingForPlant = gardenPlantingRepository.getGardenPlantingForPlant(plantId)
        isPlanted = gardenPlantingForPlant.map { GardenPlanting ->
            GardenPlanting != null
        }
    }

}