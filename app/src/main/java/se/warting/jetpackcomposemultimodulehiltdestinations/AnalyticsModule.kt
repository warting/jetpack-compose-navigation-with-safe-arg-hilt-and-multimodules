package se.warting.jetpackcomposemultimodulehiltdestinations

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object AnalyticsModule {
    @Provides
    fun provideAnalyticsService(): AnalyticsService = AnalyticsServiceImpl()
}


interface AnalyticsService {
    fun logEvent(event: String)
}

class AnalyticsServiceImpl : AnalyticsService {
    override fun logEvent(event: String) {
        println("Event: $event")
    }
}