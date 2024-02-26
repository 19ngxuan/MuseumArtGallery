package com.example.improvedmuseumartgallery.presentation.screens.detail.detailScreenComponents

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.improvedmuseumartgallery.R

@Composable
fun ArtworkCard(image: String, isChecked: Boolean, onCheckClick: () -> Unit) {

    Column {
        AsyncImage(
            modifier = Modifier
                .width(123.dp)
                .height(150.dp)
                .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(image)
                .build(),
            contentDescription = stringResource(R.string.art_picture),


            contentScale = Crop,
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),

            )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                .background(Color.White)


        ) {

            DownloadDescription(isChecked = isChecked, onCheckClick = onCheckClick)
        }
    }


}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun CardPreview() {
    ArtworkCard("Hello", false) { }
}