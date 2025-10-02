package edu.ucne.angel_paredes_ap2_p1.Data.Local.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.ucne.angel_paredes_ap2_p1.Data.Local.Dao.HuacalesDao
import edu.ucne.angel_paredes_ap2_p1.Data.Local.Entities.EntradaHuacales

@Database(entities = [EntradaHuacales::class], version = 1, exportSchema = false)
abstract class HuacalesDb : RoomDatabase() {
    abstract fun huacalesDao(): HuacalesDao

    companion object {
        @Volatile private var INSTANCE: HuacalesDb? = null
        fun getInstance(ctx: Context): HuacalesDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    ctx.applicationContext,
                    HuacalesDb::class.java,
                    "huacales.db"
                ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
            }
    }
}
