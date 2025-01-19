package com.example.android_project.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_project.R
import com.example.android_project.classes.CategoryVM


@Composable
fun CategoryCard(
    categorieVM: CategoryVM,
    modifier: Modifier = Modifier,
    onDeleteClick: (CategoryVM) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp) // Padding around the entire card
    ) {
        Column {
            // Box for image and the name bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Adjust the height based on your preference
            ) {
                // Image placed inside the Box
                AsyncImage(
                    model = categorieVM.img,
                    placeholder = painterResource(id = R.drawable.placeholder), // Placeholder image
                    error = painterResource(id = R.drawable.placeholder), // Fallback image if loading fails
                    contentDescription = "Logo ${categorieVM.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)) // Rounded corners for the image
                        .background(Color.Gray), // Optional background color for empty space
                    contentScale = ContentScale.Crop // Image fills its bounds while maintaining aspect ratio
                )

                // Black bar with category name at the bottom
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(Color.Black) // Black bar
                ) {
                    Text(
                        text = categorieVM.name,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 16.dp), // Adjust text padding
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
        IconButton(
            onClick = { onDeleteClick(categorieVM) },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(36.dp) // Size of the circle
                .background(Color.White, shape = CircleShape) // White circle background
                .border(1.dp, color = Color.Black, shape = CircleShape) // Border around the circle
                .padding(4.dp) // Padding around the icon (this padding fits the icon better inside the circle)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete_food),
                tint = Color.Black
            )
        }
    }
}
