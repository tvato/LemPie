package eu.tvato.lempie

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tvato.lempie.database.DatabaseViewModel
import eu.tvato.lempie.database.LempieDatabase
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = LempieDatabase.getDatabase(this)
        val dbViewModel = DatabaseViewModel(
            settingsDao = db.settingsDao(),
            themeDao = db.themeDao(),
            dateTimeFormatDao = db.dateTimeFormatDao()
        )
        enableEdgeToEdge()
        setContent {
            //val dbViewModel: DatabaseViewModel = DatabaseViewModel(AppDataContainer(this).repository)
            dbViewModel.setUserId(1)
            dbViewModel.addTheme("Dark")
            dbViewModel.addDateTimeFormat("default", "MMM d, yy, HH:mm")
            dbViewModel.addSettings()
            dbViewModel.loadUserSettings()
            val settings = dbViewModel.userSettings.collectAsState()
            Log.d("db", "Settings 3: ${settings.value}")
            LemPieTheme(theme = Theme.Dark){
                /*HomeScreen(
                    modifier = Modifier
                )*/
                DBTestScreen()
                /*UserScreen(
                    innerPadding = PaddingValues(0.dp),
                    navController = rememberNavController(),
                    userId = 9166
                )*/
            }
        }
    }
}

@Preview
@Composable
fun DBTestScreen(){
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "Hello!!"
        )
    }
}

@Composable
fun LoadingItem(modifier: Modifier = Modifier){
    Card(
        modifier = modifier,
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(),
        border = null
    ) {
        Text(
            text = "Loading...",
            fontSize = 30.sp,
            modifier = modifier.padding(start = 10.dp, top = 10.dp, bottom = 20.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(text = "Tag")
            Text(text = "Timestamp")
        }
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Fuck off",
            modifier = modifier.align(Alignment.CenterHorizontally)
                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp, end = 10.dp)
        )
    }
}

@Composable
fun ErrorItem(message: String, onRetry: () -> Unit, modifier: Modifier = Modifier){
    Card(
        modifier = modifier,
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(),
        border = null
    ) {
        Text(
            text = "Error loading",
            fontSize = 30.sp,
            modifier = modifier.padding(start = 10.dp, top = 10.dp, bottom = 20.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(text = "Tag")
            Text(text = "Timestamp")
        }
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Fuck off",
            modifier = modifier.align(Alignment.CenterHorizontally)
                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp, end = 10.dp)
        )
    }
}
