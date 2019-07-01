package com.example.myjetpackwithandroidx.AppDataBase

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.myjetpackwithandroidx.DAO.PlantDao
import com.example.myjetpackwithandroidx.data.GardenPlanting
import com.example.myjetpackwithandroidx.data.Plant
import com.example.myjetpackwithandroidx.utilities.DATABASE_NAME
import com.example.myjetpackwithandroidx.utilities.Converters

@Database(entities = [GardenPlanting::class, Plant::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao

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