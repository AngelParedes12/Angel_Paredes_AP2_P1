package edu.ucne.angel_paredes_ap2_p1.Presentation.Huacales

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FiltrosBar(
    filtros: Filtros,
    onBuscar: (Filtros) -> Unit
) {
    var cliente by remember { mutableStateOf(filtros.cliente ?: "") }
    var desde by remember { mutableStateOf(filtros.desde ?: "") }
    var hasta by remember { mutableStateOf(filtros.hasta ?: "") }
    var minC by remember { mutableStateOf(filtros.minCant?.toString() ?: "") }
    var maxC by remember { mutableStateOf(filtros.maxCant?.toString() ?: "") }
    var minP by remember { mutableStateOf(filtros.minPrecio?.toString() ?: "") }
    var maxP by remember { mutableStateOf(filtros.maxPrecio?.toString() ?: "") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(cliente, { cliente = it }, label = { Text("Cliente (opcional)") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(desde, { desde = it }, label = { Text("Desde (yyyy-MM-dd)") }, singleLine = true, modifier = Modifier.weight(1f))
            OutlinedTextField(hasta, { hasta = it }, label = { Text("Hasta (yyyy-MM-dd)") }, singleLine = true, modifier = Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(minC, { minC = it }, label = { Text("Min Cant.") }, singleLine = true, modifier = Modifier.weight(1f))
            OutlinedTextField(maxC, { maxC = it }, label = { Text("Max Cant.") }, singleLine = true, modifier = Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(minP, { minP = it }, label = { Text("Min Precio") }, singleLine = true, modifier = Modifier.weight(1f))
            OutlinedTextField(maxP, { maxP = it }, label = { Text("Max Precio") }, singleLine = true, modifier = Modifier.weight(1f))
        }
        Button(onClick = {
            onBuscar(
                Filtros(
                    cliente = cliente.ifBlank { null },
                    desde = desde.ifBlank { null },
                    hasta = hasta.ifBlank { null },
                    minCant = minC.toIntOrNull(),
                    maxCant = maxC.toIntOrNull(),
                    minPrecio = minP.toDoubleOrNull(),
                    maxPrecio = maxP.toDoubleOrNull()
                )
            )
        }) { Text("Aplicar filtros") }
    }
}
