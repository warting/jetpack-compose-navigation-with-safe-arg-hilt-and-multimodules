package se.warting.jetpackcomposemultimodulehiltdestinations;

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import se.warting.destination.Destination
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    val destinations: Set<@JvmSuppressWildcards Destination>
) : ViewModel()