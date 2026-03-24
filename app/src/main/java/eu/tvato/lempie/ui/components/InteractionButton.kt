package eu.tvato.lempie.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tvato.lempie.R

@Composable
fun InteractionButton(
    res: Int,
    text: String?,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier.padding(end = 5.dp)){
        Icon(
            painter = painterResource(res),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.surfaceBright
        )
        if(text != null) Text(
            text = text,
            color = MaterialTheme.colorScheme.surfaceBright,
            modifier = Modifier.align(Alignment.CenterVertically).padding(start = 5.dp),
        )
    }
}

@Preview
@Composable
fun InteractionButtonPreview(){
    InteractionButton(
        res = R.drawable.upvote,
        text = "200"
    )
}