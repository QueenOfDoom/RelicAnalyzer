package edu.shch.hsr.relicanalyzer.ui.view.dialogue

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import edu.shch.hsr.relicanalyzer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkInProgress(
    imageLoader: ImageLoader,
    context: Context,
    dismiss: () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = dismiss,
        content = {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.5f)
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(64.dp))
                    .border(4.dp, MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(64.dp))
                    .padding(top = 40.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.ui_do_not_touch),
                    fontSize = 7.em,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(context).data(data = R.drawable.herta_kuru)
                            .apply(block = {
                                size(Size.ORIGINAL)
                            }).build(),
                        imageLoader = imageLoader
                    ),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        })
}