package com.example.myjetpackwithandroidx.repository

import com.example.myjetpackwithandroidx.DAO.PlantDao

class PlantRepository private constructor(private val plantDao: PlantDao) {

    fun getPlants() = plantDao.getPlants()



}