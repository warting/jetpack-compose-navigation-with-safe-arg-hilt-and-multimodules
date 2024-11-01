package se.warting.legacy

import androidx.navigation.NavGraphBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import se.warting.destination.Destination

@Module
@InstallIn(SingletonComponent::class)
object LegacyModule {
    @Provides
    @IntoSet
    fun provideLegacyNavigations(): Destination {
        return LegacyDestinationImpl()
    }
}


class LegacyDestinationImpl : Destination {
    override fun host(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.legacyNavigation()
    }
}