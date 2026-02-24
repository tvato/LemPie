package eu.tvato.lempie.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import eu.tvato.lempie.ui.theme.LemPieTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    scope: CoroutineScope,
    drawerState: DrawerState
){
    TopAppBar(
        title = {
            Text(
                text = "LemPie"
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.apply{
                        if(isClosed) open() else close()
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Drawer menu"
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Add"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavHostController
){
    TopAppBar(
        title = {
            Text(
                text = "LemPie"
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go back"
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavHostController,
    username: String,
){
    TopAppBar(
        title = {
            Text(
                text = username
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go back"
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {      // Search?
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
            IconButton(onClick = {}) {      // Sort?
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
            // TODO() One button for "more" -menu
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
fun HomeTopBarPreview(){
    LemPieTheme {
        HomeTopBar(
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            scope = rememberCoroutineScope(),
            drawerState = rememberDrawerState(DrawerValue.Closed)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
fun PostTopBarPreview(){
    LemPieTheme {
        PostTopBar(
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            navController = rememberNavController()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
fun UserTopBarPreview(){
    LemPieTheme{
        UserTopBar(
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            navController = rememberNavController(),
            username = "PreviewUser"
        )
    }
}