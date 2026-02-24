package eu.tvato.lempie.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme

@Composable
fun DateAndTimeScreen(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp)
        ){
            Text(
                text = "Datetime format:",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = modifier.weight(1f))
            TextField(
                value = "",
                onValueChange = {},
                modifier = modifier.size(width = 200.dp, height = 50.dp),
                textStyle = TextStyle.Default
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp)
        ){
            Text(
                text = "Time format:",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = modifier.weight(1f))
            TextField(
                value = "HH:mm",
                onValueChange = {},
                modifier = modifier.size(width = 200.dp, height = 50.dp),
                textStyle = TextStyle.Default
            )
        }
    }
}

@Preview
@Composable
fun DateAndTimeScreenPreview(
    theme: Theme = Theme.Dark
){
    LemPieTheme(theme = theme) {
        DateAndTimeScreen()
    }
}