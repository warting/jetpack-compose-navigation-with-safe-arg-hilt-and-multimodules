package se.warting.legacy

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.fragment.compose.AndroidFragment
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import se.warting.destination.baseuri

@Serializable
data class Legacy(val value1: String, val value2: String)

fun NavGraphBuilder.legacyNavigation() {
    composable<Legacy>(
        deepLinks = listOf(
            navDeepLink<Legacy>("$baseuri/legacy") {
                uriPattern = "deeplinks://warting/legacy/{value1}/{value2}"
            },
        ),
    ) { backStackEntry ->
        val legacy: Legacy = backStackEntry.toRoute()

        AndroidFragment<LegacyFragment>(
            arguments = LegacyFragment.paramBundle(legacy.value1, legacy.value2),
            modifier = Modifier.fillMaxSize(),
        )
    }
}
