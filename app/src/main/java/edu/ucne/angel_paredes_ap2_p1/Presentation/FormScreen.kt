package edu.ucne.angel_paredes_ap2_p1.Presentation

import androidx.compose.runtime.Composable
import edu.ucne.angel_paredes_ap2_p1.Presentation.Huacales.HuacalesScreen
import edu.ucne.angel_paredes_ap2_p1.Presentation.Huacales.HuacalesViewModel

@Composable
fun FormScreen(vm: HuacalesViewModel, onCerrar: () -> Unit) {
    HuacalesScreen(vm, onCerrar)
}
