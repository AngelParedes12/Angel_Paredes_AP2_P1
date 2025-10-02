package edu.ucne.angel_paredes_ap2_p1.Data.Mappers

import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales
import edu.ucne.angel_paredes_ap2_p1.Data.Local.HuacalesEntity

fun HuacalesEntity.toDomain() = Huacales
    id = HuacalesId ?: 0,
        nombre = nombres,

