package edu.ucne.angel_paredes_ap2_p1.Domain.UseCase

data class HuacalesUseCase(
    val guardar: GuardarHuacales,
    val eliminar: EliminarHuacales,
    val obtener: ObtenerHuacalesUseCase,
    val validar: ValidarHuacales
)
