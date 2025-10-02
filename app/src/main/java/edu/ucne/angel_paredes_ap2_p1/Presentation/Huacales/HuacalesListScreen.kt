package edu.ucne.angel_paredes_ap2_p1.Presentation.Huacales

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HuacalesListScreen(
    vm: HuacalesViewModel,
    onNuevo: () -> Unit,
    onEditar: (Huacales) -> Unit
) {
    val ui = vm.ui.collectAsState().value
    val money = remember { NumberFormat.getCurrencyInstance(Locale.US) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Entradas de Huacales") },
                navigationIcon = {
                    IconButton(onClick = {}) { Icon(Icons.Default.Menu, contentDescription = "menu") }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { vm.nuevo(); onNuevo() }) {
                Icon(Icons.Default.Add, contentDescription = "Nuevo")
            }
        },
        bottomBar = {
            SummaryBar(
                conteo = ui.conteo,
                total = money.format(ui.total)
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(12.dp)) {

            FiltersCard(
                filtros = ui.filtros,
                onBuscar = { vm.aplicarFiltros(it) }
            )

            Spacer(Modifier.height(8.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(ui.lista, key = { it.idEntrada }) { e ->
                    EntryItem(
                        item = e,
                        money = { money.format(it) },
                        onEditar = { onEditar(e) },
                        onEliminar = { vm.eliminar(e) }
                    )
                }
            }
        }
    }
}

@Composable
private fun EntryItem(
    item: Huacales,
    money: (Double) -> String,
    onEditar: () -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(item.nombreCliente, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.weight(1f))
                Text(item.fecha, style = MaterialTheme.typography.labelLarge)
            }
            Spacer(Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("${item.cantidad} x", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.width(8.dp))
                Text(money(item.precio), style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.width(12.dp))
                Text("= ", style = MaterialTheme.typography.titleLarge)
                Text(
                    money(item.totalLinea),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = onEditar) { Text("Editar") }
                OutlinedButton(onClick = onEliminar) { Text("Eliminar") }
            }
        }
    }
}

@Composable
private fun SummaryBar(conteo: Int, total: String) {
    Surface(
        tonalElevation = 2.dp,
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("$conteo", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.weight(1f))
            Text(total, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun FiltersCard(
    filtros: Filtros,
    onBuscar: (Filtros) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.large,
        tonalElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Filtros", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.FilterList, contentDescription = "filtros")
                }
            }
            AnimatedVisibility(expanded) {
                Column(Modifier.fillMaxWidth()) {
                    FiltrosBar(filtros = filtros, onBuscar = onBuscar)
                }
            }
        }
    }
}
