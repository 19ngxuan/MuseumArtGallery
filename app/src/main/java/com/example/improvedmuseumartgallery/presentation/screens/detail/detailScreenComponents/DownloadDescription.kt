package com.example.improvedmuseumartgallery.presentation.screens.detail.detailScreenComponents

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.improvedmuseumartgallery.presentation.screens.common.MyCheckbox
import com.example.improvedmuseumartgallery.presentation.theme.poppinsFontFamily

@Composable
fun DownloadDescription(
    isChecked: Boolean,
    onCheckClick: () -> Unit

) {
    Box(
        modifier = Modifier
            .width(123.dp)
            .height(30.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()

        ) {
            Box() {
                MyCheckbox(
                    isChecked = isChecked,
                    onCheckClick = onCheckClick
                )
            }

            //TODO Wrong Padding
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(

                    text = "Mark for Download",
                    fontSize = 18.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
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
fun DownPreview() {
    DownloadDescription(false, { false })
}
