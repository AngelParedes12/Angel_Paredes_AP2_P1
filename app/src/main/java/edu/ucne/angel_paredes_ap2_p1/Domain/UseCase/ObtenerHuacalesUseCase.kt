package edu.ucne.angel_paredes_ap2_p1.Domain.UseCase

import edu.ucne.angel_paredes_ap2_p1.Data.Repository.HuacalesRepository
import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales

class ObtenerHuacalesUseCase(
private val repository: HuacalesRepository
){
    suspend operator fun invoke(id: Int): Huacales? {
        return repository.find(id)
    }
}