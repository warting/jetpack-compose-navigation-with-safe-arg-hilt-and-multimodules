package se.warting.result.name

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NameScreen(
    name: String,
    navigateToEnterName: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Text("Your name: $name")
        Button(onClick = navigateToEnterName) {
            Text("Navigate to enter name")
        }
    }
}
