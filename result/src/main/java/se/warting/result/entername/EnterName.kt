package se.warting.result.entername

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EnterName(
    saveName: (name: String) -> Unit
) {
    Box(
        Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            saveName("woop")
        }) {
            Text("woop")
        }

    }
}
