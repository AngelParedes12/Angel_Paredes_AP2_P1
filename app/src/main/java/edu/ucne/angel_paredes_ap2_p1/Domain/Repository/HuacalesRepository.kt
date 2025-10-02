package edu.ucne.angel_paredes_ap2_p1.Domain.Repository

import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales
import kotlinx.coroutines.flow.Flow

interface HuacalesRepository{
    suspend fun save( huacales: Huacales): Boolean)
    suspend fun find(id: Int): Huacales? }
    suspend fun delete(id: Int)
    suspend fun getAll(): List<Huacales>
    suspend fun getAllFlow(): Flow<List<Huacales>>
}
