package eu.tvato.lempie.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import eu.tvato.lempie.R
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme

@Composable
fun SettingsScreen(
    innerPadding: PaddingValues,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier.fillMaxSize().background(Color.Black),
        contentPadding = innerPadding
    ) {
        item{
            ButtonRow(
                text = "Theme",
                icon = R.drawable.comment,
                onClick = { navController.navigate("Theme") }
            )
            ButtonRow(
                text = "Interface",
                icon = R.drawable.comment,
                onClick = { navController.navigate("Interface") }
            )
            ButtonRow(
                text = "Something",
                icon = R.drawable.comment,
                onClick = { navController.navigate("Something") }
            )
            ButtonRow(
                text = "Anything",
                icon = R.drawable.comment,
                onClick = { navController.navigate("Anything") }
            )
            ButtonRow(
                text = "Date & Time",
                icon = R.drawable.comment,
                onClick = { navController.navigate("DateAndTimeSettings") }
            )
        }
    }
}

@Composable
fun ButtonRow(
    text: String,
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(start = 10.dp, top = 20.dp)
    ){
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.White,
            modifier = modifier.padding(end = 20.dp)
        )
        Text(
            text = text,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun SettingsScreenPreview(){
    LemPieTheme(theme = Theme.Dark) {
        SettingsScreen(
            innerPadding = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}