package edu.ucne.angel_paredes_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.angel_paredes_ap2_p1.Presentation.Huacales.HuacalesListScreen
import edu.ucne.angel_paredes_ap2_p1.Presentation.Huacales.HuacalesScreen
import edu.ucne.angel_paredes_ap2_p1.Presentation.Huacales.HuacalesViewModel

class MainActivity : ComponentActivity() {
    private val vm: HuacalesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val nav = rememberNavController()
                NavHost(navController = nav, startDestination = "list") {
                    composable("list") {
                        HuacalesListScreen(
                            vm,
                            onNuevo = { nav.navigate("form") },
                            onEditar = { vm.editar(it); nav.navigate("form") }
                        )
                    }
                    composable("form") { HuacalesScreen(vm) { nav.popBackStack() } }
                }
            }
        }
    }
}
