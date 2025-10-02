package edu.ucne.angel_paredes_ap2_p1.Domain.Repository

import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales
import kotlinx.coroutines.flow.Flow

interface HuacalesRepository {
    suspend fun guardar(h: Huacales)
    suspend fun eliminar(h: Huacales)
    fun listar(): Flow<List<Huacales>>
    fun filtrar(
        cliente: String?, desde: String?, hasta: String?,
        minCant: Int?, maxCant: Int?, minPrec: Double?, maxPrec: Double?
    ): Flow<List<Huacales>>
    fun conteo(
        cliente: String?, desde: String?, hasta: String?,
        minCant: Int?, maxCant: Int?, minPrec: Double?, maxPrec: Double?
    ): Flow<Int>
    fun total(
        cliente: String?, desde: String?, hasta: String?,
        minCant: Int?, maxCant: Int?, minPrec: Double?, maxPrec: Double?
    ): Flow<Double>
}
