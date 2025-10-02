package edu.ucne.angel_paredes_ap2_p1.Domain.UseCase

data class HuacalesUseCase(
    val guardarHuacales: GuardarHuacales,
    val obtenerHuacales: ObtenerHuacales,
    val eliminarHuacales: EliminarHuacales,
    val validarHuacales: HuacalesValidator

)
