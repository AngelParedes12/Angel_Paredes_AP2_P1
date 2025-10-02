package edu.ucne.angel_paredes_ap2_p1.Data.Repository
import HuacalesDao
import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales

class HuacalesRepository @injet constructor(
    private val dao : HuacalesDao
) : HuacalesRepository {
    suspend fun save(huacales: Huacales): Boolean  {
        dao.save(huacales.toEntity())
        return true
    }
    override suspend fun find (id: Int) : Huacales? =
    dao.find(id)?.toDomain()

    override suspend fun delete(id: Int){
        dao.delete(Huacales.toEntity())
    }
    override suspend fun getAll() : List<Huacales> =
        dao.getAll().firstNull()?.map {it.toDomain() } ?: emptyList()

    override suspend fun getAllFlow(): Flow<list<Huacales>> =
        dao.getAllFlow().map { entities -> entities.map { HuacalesEntity ::toDomain) }
