import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EntradasHuacales::class], version = 1)
abstract class HuacalesDatabase : RoomDatabase() {
    abstract fun huacalesDao(): HuacalesDao
}
