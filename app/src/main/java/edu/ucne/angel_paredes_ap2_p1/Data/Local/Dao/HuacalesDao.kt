import androidx.room.*

@Dao
interface HuacalesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarHuacal(entrada: EntradasHuacales)

    @Update
    suspend fun actualizarHuacal(entrada: EntradasHuacales)

    @Delete
    suspend fun eliminarHuacal(entrada: EntradasHuacales)

    @Query("SELECT * FROM entradas_huacales")
    suspend fun obtenerTodos(): List<EntradasHuacales>

    @Query("""
        SELECT * FROM entradas_huacales 
        WHERE (:cliente IS NULL OR nombreCliente LIKE '%' || :cliente || '%')
        AND (:fecha IS NULL OR fecha = :fecha)
    """)
    suspend fun filtrar(cliente: String?, fecha: String?): List<EntradasHuacales>

    @Query("SELECT COUNT(*) FROM entradas_huacales")
    suspend fun conteo(): Int

    @Query("SELECT SUM(cantidad * precio) FROM entradas_huacales")
    suspend fun total(): Double
}
