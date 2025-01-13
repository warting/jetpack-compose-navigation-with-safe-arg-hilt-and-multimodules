package se.warting.result

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import se.warting.destination.Destination

@Module
@InstallIn(SingletonComponent::class)
object ResultModule {
    @Provides
    @IntoSet
    fun provideResultNavigations(): Destination {
        return ResultDestinationImpl()
    }
}