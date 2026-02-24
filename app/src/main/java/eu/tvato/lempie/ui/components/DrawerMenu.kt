package eu.tvato.lempie.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme

@Composable
fun DrawerMenu(
    drawerState: DrawerState,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    val setInstanceDialog = remember { mutableStateOf(false) }
    when{
        setInstanceDialog.value -> {
            SetInstanceDialog({setInstanceDialog.value = false})
        }
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.surface,
                drawerTonalElevation = 10.dp
            ){
                Text(
                    text = "Menu",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier.align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp)
                )
                HorizontalDivider(
                    modifier = modifier.padding(top = 10.dp, bottom = 10.dp),
                    color = MaterialTheme.colorScheme.primary,
                    thickness = 2.dp
                )
                Column{
                    Text(
                        text = "Instance",
                        modifier = modifier.padding(start = 15.dp, top = 20.dp, bottom = 10.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Instance info") },
                        selected = false,
                        onClick = { /* TODO() Navigate to Instance Screen */ },
                        modifier = modifier.padding(start = 20.dp)
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Set instance") },
                        selected = false,
                        onClick = { setInstanceDialog.value = true },
                        modifier = modifier.padding(start = 20.dp)
                    )
                    HorizontalDivider(
                        modifier = modifier.padding(top = 10.dp, bottom = 10.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Preferences",
                        modifier = modifier.padding(start = 15.dp, top = 20.dp, bottom = 10.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Theme") },
                        selected = false,
                        onClick = {
                            /* TODO() Change theme (either cycle through, or open a selector dialog)
                                     (toggle between light/dark theme here, selector in settings?)
                            */
                        },
                        modifier = modifier.padding(start = 20.dp)
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Settings") },
                        selected = false,
                        onClick = { navController.navigate("Settings") },
                        modifier = modifier.padding(start = 20.dp)
                    )
                }
            }
        },
        content = content
    )
}

@Composable
fun DrawerMenuPreview(
    theme: Theme
){
    LemPieTheme(theme = theme) {
        DrawerMenu(
            drawerState = rememberDrawerState(DrawerValue.Open),
            navController = rememberNavController()
        ){}
    }
}

@Preview
@Composable
fun DrawerMenuPreviewDark(){
    DrawerMenuPreview(Theme.Dark)
}
@Preview
@Composable
fun DrawerMenuPreviewLight(){
    DrawerMenuPreview(Theme.Light)
}
@Preview
@Composable
fun DrawerMenuPreviewDarkGen(){
    DrawerMenuPreview(Theme.DarkGen)
}