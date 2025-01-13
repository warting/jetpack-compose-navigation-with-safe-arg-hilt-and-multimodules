import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
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

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(Modifier.weight(.5f)) {
            NavHostComponent(
                navController = navController,
                destinations = destinations
            )
        }


        Box(Modifier.weight(.5f)) {
            BackStackLogger(navController = navController)
        }
    }
}

@Composable
fun NavHostComponent(
    navController: NavHostController,
    destinations: Set<Destination>,
) {
    NavHost(
        navController = navController,
        startDestination = Home,
    ) {
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
                },
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
                SampleScreen(type = SampleScreenEnum.SampleAType)
            }
            composable<SampleB>(
                deepLinks = listOf(
                    navDeepLink<SampleB>(basePath = "$baseuri/${SampleB.DEEP_LINK}")
                )
            ) {
                SampleScreen(type = SampleScreenEnum.SampleBType)
            }

            composable<Home> {
                HomeScreen(
                    text = "I'm within the nav wrapper - London",
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
                    },
                )
            }

            composable<SampleC>(
                deepLinks = listOf(
                    navDeepLink<SampleC>(basePath = "$baseuri/${SampleC.DEEP_LINK}")
                )
            ) {
                SampleScreen(
                    type = SampleScreenEnum.SampleCType,
                    onNavigateToHome = { navController.navigate(Home) })
            }

            composable<Home> {
                HomeScreen(
                    text = "I'm within the nav wrapper - Stockholm",
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
                    },
                )
            }

            composable<Home> {
                HomeScreen(
                    text = "I'm within the nav wrapper - Manchester",
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
                    },
                )
            }
        }

        composable<Home> {
            HomeScreen(
                text = "I'm within the nav wrapper - Liverpool",
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
                },
            )
        }
    }
}

@Composable
fun BackStackLogger(
    navController: NavController,
) {
    val currentBackStack by navController.currentBackStack.collectAsStateWithLifecycle()
    val graph = navController.graph.toList()

    Box(Modifier.background(Color.Black.copy(alpha = .8f))) {
        when {
            currentBackStack.isEmpty() -> Text(
                "Empty back stack",
                fontSize = 20.sp,
            )

            else -> Row(Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.weight(.5f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(currentBackStack) {
                        Text(
                            String.format(
                                "%s %s",
                                it.destination.id,
                                it.destination.route ?: "Graph route"
                            ),
                            fontSize = 20.sp,
                            color = Color.White,
                        )
                    }
                }

                LazyColumn(
                    modifier = Modifier.weight(.5f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(graph.toList()) {
                        Text(
                            String.format(
                                "%s %s",
                                it.id,
                                it.route ?: "Graph route"
                            ),
                            fontSize = 20.sp,
                            color = Color.White,
                        )
                    }
                }
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
    text: String = "I'm in the root",
    onNavigateToProfile: (String) -> Unit,
    onNavigateToSampleA: () -> Unit,
    onNavigateToSampleB: () -> Unit,
    onNavigateToSampleC: () -> Unit,
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .background(Color.Red)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text,
                fontSize = 20.sp,
            )

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

@Composable
fun ProfileScreen(profile: String) {
    Scaffold { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .background(Color.Blue)
                .verticalScroll(rememberScrollState()),
        ) {
            Text("profile $profile")
        }
    }
}

@Composable
fun SampleScreen(
    type: SampleScreenEnum,
    onNavigateToHome: () -> Unit = {}
) {
    Scaffold { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .background(Color.Green)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = when (type) {
                    SampleScreenEnum.SampleAType -> "Sample A"
                    SampleScreenEnum.SampleBType -> "Sample B"
                    SampleScreenEnum.SampleCType -> "Sample C"
                }
            )

            when (type) {
                SampleScreenEnum.SampleCType -> Button(onClick = onNavigateToHome) {
                    Text("Navigate to Home")
                }

                else -> Unit
            }
        }
    }
}

enum class SampleScreenEnum {
    SampleAType, SampleBType, SampleCType
}
