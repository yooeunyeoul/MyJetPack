package com.example.myjetpackwithandroidx.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.myjetpackwithandroidx.data.Plant
import com.example.myjetpackwithandroidx.repository.PlantRepository

class PlantListViewModel internal constructor(plantRepository: PlantRepository) : ViewModel() {

    private val growZoneNumber = MutableLiveData<Int>().apply {
        value = NO_GROW_ZONE
    }

    val plants : LiveData<List<Plant>> = growZoneNumber.switchMap {
        if (it == NO_GROW_ZONE) {
            plantRepository.getPlants()
        } else {
            plantRepository.getPlantsWithGrowZoneNumber(it)
        }
    }

    companion object {
        private const val NO_GROW_ZONE = -1
    }

}