package edu.ucne.angel_paredes_ap2_p1.Domain.UseCase

import edu.ucne.angel_paredes_ap2_p1.Domain.Repository.HuacalesRepository

class ObtenerHuacalesUseCase(private val repo: HuacalesRepository) {
    fun listar() = repo.listar()
    fun filtrar(
        cliente: String?, desde: String?, hasta: String?,
        minCant: Int?, maxCant: Int?, minPrec: Double?, maxPrec: Double?
    ) = repo.filtrar(cliente, desde, hasta, minCant, maxCant, minPrec, maxPrec)

    fun conteo(
        cliente: String?, desde: String?, hasta: String?,
        minCant: Int?, maxCant: Int?, minPrec: Double?, maxPrec: Double?
    ) = repo.conteo(cliente, desde, hasta, minCant, maxCant, minPrec, maxPrec)

    fun total(
        cliente: String?, desde: String?, hasta: String?,
        minCant: Int?, maxCant: Int?, minPrec: Double?, maxPrec: Double?
    ) = repo.total(cliente, desde, hasta, minCant, maxCant, minPrec, maxPrec)
}
