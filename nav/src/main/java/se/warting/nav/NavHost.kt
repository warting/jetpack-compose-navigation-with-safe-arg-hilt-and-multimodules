import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import se.warting.destination.Destination
import se.warting.destination.baseuri


// /Users/stefan/Library/Android/sdk/platform-tools/adb shell am start -a android.intent.action.VIEW -d "deeplinks://warting/profile/1234
// /Users/stefan/Library/Android/sdk/platform-tools/adb shell am start -a android.intent.action.VIEW -d "deeplinks://warting/hidden"

@Composable
fun MyNavHost(destinations: Set<Destination>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {
        destinations.forEach { destination->
            destination.host(this)
        }
        composable<Home> {
            HomeScreen(onNavigateToProfile = { id ->
                navController.navigate(Profile(id))
            })
        }
        composable<Profile>(
            deepLinks = listOf(
                navDeepLink<Profile>(basePath = "$baseuri/profile")
            )
        ) { backStackEntry ->
            val profile: Profile = backStackEntry.toRoute()
            ProfileScreen(profile.id)
        }
    }
}

// Define a home route that doesn't take any arguments
@Serializable
object Home

// Define a profile route that takes an ID
@Serializable
data class Profile(val id: String)


@Composable
fun HomeScreen(onNavigateToProfile: (String) -> Unit) {

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Button(onClick = { onNavigateToProfile("123") }) {
            Text("Home->profile 123")
        }
    }

}

@Composable
fun ProfileScreen(profile: String) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Text("profile $profile")
    }
}