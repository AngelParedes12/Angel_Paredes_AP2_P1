package edu.ucne.angel_paredes_ap2_p1.Domain.UseCase

import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales
import edu.ucne.angel_paredes_ap2_p1.Domain.Repository.HuacalesRepository

class GuardarHuacales(
    private val repo: HuacalesRepository,
    private val validar: ValidarHuacales
) {
    suspend operator fun invoke(h: Huacales) {
        validar(h)?.let { throw IllegalArgumentException(it) }
        repo.guardar(h)
    }
}
