package com.example.myjetpackwithandroidx.InjectorUtils

import android.content.Context
import com.example.myjetpackwithandroidx.AppDataBase.AppDatabase
import com.example.myjetpackwithandroidx.repository.GardenPlantingRepository
import com.example.myjetpackwithandroidx.repository.PlantRepository
import com.example.myjetpackwithandroidx.viewmodelFactory.GardenPlantingListViewModelFactory
import com.example.myjetpackwithandroidx.viewmodelFactory.PlantDetailViewModelFactory
import com.example.myjetpackwithandroidx.viewmodelFactory.PlantListViewModelFactory

object InjectorUtils {

    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(AppDatabase.getInstance(context).plantDao())
    }

    private fun getGardenPlantingRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(AppDatabase.getInstance(context).gardenPlantingDao())
    }

    fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory {
        val repository = getPlantRepository(context)
        return PlantListViewModelFactory(repository)
    }

    fun providePlantDetailViewModelFactory(context: Context, plantId: String): PlantDetailViewModelFactory {
        return PlantDetailViewModelFactory(getPlantRepository(context), getGardenPlantingRepository(context), plantId)
    }

    fun provideGardenPlantingListViewModelFactory(context: Context): GardenPlantingListViewModelFactory {
        return GardenPlantingListViewModelFactory(getGardenPlantingRepository(context))
    }
}