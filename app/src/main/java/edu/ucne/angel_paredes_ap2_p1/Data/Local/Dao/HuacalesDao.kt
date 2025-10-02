package edu.ucne.angel_paredes_ap2_p1.Data.Local.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import edu.ucne.angel_paredes_ap2_p1.Data.Local.Entities.EntradaHuacales
import kotlinx.coroutines.flow.Flow

@Dao
interface HuacalesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(e: EntradaHuacales)

    @Update
    suspend fun update(e: EntradaHuacales)

    @Delete
    suspend fun delete(e: EntradaHuacales)

    @Query("SELECT * FROM EntradasHuacales ORDER BY fecha DESC, idEntrada DESC")
    fun getAll(): Flow<List<EntradaHuacales>>

    @Query("""
        SELECT * FROM EntradasHuacales
        WHERE (:cliente IS NULL OR nombreCliente LIKE '%'||:cliente||'%')
          AND (:desde IS NULL OR fecha >= :desde)
          AND (:hasta IS NULL OR fecha <= :hasta)
          AND (:minCant IS NULL OR cantidad >= :minCant)
          AND (:maxCant IS NULL OR cantidad <= :maxCant)
          AND (:minPrec IS NULL OR precio  >= :minPrec)
          AND (:maxPrec IS NULL OR precio  <= :maxPrec)
        ORDER BY fecha DESC, idEntrada DESC
    """)
    fun getFiltered(
        cliente: String?,
        desde: String?,
        hasta: String?,
        minCant: Int?,
        maxCant: Int?,
        minPrec: Double?,
        maxPrec: Double?
    ): Flow<List<EntradaHuacales>>

    @Query("""
        SELECT COUNT(*) FROM EntradasHuacales
        WHERE (:cliente IS NULL OR nombreCliente LIKE '%'||:cliente||'%')
          AND (:desde IS NULL OR fecha >= :desde)
          AND (:hasta IS NULL OR fecha <= :hasta)
          AND (:minCant IS NULL OR cantidad >= :minCant)
          AND (:maxCant IS NULL OR cantidad <= :maxCant)
          AND (:minPrec IS NULL OR precio  >= :minPrec)
          AND (:maxPrec IS NULL OR precio  <= :maxPrec)
    """)
    fun countFiltered(
        cliente: String?, desde: String?, hasta: String?,
        minCant: Int?, maxCant: Int?, minPrec: Double?, maxPrec: Double?
    ): Flow<Int>

    @Query("""
        SELECT COALESCE(SUM(cantidad*precio),0) FROM EntradasHuacales
        WHERE (:cliente IS NULL OR nombreCliente LIKE '%'||:cliente||'%')
          AND (:desde IS NULL OR fecha >= :desde)
          AND (:hasta IS NULL OR fecha <= :hasta)
          AND (:minCant IS NULL OR cantidad >= :minCant)
          AND (:maxCant IS NULL OR cantidad <= :maxCant)
          AND (:minPrec IS NULL OR precio  >= :minPrec)
          AND (:maxPrec IS NULL OR precio  <= :maxPrec)
    """)
    fun sumFiltered(
        cliente: String?, desde: String?, hasta: String?,
        minCant: Int?, maxCant: Int?, minPrec: Double?, maxPrec: Double?
    ): Flow<Double>
}
