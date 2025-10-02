package edu.ucne.angel_paredes_ap2_p1.Data.Local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EntradasHuacales")
data class EntradaHuacales(
    @PrimaryKey(autoGenerate = true) val idEntrada: Int = 0,
    val fecha: String,
    val nombreCliente: String,
    val cantidad: Int,
    val precio: Double
) {
    val totalLinea: Double get() = cantidad * precio
}
