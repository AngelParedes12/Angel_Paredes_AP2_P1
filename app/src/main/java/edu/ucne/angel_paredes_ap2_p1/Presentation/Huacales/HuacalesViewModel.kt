package edu.ucne.angel_paredes_ap2_p1.Presentation.Huacales

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.ucne.angel_paredes_ap2_p1.Data.Local.Database.HuacalesDb
import edu.ucne.angel_paredes_ap2_p1.Data.Repository.HuacalesRepository
import edu.ucne.angel_paredes_ap2_p1.Domain.Model.Huacales
import edu.ucne.angel_paredes_ap2_p1.Domain.UseCase.EliminarHuacales
import edu.ucne.angel_paredes_ap2_p1.Domain.UseCase.GuardarHuacales
import edu.ucne.angel_paredes_ap2_p1.Domain.UseCase.HuacalesUseCase
import edu.ucne.angel_paredes_ap2_p1.Domain.UseCase.ObtenerHuacalesUseCase
import edu.ucne.angel_paredes_ap2_p1.Domain.UseCase.ValidarHuacales
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class Filtros(
    val cliente: String? = null,
    val desde: String? = null,
    val hasta: String? = null,
    val minCant: Int? = null,
    val maxCant: Int? = null,
    val minPrecio: Double? = null,
    val maxPrecio: Double? = null
)

data class UiState(
    val lista: List<Huacales> = emptyList(),
    val conteo: Int = 0,
    val total: Double = 0.0,
    val filtros: Filtros = Filtros(),
    val editando: Huacales? = null,
    val error: String? = null
)

class HuacalesViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = HuacalesRepository(HuacalesDb.getInstance(app).huacalesDao())
    private val use = HuacalesUseCase(
        guardar = GuardarHuacales(repo, ValidarHuacales()),
        eliminar = EliminarHuacales(repo),
        obtener = ObtenerHuacalesUseCase(repo),
        validar = ValidarHuacales()
    )

    private val _ui = MutableStateFlow(UiState())
    val ui: StateFlow<UiState> = _ui

    init { aplicarFiltros(Filtros()) }

    fun aplicarFiltros(f: Filtros) {
        _ui.update { it.copy(filtros = f) }

        viewModelScope.launch {
            use.obtener
                .filtrar(f.cliente, f.desde, f.hasta, f.minCant, f.maxCant, f.minPrecio, f.maxPrecio)
                .collectLatest { list -> _ui.update { s -> s.copy(lista = list) } }
        }
        viewModelScope.launch {
            use.obtener
                .conteo(f.cliente, f.desde, f.hasta, f.minCant, f.maxCant, f.minPrecio, f.maxPrecio)
                .collectLatest { c -> _ui.update { s -> s.copy(conteo = c) } }
        }
        viewModelScope.launch {
            use.obtener
                .total(f.cliente, f.desde, f.hasta, f.minCant, f.maxCant, f.minPrecio, f.maxPrecio)
                .collectLatest { t -> _ui.update { s -> s.copy(total = t) } }
        }
    }

    fun nuevo() { _ui.update { it.copy(editando = Huacales(fecha = hoy())) } }
    fun editar(h: Huacales) { _ui.update { it.copy(editando = h) } }
    fun cancelar() { _ui.update { it.copy(editando = null, error = null) } }

    fun guardar(h: Huacales) = viewModelScope.launch {
        runCatching { use.guardar(h) }
            .onSuccess { cancelar(); aplicarFiltros(_ui.value.filtros) }
            .onFailure { ex -> _ui.update { s -> s.copy(error = ex.message ?: "Error al guardar") } }
    }

    fun eliminar(h: Huacales) = viewModelScope.launch {
        use.eliminar(h)
        aplicarFiltros(_ui.value.filtros)
    }

    private fun hoy() = java.time.LocalDate.now().toString()
}
