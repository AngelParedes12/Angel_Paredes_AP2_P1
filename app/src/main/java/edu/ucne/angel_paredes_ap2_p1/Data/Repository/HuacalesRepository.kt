package edu.ucne.angel_paredes_ap2_p1.Data.Repository

import edu.ucne.angel_paredes_ap2_p1.Data.Local.Dao.HuacalesDao
import edu.ucne.angel_paredes_ap2_p1.Data.Mappers.toDomain
import edu.ucne.angel_paredes_ap2_p1.Data.Mappers.toEntity
import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales
import edu.ucne.angel_paredes_ap2_p1.Domain.Repository.HuacalesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HuacalesRepository(
    private val dao: HuacalesDao
) : HuacalesRepository {

    override suspend fun guardar(h: Huacales) {
        val e = h.toEntity()
        if (e.idEntrada == 0) dao.insert(e) else dao.update(e)
    }

    override suspend fun eliminar(h: Huacales) = dao.delete(h.toEntity())

    override fun listar(): Flow<List<Huacales>> = dao.getAll().map { it.map { r -> r.toDomain() } }

    override fun filtrar(
        cliente: String?, desde: String?, hasta: String?,
        minCant: Int?, maxCant: Int?, minPrec: Double?, maxPrec: Double?
    ): Flow<List<Huacales>> =
        dao.getFiltered(cliente, desde, hasta, minCant, maxCant, minPrec, maxPrec)
            .map { it.map { r -> r.toDomain() } }

    override fun conteo(
        cliente: String?, desde: String?, hasta: String?,
        minCant: Int?, maxCant: Int?, minPrec: Double?, maxPrec: Double?
    ) = dao.countFiltered(cliente, desde, hasta, minCant, maxCant, minPrec, maxPrec)

    override fun total(
        cliente: String?, desde: String?, hasta: String?,
        minCant: Int?, maxCant: Int?, minPrec: Double?, maxPrec: Double?
    ) = dao.sumFiltered(cliente, desde, hasta, minCant, maxCant, minPrec, maxPrec)
}
