package eu.tvato.lempie.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme

@Composable
fun SetInstanceDialog(
    dismissRequest: () -> Unit,
    modifier: Modifier = Modifier
){
    Dialog(
        onDismissRequest = { dismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = modifier.clip(RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors().copy(
                containerColor = MaterialTheme.colorScheme.surfaceBright
            )
        ){
            Text(
                text = "Set instance:",
                color = MaterialTheme.colorScheme.onSurface,
                modifier = modifier.padding(10.dp).align(Alignment.CenterHorizontally)
            )
            TextField(
                value = "https://example.com",
                onValueChange = {},
                singleLine = true,
                colors = TextFieldDefaults.colors().copy(
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow
                ),
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
            Row{
                TextButton(
                    onClick = {},
                    modifier = modifier.padding(10.dp)
                ) {
                    Text("Cancel")
                }
                Spacer(modifier = modifier.weight(1f))
                TextButton(
                    onClick = {},
                    modifier = modifier.padding(10.dp)
                ) {
                    Text("Confirm")
                }
            }
        }
    }
}

@Preview
@Composable
fun SetInstanceDialogPreview(){
    LemPieTheme(theme = Theme.Dark) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Black)
        ) {
            SetInstanceDialog({})
        }
    }
}