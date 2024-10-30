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

val uri = "deeplinks://warting"

// /Users/stefan/Library/Android/sdk/platform-tools/adb shell am start -a android.intent.action.VIEW -d "deeplinks://warting/profile"

@Composable
fun MyNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(onNavigateToProfile = { id ->
                navController.navigate(Profile(id))
            })
        }
        composable<Profile>(
            deepLinks = listOf(
                navDeepLink<Profile>(basePath = "$uri/profile")
            )
        ) { backStackEntry ->
            val profile: Profile = backStackEntry.toRoute()
            ProfileScreen(profile.id)
        }
        composable<Hidden>(
            deepLinks = listOf(
                navDeepLink<Hidden>(basePath = "$uri/hidden")
            )
        ) { backStackEntry ->
            ProfileScreen("hidden page")
        }
    }
}

// Define a home route that doesn't take any arguments
@Serializable
object Home

// Define a profile route that takes an ID
@Serializable
data class Profile(val id: String)

// Define a profile route that takes an ID
@Serializable
object Hidden


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