package edu.ucne.angel_paredes_ap2_p1.Domain.UseCase

import edu.ucne.angel_paredes_ap2_p1.Data.Repository.HuacalesRepository
import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales


class GuardarHuacales(
private val repository: HuacalesRepository,
private val validator: HuacalesValidator
){
    suspend operator fun invoke(huacales: Huacales): Result<Boolean> {
     val validacion = validator.validar(huacales)
        if (validacion.isSuccess) return result.failure(validacion.exceptionOrNull()!!)

        val result = repository.save(huacales)
        return Result.success(result)
    }
}