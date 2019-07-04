package com.example.myjetpackwithandroidx.repository

import androidx.lifecycle.LiveData
import com.example.myjetpackwithandroidx.DAO.GardenPlantingDao
import com.example.myjetpackwithandroidx.DAO.PlantDao
import com.example.myjetpackwithandroidx.data.GardenPlanting
import com.example.myjetpackwithandroidx.data.Plant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class GardenPlantingRepository private constructor(private val gardenPlantingDao: GardenPlantingDao) {

    suspend fun createGardenPlanting(plantId: String) {
        withContext(IO) {
            val gardenPlanting = GardenPlanting(plantId)
            gardenPlantingDao.insertGardenPlanting(gardenPlanting)
        }
    }

    private fun withContext(context: CoroutineDispatcher) {

    }

    fun getGardenPlantingForPlant(plantId: String) =
        gardenPlantingDao.getGardenPlantingForPlant(plantId)

    fun getPlantAndGardenPlantings() = gardenPlantingDao.getPlantAndGardenPlantings()

    fun getGardenPlants() = gardenPlantingDao.getGardenPlantings()


    companion object {

        //TODO Volatile 이 뭐지??
        @Volatile
        private var instance: GardenPlantingRepository? = null

        fun getInstance(gardenPlantingDao: GardenPlantingDao) =
            instance ?: synchronized(this) {
                //TODO :: also 에서 apply 로 바꿔봄
                GardenPlantingRepository(gardenPlantingDao).apply {
                    instance = this
                }
            }

    }


}