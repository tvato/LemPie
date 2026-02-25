package eu.tvato.lempie

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tvato.lempie.database.DatabaseViewModel
import eu.tvato.lempie.database.LempieDatabase
import eu.tvato.lempie.ui.screens.HomeScreen
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme
import eu.tvato.lempie.utils.parseIsoDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val db = LempieDatabase.getDatabase(this)
        //val dbViewModel = DatabaseViewModel(db)
        enableEdgeToEdge()
        setContent {
            LemPieTheme(theme = Theme.Dark){
                HomeScreen(
                    modifier = Modifier
                )
                /*UserScreen(
                    innerPadding = PaddingValues(0.dp),
                    navController = rememberNavController(),
                    userId = 9166
                )*/
            }
        }
    }
}


@Composable
fun DBTestScreen(
    format: String,
    backgroundColor: Long,
    textColor: Long,
    dbViewModel: DatabaseViewModel,
    currentFormatId: Int
){
    Column(
        modifier = Modifier.fillMaxSize().background(Color(backgroundColor)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "DB Testing....",
            color = Color(textColor)
        )
        Text(
            text = "2025-10-24T15:09:39.355313Z",
            color = Color(textColor)
        )
        Text(
            text = parseIsoDate("2025-10-24T15:09:39.355313Z", format),
            color = Color(textColor)
        )
        Button(
            onClick = {
                Log.d("db", "Changing format...")
                dbViewModel.changeFormatId(
                    if(currentFormatId < 10) currentFormatId + 1 else 1
                )
                dbViewModel.loadUserSettings()
            }
        ){
            Text(text = "Change format", color = Color(textColor))
        }
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
