package com.example.myjetpackwithandroidx.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myjetpackwithandroidx.data.GardenPlanting
import com.example.myjetpackwithandroidx.data.Plant
import com.example.myjetpackwithandroidx.data.PlantAndGardenPlantings

/**
 * @Query 는 DAO 에서 주요한 애노테이션이다.
 * 읽기/쓰기를 이 애노테이션으로 모두 가능하다.
 * 각 @Query 메소드는 컴파일 시간에 알맞은 쿼리 인지 입증하게 되고 문제가 있을시에는 컴파일 에러가 발생한다.
 *
 * Room은 또한 쿼리에 대한 반환값을 확인한다.
 * 반환되는 객체의 필드의 이름이 만약에 대응되는 컬럼이름이 질의응답에서 일치하지 않는다면 Room은 다음과 같이 두가지 방법중 하나로 알림을 줄것이다.

1. 몇몇의 필드명만 일치하는 경우에는 경고 발생
2. 일치하지 않는 필드명이 있을시 에러 발생
 */
@Dao
interface GardenPlantingDao {

    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlantings(): LiveData<List<GardenPlanting>>

    @Insert
    fun insertGardenPlanting(gardenPlanting: GardenPlanting): Long

    @Query("SELECT * FROM garden_plantings WHERE plant_id =:plantId ")
    fun getGardenPlantingForPlant(plantId: String): LiveData<GardenPlanting>

    @Query("SELECT * FROM plants")
    fun getPlantAndGardenPlantings(): LiveData<List<PlantAndGardenPlantings>>


}