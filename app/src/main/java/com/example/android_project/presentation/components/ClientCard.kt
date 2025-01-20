package com.example.android_project.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.android_project.R
import com.example.android_project.classes.CategoryVM
import com.example.android_project.classes.ClientVM


@Composable
fun ClientCard(
    clientVM: ClientVM,
    modifier: Modifier = Modifier,
    onDeleteClick: (ClientVM) -> Unit,
    onEditClick: (ClientVM) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp) // Padding around the entire card
    ) {
        Column {
            // Box for client ID centered and bolded
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp) // Adjusted height for content
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray) // Background color for the content area
                ,
                contentAlignment = Alignment.Center // Center the content within the box
            ) {
                Text(
                    text = "ID: ${clientVM.id}",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp, // Increased font size for the ID
                        textAlign = TextAlign.Center // Center the text within the box
                    ),
                )
            }

            // Row for the delete and edit buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp), // Padding at the top of the row
                horizontalArrangement = Arrangement.Center // Center the buttons horizontally
            ) {
                IconButton(
                    onClick = { onDeleteClick(clientVM) },
                    modifier = Modifier
                        .size(40.dp) // Increased size for better visibility
                        .background(Color.LightGray, shape = CircleShape) // Lighter background for buttons
                        .border(1.dp, color = Color.Black, shape = CircleShape) // Border around the circle
                        .padding(4.dp) // Padding around the icon
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "stringResource(R.string.delete_client)",
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(16.dp)) // Spacing between buttons (increased to 16.dp)
                IconButton(
                    onClick = { onEditClick(clientVM) },
                    modifier = Modifier
                        .size(40.dp) // Increased size for better visibility
                        .background(Color.LightGray, shape = CircleShape) // Lighter background for buttons
                        .border(1.dp, color = Color.Black, shape =CircleShape) // Lighter background for buttons
                        .border(1.dp, color = Color.Black, shape = CircleShape) // Border around the circle
                        .padding(4.dp) // Padding
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                    contentDescription = "stringResource(R.string.edit_client)",
                    tint = Color.Black
                )
            }

        }
    }
}
}