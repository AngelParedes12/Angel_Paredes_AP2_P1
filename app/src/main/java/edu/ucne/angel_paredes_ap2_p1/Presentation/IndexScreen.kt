package edu.ucne.angel_paredes_ap2_p1.Presentation

import androidx.compose.runtime.Composable
import edu.ucne.angel_paredes_ap2_p1.Presentation.Huacales.HuacalesListScreen
import edu.ucne.angel_paredes_ap2_p1.Presentation.Huacales.HuacalesViewModel
import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales

@Composable
fun IndexScreen(
    vm: HuacalesViewModel,
    onNuevo: () -> Unit,
    onEditar: (Huacales) -> Unit
) {
    HuacalesListScreen(vm, onNuevo, onEditar)
}
