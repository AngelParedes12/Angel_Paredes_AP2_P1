package edu.ucne.angel_paredes_ap2_p1.Data.Mappers

import edu.ucne.angel_paredes_ap2_p1.Data.Local.Entities.EntradaHuacales
import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales

fun EntradaHuacales.toDomain() = Huacales(
    idEntrada = idEntrada,
    fecha = fecha,
    nombreCliente = nombreCliente,
    cantidad = cantidad,
    precio = precio
)

fun Huacales.toEntity() = EntradaHuacales(
    idEntrada = idEntrada,
    fecha = fecha,
    nombreCliente = nombreCliente,
    cantidad = cantidad,
    precio = precio
)
