package se.warting.result

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import se.warting.destination.Destination
import se.warting.result.entername.EnterName
import se.warting.result.name.NameScreen


class ResultDestinationImpl : Destination {
    override fun host(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<NavigationResultDestination> { backStackEntry ->

            val name by backStackEntry.savedStateHandle
                .getStateFlow("name_result", "")
                .collectAsStateWithLifecycle()

            NameScreen(name = name,
                navigateToEnterName = {
                    navController.navigate(EnterNameDestination)
                })
        }
        navGraphBuilder.composable<EnterNameDestination> {
            EnterName(saveName = { name ->
                navController.previousBackStackEntry?.savedStateHandle?.set("name_result", name)
                navController.popBackStack()
            })
        }
    }
}