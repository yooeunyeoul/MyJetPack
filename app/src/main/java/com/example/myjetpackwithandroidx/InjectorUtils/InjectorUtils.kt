package com.example.myjetpackwithandroidx.InjectorUtils

import android.content.Context
import com.example.myjetpackwithandroidx.AppDataBase.AppDatabase
import com.example.myjetpackwithandroidx.DAO.PlantDao
import com.example.myjetpackwithandroidx.repository.PlantRepository
import com.example.myjetpackwithandroidx.viewmodelFactory.PlantListViewModelFactory

object InjectorUtils {

    private fun getPlantRepository(context: Context) : PlantRepository {
        return  PlantRepository.getInstance(AppDatabase.getInstance(context).plantDao())

    }

    fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory {
        val repository = getPlantRepository(context)
        return PlantListViewModelFactory(repository)
    }
}