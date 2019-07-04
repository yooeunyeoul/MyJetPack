package com.example.myjetpackwithandroidx.AppDataBase

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.myjetpackwithandroidx.DAO.GardenPlantingDao
import com.example.myjetpackwithandroidx.DAO.PlantDao
import com.example.myjetpackwithandroidx.data.GardenPlanting
import com.example.myjetpackwithandroidx.data.Plant
import com.example.myjetpackwithandroidx.utilities.DATABASE_NAME
import com.example.myjetpackwithandroidx.utilities.Converters


/**
 *
Note:Room은 메인쓰레드에서의 데이터베이스 접근을 허용하지 않는다.
허용하고 싶다면 데이터베이스를 생성하는 빌더에서 allowMainThreadQueries()를 호출해야한다.
허용하지 않는 이유는 데이터를 받아오는 작업이 길어질 경우 UI가 장시간 멈춰버릴수 있기 때문이다.
그래서 보통 비동기 쿼리를 하게 되는데 반환값으로는 LiveData 또는 RxJava의 Flowable이 될 수도 있다.
 */

@Database(entities = [GardenPlanting::class, Plant::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao
    abstract fun gardenPlantingDao(): GardenPlantingDao

    companion object {

        // https://tourspace.tistory.com/163
        // 동기화 제어
        // 싱글턴 인스턴스
        @Volatile
        private var Instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            // 멀티 스레드를 고려하여 객체가 여러개 생성되면 안되므로 synchronized 처리 해줌
            return Instance ?: synchronized(this) {
                Instance ?: buildDatabase(context).also {
                    Instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                }).build()
        }

    }
}