import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import se.warting.destination.Destination
import se.warting.destination.baseuri

@Composable
fun MyNavHost(destinations: Set<Destination>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {
        destinations.forEach { destination ->
            destination.host(this)
        }
        composable<Home> {
            HomeScreen(
                onNavigateToProfile = { id ->
                    navController.navigate(Profile(id))
                },
                onNavigateToSampleA = {
                    navController.navigate(SampleA)
                },
                onNavigateToSampleB = {
                    navController.navigate(SampleB)
                },
                onNavigateToSampleC = {
                    navController.navigate(SampleC)
                }
            )
        }
        composable<Profile>(
            deepLinks = listOf(
                navDeepLink<Profile>(basePath = "$baseuri/${Profile.DEEP_LINK}")
            )
        ) { backStackEntry ->
            val profile: Profile = backStackEntry.toRoute()
            ProfileScreen(profile.id)
        }

        navigation<SampleGraph>(startDestination = SampleA) {
            composable<SampleA>(
                deepLinks = listOf(
                    navDeepLink<SampleA>(basePath = "$baseuri/${SampleA.DEEP_LINK}")
                )
            ) {
                SampleScreen("Sample A")
            }
            composable<SampleB>(
                deepLinks = listOf(
                    navDeepLink<SampleB>(basePath = "$baseuri/${SampleB.DEEP_LINK}")
                )
            ) {
                SampleScreen("Sample B")
            }
            composable<SampleC>(
                deepLinks = listOf(
                    navDeepLink<SampleC>(basePath = "$baseuri/${SampleC.DEEP_LINK}")
                )
            ) {
                SampleScreen("Sample C")
            }
        }
    }
}

// Define a home route that doesn't take any arguments
@Serializable
data object Home

// Define a profile route that takes an ID
@Serializable
data class Profile(val id: String) {
    companion object {
        const val DEEP_LINK = "profile"
    }
}

@Serializable
data object SampleGraph

@Serializable
data object SampleA {
    const val DEEP_LINK = "sampleA"
}

@Serializable
data object SampleB {
    const val DEEP_LINK = "sampleB"
}

@Serializable
data object SampleC {
    const val DEEP_LINK = "sampleC"
}


@Composable
fun HomeScreen(
    onNavigateToProfile: (String) -> Unit,
    onNavigateToSampleA: () -> Unit,
    onNavigateToSampleB: () -> Unit,
    onNavigateToSampleC: () -> Unit,
) {
    Scaffold { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .background(Color.Red)
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = { onNavigateToProfile("123") }) {
                    Text("Home -> profile 123")
                }
                Button(onClick = { onNavigateToSampleA() }) {
                    Text("Home -> Sample A")
                }
                Button(onClick = { onNavigateToSampleB() }) {
                    Text("Home -> Sample B")
                }
                Button(onClick = { onNavigateToSampleC() }) {
                    Text("Home -> Sample C")
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(profile: String) {
    Scaffold { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .background(Color.Blue)
        ) {
            Text("profile $profile")
        }
    }
}

@Composable
fun SampleScreen(text: String) {
    Scaffold { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .background(Color.Green)
        ) {
            Text(text)
        }
    }
}
