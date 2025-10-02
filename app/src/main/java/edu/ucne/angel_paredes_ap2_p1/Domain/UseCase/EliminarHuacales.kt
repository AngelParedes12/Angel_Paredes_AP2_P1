package edu.ucne.angel_paredes_ap2_p1.Domain.UseCase

import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales
import edu.ucne.angel_paredes_ap2_p1.Domain.Repository.HuacalesRepository

class EliminarHuacales(private val repo: HuacalesRepository) {
    suspend operator fun invoke(h: Huacales) = repo.eliminar(h)
}
