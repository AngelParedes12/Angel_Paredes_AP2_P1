package edu.ucne.angel_paredes_ap2_p1.Presentation.Huacales

data class HuacalesUiState(
    val huacalesId: Int? = null,
    val nombre: String = "",
    val cantidad: Int = 0,
    val isSaving: Boolean = false,
    val errorMessages: List<String> = emptyList()
){
    companion object{
    fun default() = HuacalesUiState()
    }
}