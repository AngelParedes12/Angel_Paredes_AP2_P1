package edu.ucne.angel_paredes_ap2_p1.Domain.UseCase

import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales

class ValidarHuacales {
    operator fun invoke(h: Huacales): String? {
        if (h.fecha.isBlank()) return "La fecha es obligatoria (yyyy-MM-dd)."
        if (h.nombreCliente.isBlank()) return "El nombre del cliente es obligatorio."
        if (h.cantidad < 0) return "La cantidad no puede ser negativa."
        if (h.precio < 0.0) return "El precio no puede ser negativo."
        return null
    }
}
