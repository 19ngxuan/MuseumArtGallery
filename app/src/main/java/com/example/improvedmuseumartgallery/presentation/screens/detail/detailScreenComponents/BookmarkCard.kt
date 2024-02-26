package com.example.improvedmuseumartgallery.presentation.screens.detail.detailScreenComponents

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.improvedmuseumartgallery.presentation.dimensions.dimens.smallPadding
import com.example.improvedmuseumartgallery.presentation.screens.common.BookmarkButton
import com.example.improvedmuseumartgallery.presentation.theme.poppinsFontFamily

@Composable
fun BookMarkCard(isBookmarked: Boolean, onBookmarkClick:() -> Unit) {
    Box(modifier = Modifier
        .width(360.dp)
        .height(56.dp),
        contentAlignment = Alignment.Center) {
        Row(modifier = Modifier.padding(smallPadding)) {

            Box(modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center) {
                Text(
                    text = "You can bookmark this artwork as favorite!",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }

            BookmarkButton(
                isBookmarked = isBookmarked,
                onBookmarkClick = onBookmarkClick)


        }
    }

}

@Preview(
    name = "Dark Mode",
    showBackground = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun BookPreview() {
    BookMarkCard(false,{})
}
