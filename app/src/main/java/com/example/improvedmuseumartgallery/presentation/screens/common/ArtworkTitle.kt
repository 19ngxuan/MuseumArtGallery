package com.example.improvedmuseumartgallery.presentation.screens.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.improvedmuseumartgallery.presentation.theme.poppinsFontFamily

@Composable
fun ArtworkTitle(
    title: String
) {


    Text(


        text = title,
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        lineHeight = 36.sp,
        style = TextStyle(
            shadow = Shadow(
                color = Color.Black,
                offset = Offset(x = 0f, y = 4f), // Horizontaler und vertikaler Offset des Schattens
                blurRadius = 2f // Wie "weich" der Schatten aussieht
            )
        )
    )


}