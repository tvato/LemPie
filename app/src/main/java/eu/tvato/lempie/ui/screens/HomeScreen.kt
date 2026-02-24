package eu.tvato.lempie.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import eu.tvato.lempie.post.PostViewModel
import eu.tvato.lempie.ui.components.DrawerMenu
import eu.tvato.lempie.ui.components.HomeTopBar
import eu.tvato.lempie.ui.components.PostCard
import eu.tvato.lempie.ui.components.PostTopBar
import eu.tvato.lempie.ui.previewdata.previewPosts
import eu.tvato.lempie.ui.previewdata.previewUsers
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = "Home",
        modifier = modifier
    ){
        composable(route = "Home"){
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            BackHandler(enabled = drawerState.isOpen) {
                scope.launch {
                    drawerState.close()
                }
            }
            DrawerMenu(
                drawerState = drawerState,
                navController = navController
            ){
                Scaffold(
                    modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        HomeTopBar(
                            scrollBehavior = scrollBehavior,
                            scope = scope,
                            drawerState = drawerState
                        )
                    }
                ) { innerPadding ->
                    CardList(
                        modifier = modifier,
                        innerPadding = innerPadding,
                        navController = navController
                    )
                }
            }
        }
        composable(
            route = "Post/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStack ->
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
            Scaffold(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    PostTopBar(
                        scrollBehavior = scrollBehavior,
                        navController = navController
                    )
                }
            ) { innerPadding ->
                PostScreen(
                    modifier = modifier,
                    navController = navController,
                    innerPadding = innerPadding,
                    postId = backStack.arguments!!.getInt("postId")
                )
            }
        }
        composable(route = "Settings"){
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
            Scaffold(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    PostTopBar(
                        scrollBehavior = scrollBehavior,
                        navController = navController
                    )
                }
            ) { innerPadding ->
                SettingsScreen(
                    innerPadding = innerPadding,
                    navController = navController,
                    modifier = modifier
                )
            }
        }
        composable(
            route = "User/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ){ backStack ->
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
            Scaffold(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    PostTopBar(
                        scrollBehavior = scrollBehavior,
                        navController = navController
                    )
                }
            ) { innerPadding ->
                UserScreen(
                    innerPadding = innerPadding,
                    navController = navController,
                    userId = backStack.arguments!!.getInt("userId")
                )
            }
        }
        composable(
            route = "Community/{communityId}",
            arguments = listOf(navArgument("communityId"){ type = NavType.IntType })
        ){ backStack ->
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
            Scaffold(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    PostTopBar(
                        scrollBehavior = scrollBehavior,
                        navController = navController
                    )
                }
            ){ innerPadding ->
                CommunityScreen(
                    navController = navController,
                    innerPadding = innerPadding,
                    communityId = backStack.arguments!!.getInt("communityId")
                )
            }
        }
        composable(route = "DateAndTimeSettings"){
            DateAndTimeScreen()
        }
    }
}

@Composable
fun CardList(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    navController: NavHostController,
){
    val viewModel: PostViewModel = viewModel()
    val items = viewModel.posts.collectAsLazyPagingItems()
    LazyColumn(
        modifier = modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentPadding = innerPadding
    ) {
        items(
            count = items.itemCount,
            key = { index -> items[index]?.post?.id ?: index }
        ){ index ->
            PostCard(
                post = items[index]?.post,
                user = items[index]?.creator,
                community = items[index]?.community,
                navController = navController,
                limitTextRows = true,
                noText = true,
                modifier = modifier
            )
        }

        /*items.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = error.error.localizedMessage ?: "Error",
                            onRetry = { retry() }
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    val error = loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = error.error.localizedMessage ?: "Error",
                            onRetry = { retry() }
                        )
                    }
                }
            }
        }*/
    }
}

@Preview
@Composable
fun CardListPreviewDark(){
    LemPieTheme(theme = Theme.Dark) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            items(
                count = previewPosts.size,
                key = { index -> previewPosts[index].id }
            ) { index ->
                PostCard(
                    post = previewPosts[index],
                    user = previewUsers[0],
                    community = null,
                    navController = rememberNavController(),
                    limitTextRows = true
                )
            }
        }
    }
}

@Preview
@Composable
fun CardListPreviewLight(){
    LemPieTheme(theme = Theme.Light) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            items(
                count = previewPosts.size,
                key = { index -> previewPosts[index].id }
            ) { index ->
                PostCard(
                    post = previewPosts[index],
                    user = previewUsers[0],
                    community = null,
                    navController = rememberNavController(),
                    limitTextRows = true
                )
            }
        }
    }
}

@Preview
@Composable
fun CardListPreviewDarkGen(){
    LemPieTheme(theme = Theme.DarkGen) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            items(
                count = previewPosts.size,
                key = { index -> previewPosts[index].id }
            ) { index ->
                PostCard(
                    post = previewPosts[index],
                    user = previewUsers[0],
                    community = null,
                    navController = rememberNavController(),
                    limitTextRows = true
                )
            }
        }
    }
}