package com.example.myjetpackwithandroidx.data

import androidx.room.*
import java.util.*

@Entity(
    tableName = "garden_plantings",
    foreignKeys = [ForeignKey(entity = Plant::class, parentColumns = ["id"], childColumns = ["plant_id"])],
    indices = [Index("plant_id")]
    // @Entity 애노테이션의 속성으로 indices 를 사용한다면 엔티티를 인덱스 할 수 있다. 안써도 되지만 쿼리의 속도를 높이기위해 쓰는것같음.
)
data class GardenPlanting internal constructor(
    @ColumnInfo(name = "plant_id")
    val plantId: String,

    @ColumnInfo(name = "plant_date")
    val plantDate: Calendar = Calendar.getInstance(),

    @ColumnInfo(name = "last_watering_date")
    val lastWateringDate: Calendar = Calendar.getInstance()

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var gardenPlantingId: Long = 0
}