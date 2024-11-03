package se.warting.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import se.warting.destination.Destination
import se.warting.destination.baseuri

@Module
@InstallIn(SingletonComponent::class)
object WelcomeModule {
    @Provides
    @IntoSet
    fun provideWelcomeNavigations(): Destination {
        return WelcomeDestinationImpl()
    }
}

@Composable
fun SecretScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Text("You found a golden nugget!!!")
    }
}

class WelcomeDestinationImpl : Destination {
    override fun host(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable<Hidden>(
            deepLinks = listOf(
                navDeepLink<Hidden>(basePath = "$baseuri/hidden")
            )
        ) { backStackEntry ->
            SecretScreen()
        }
    }
}