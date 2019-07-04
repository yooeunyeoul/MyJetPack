package com.example.myjetpackwithandroidx.viewmodels

import androidx.lifecycle.*
import com.example.myjetpackwithandroidx.data.PlantAndGardenPlantings
import com.example.myjetpackwithandroidx.repository.GardenPlantingRepository

class GardenPlantingListViewModel internal constructor(
    gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

    val gardenPlantingForPlant: LiveData<List<PlantAndGardenPlantings>> =
        gardenPlantingRepository.getPlantAndGardenPlantings().map { listPlantings ->
            listPlantings.filter {
                it.gardenPlantings.isNotEmpty()
            }
        }

    val hasPlants: LiveData<Boolean> = gardenPlantingRepository.getGardenPlants().map { listGardenPlanting ->
        listGardenPlanting.isNotEmpty()
    }


}


