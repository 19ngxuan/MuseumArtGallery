package com.example.improvedmuseumartgallery.presentation.screens.detail.detailScreenComponents

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.improvedmuseumartgallery.presentation.dimensions.dimens.normalPadding
import com.example.improvedmuseumartgallery.presentation.dimensions.dimens.smallPadding
import com.example.improvedmuseumartgallery.presentation.theme.poppinsFontFamily

@Composable
fun ArtworkDescription(description: String = "Eafefjaeofajfano anefonefna naeofnain naeofangn naeonng aeo ewfewf jefwfa") {
    Card(
        modifier = Modifier
            .width(443.dp)
            .height(178.dp)
            .padding(normalPadding),

        ) {

        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(smallPadding),
            text = description,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold
        )


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
fun ArtPreview() {
    ArtworkDescription()
}