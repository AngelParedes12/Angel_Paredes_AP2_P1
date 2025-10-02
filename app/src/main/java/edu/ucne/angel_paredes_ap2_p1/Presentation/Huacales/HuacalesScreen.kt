package edu.ucne.angel_paredes_ap2_p1.Presentation.Huacales

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HuacalesScreen(vm: HuacalesViewModel, onClose: () -> Unit) {
    val ui = vm.ui.collectAsState().value
    val edit = ui.editando
    if (edit == null) {
        LaunchedEffect(Unit) { onClose() }
        Box(Modifier.fillMaxSize())
        return
    }

    var fecha by remember { mutableStateOf(edit.fecha) }
    var cliente by remember { mutableStateOf(edit.nombreCliente) }
    var cantidad by remember { mutableStateOf(edit.cantidad.toString()) }
    var precio by remember { mutableStateOf(edit.precio.toString()) }

    val cantVal = cantidad.toIntOrNull() ?: 0
    val precioVal = precio.toDoubleOrNull() ?: 0.0
    val total = cantVal * precioVal
    val money = remember { NumberFormat.getCurrencyInstance(Locale.US) }

    val valido = fecha.isNotBlank() && cliente.isNotBlank() &&
            (cantidad.toIntOrNull() ?: -1) >= 0 && (precio.toDoubleOrNull() ?: -1.0) >= 0.0

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (edit.idEntrada == 0) "Nueva Entrada" else "Editar Entrada",
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onClose() }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                    }
                }
            )
        }
    ) { p ->
        Column(
            Modifier.padding(p).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ui.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha (YYYY-MM-DD)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = cliente,
                onValueChange = { cliente = it },
                label = { Text("Nombre del Cliente") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = cantidad,
                onValueChange = { cantidad = it },
                label = { Text("Cantidad") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = money.format(total),
                onValueChange = {},
                label = { Text("Importe") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    enabled = valido,
                    onClick = {
                        vm.guardar(
                            Huacales(
                                idEntrada = edit.idEntrada,
                                fecha = fecha.trim(),
                                nombreCliente = cliente.trim(),
                                cantidad = cantVal,
                                precio = precioVal
                            )
                        )
                        onClose()
                    },
                    modifier = Modifier.weight(1f)
                ) { Text("Guardar") }

                if (edit.idEntrada != 0) {
                    OutlinedButton(
                        onClick = { vm.eliminar(edit); onClose() },
                        modifier = Modifier.weight(1f)
                    ) { Text("Eliminar") }
                }
            }

            OutlinedButton(
                onClick = { vm.cancelar(); onClose() },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Cancelar") }
        }
    }
}
