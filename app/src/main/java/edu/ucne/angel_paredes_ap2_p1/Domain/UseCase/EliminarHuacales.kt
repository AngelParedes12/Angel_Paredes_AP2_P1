package edu.ucne.angel_paredes_ap2_p1.Domain.UseCase

import edu.ucne.angel_paredes_ap2_p1.Data.Repository.HuacalesRepository
import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales

class EliminarHuacales (
    private val repository: HuacalesRepository
) {
    suspend operator fun invoke(Huacales: Huacales) {
        repository.delete(Huacales)
    }

}