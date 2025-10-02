package edu.ucne.angel_paredes_ap2_p1.Domain.Model

data class Huacales(
    val idEntrada: Int = 0,
    val fecha: String = "",
    val nombreCliente: String = "",
    val cantidad: Int = 0,
    val precio: Double = 0.0
) {
    val totalLinea: Double get() = cantidad * precio
}
