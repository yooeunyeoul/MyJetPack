package com.example.myjetpackwithandroidx.data

import androidx.room.Embedded
import androidx.room.Relation

class PlantAndGardenPlantings {

    // TODO 이게뭐지
    @Embedded
    lateinit var plant: Plant

    // TODO 이건 또 뭐지
    @Relation(parentColumn = "id", entityColumn = "plant_id")
    var gardenPlantings: List<GardenPlanting> = arrayListOf()
}