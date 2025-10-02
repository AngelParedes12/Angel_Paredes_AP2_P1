package edu.ucne.angel_paredes_ap2_p1.Presentation.Huacales

sealed interface HuacalesEvent{
    data class HuacalesChang(val huacales: Int) : HuacalesEvent
    data class NombreChang(val nombre: String) : HuacalesEvent
    data class CantidadChang(val cantidad: Int) : HuacalesEvent
    object Save : HuacalesEvent
    object delete : HuacalesEvent
    object new : HuacalesEvent
}