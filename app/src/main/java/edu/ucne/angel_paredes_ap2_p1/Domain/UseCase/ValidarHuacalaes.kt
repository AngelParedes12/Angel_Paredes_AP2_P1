package edu.ucne.angel_paredes_ap2_p1.Domain.UseCase

import edu.ucne.angel_paredes_ap2_p1.Data.Repository.HuacalesRepository
import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales

class ValidarHuacalaes(
private val repository: HuacalesRepository
){
    suspend operator fun invoke(huacales: Huacales): Result<Unit> {
        if (huacales.nombre.isBlank() || huacales.cantidad == 0) {
            return Result.failure(Exception("Nombre y cantidad son requeridos"))
        }

        val huacales = repository.getAll()
        if (huacales.any { it.nombre == huacales.nombre }) {
            return Result.failure(Exception("Nombre ya existe"))
        }
    }

}