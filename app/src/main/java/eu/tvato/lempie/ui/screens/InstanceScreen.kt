package eu.tvato.lempie.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import eu.tvato.lempie.R
import eu.tvato.lempie.site.SiteResponse
import eu.tvato.lempie.ui.previewdata.siteResponse
import eu.tvato.lempie.ui.screens.viewmodel.InstanceViewModel
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme
import eu.tvato.lempie.utils.Utils

@Composable
fun InstanceScreen(
    navController: NavHostController,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
){
    val viewModel: InstanceViewModel = viewModel()
    val instance = viewModel.site.collectAsState()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item{
            AsyncImage(
                model = instance.value?.siteView?.site?.bannerUrl,
                contentDescription = null
            )
        }
        item {
            Card(
                modifier = modifier.fillMaxSize().padding(start = 5.dp, end = 5.dp, bottom = 10.dp),
                colors = CardDefaults.cardColors().copy(
                    containerColor = MaterialTheme.colorScheme.surface,     // TODO() Change these? And also in UserScreen and CommunityScreen?
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                InfoRow(
                    firstText = "Users: ${Utils.addNumberDelimiter(instance.value?.siteView?.counts?.users)}",
                    secondText = "Active users: ${Utils.addNumberDelimiter(instance.value?.siteView?.counts?.usersActiveMonth)}",
                    modifier = modifier.padding(top = 10.dp)
                )
                InfoRow(
                    firstText = "Posts: ${Utils.addNumberDelimiter(instance.value?.siteView?.counts?.posts)}",
                    secondText = "Comments: ${Utils.addNumberDelimiter(instance.value?.siteView?.counts?.comments)}"
                )
                InfoRow(
                    firstText = "Communities: ${Utils.addNumberDelimiter(instance.value?.siteView?.counts?.communities)}",
                    modifier = modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        item { AdminsCard(instance = instance.value) }

        item {
            Text(
                text = Utils.parseMarkdown("${instance.value?.siteView?.site?.sidebar}", LocalContext.current),
                color = Color.White,
            )
        }
    }
}

@Composable
fun AdminsCard(
    instance: SiteResponse?
){
    Card(
        modifier = Modifier.fillMaxSize().padding(start = 5.dp, bottom = 10.dp, end = 5.dp, top = 5.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.surface,     // TODO() Change these? And also in UserScreen and CommunityScreen?
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(
            text = "Admins:",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 15.dp, start = 15.dp, bottom = 10.dp)
        )
        instance?.admins?.forEach {
            Row(
                modifier = Modifier.padding(start = 15.dp, bottom = if(it.user == instance.admins.last().user) 10.dp else 0.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.lempie_001),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .sizeIn(maxWidth = 20.dp, maxHeight = 20.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = "${it.user.displayName}",
                    color = Color.Red,
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                )
            }
        }
    }
}

@Composable
fun InfoRow(
    firstText: String,
    modifier: Modifier = Modifier,
    secondText: String? = null,
){
    Row(
        modifier = modifier
            .padding(start = 35.dp, end = 35.dp, top = 10.dp, bottom = 10.dp),
    ) {
        Text(
            text = firstText
        )
        if(secondText != null) {
            Spacer(modifier.weight(1f))
            Text(
                text = secondText
            )
        }
    }
}

@Preview
@Composable
fun InstanceScreenPreview(
    theme: Theme = Theme.Dark
){
    val previewInstance = siteResponse
    LemPieTheme(theme = theme) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer),
        ){
            item{
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
                    colors = CardDefaults.cardColors().copy(
                        containerColor = MaterialTheme.colorScheme.surface,     // TODO() Change these? And also in UserScreen and CommunityScreen?
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    InfoRow(
                        firstText = "Users: ${Utils.addNumberDelimiter(previewInstance.siteView.counts.users)}",
                        secondText = "Active users: ${Utils.addNumberDelimiter(previewInstance.siteView.counts.usersActiveMonth)}",
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    InfoRow(
                        firstText = "Posts: ${Utils.addNumberDelimiter(previewInstance.siteView.counts.posts)}",
                        secondText = "Comments: ${Utils.addNumberDelimiter(previewInstance.siteView.counts.comments)}"
                    )
                    InfoRow(
                        firstText = "Communities: ${Utils.addNumberDelimiter(previewInstance.siteView.counts.communities)}",
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 5.dp)
                    )
                }
            }

            item { AdminsCard(instance = previewInstance) }

            item {
                Text(
                    text = Utils.parseMarkdown("${previewInstance.siteView.site.sidebar}", LocalContext.current),
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}
