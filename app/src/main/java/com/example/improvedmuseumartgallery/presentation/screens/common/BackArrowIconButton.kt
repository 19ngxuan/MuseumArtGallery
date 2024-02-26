package com.example.improvedmuseumartgallery.presentation.screens.common

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.improvedmuseumartgallery.presentation.dimensions.dimens

@Composable
fun BackButton(onclick: () -> Unit) {
    IconButton(onClick = onclick) {
        Icon(
            modifier = Modifier
                .size(dimens.normalSizedIcon),
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Go Back",
            tint = Color.Black,


            )
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = false,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = false,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun ButtonPreview() {
    BackButton({})
}
