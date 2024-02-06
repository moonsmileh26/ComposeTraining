package mx.com.moonsmileh.composetraining.ui.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import mx.com.moonsmileh.composetraining.R
import mx.com.moonsmileh.composetraining.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel, context: Context) {
    val state = viewModel.state

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(items = viewModel.state.characters) {
                val image = it.image ?: ""
                LoadImage(image, context)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun LoadImage(imageUrl: String, context: Context) {
    Box(modifier = Modifier
        .padding(2.dp)
        .background(Color.Black)
        .fillMaxHeight(150f)) {
        GlideImage(
            model = imageUrl,
            contentScale = ContentScale.FillWidth,
            contentDescription = context.getString(R.string.image_description),
            loading = placeholder(context.getDrawable(R.drawable.mouse))
        )
    }
}
