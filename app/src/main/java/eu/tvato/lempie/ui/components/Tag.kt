package eu.tvato.lempie.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import eu.tvato.lempie.ui.theme.LemPieTheme

@Composable
fun Tag(
    modifier: Modifier = Modifier,
    text: String?
){
    Box(
        modifier = modifier.heightIn(max = 25.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text.toString(),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = modifier.padding(start = 5.dp, end = 5.dp, bottom = 2.dp),
        )
    }
}

@PreviewLightDark
@Composable
fun TagPreview(){
    LemPieTheme() {
        Tag(text = "TEST")
    }
}