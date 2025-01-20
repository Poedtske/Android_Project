package com.example.android_project


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.android_project.ui.theme.Android_ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android_ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val logoUrl= if(isSystemInDarkTheme()){
            "https://kfdemoedigevrienden.be/images/logoJeugd.png"
        }else{
            "https://kfdemoedigevrienden.be/images/logoFanfare.png"
        }
        // Logo Image
        AsyncImage(
            model = logoUrl,
            contentDescription = "Logo Fanfare",
            modifier = Modifier
                .fillMaxWidth(0.5f) // Adjust the size
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(32.dp)) // Space between image and buttons

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between buttons
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Button for navigating to ProductsView
            Button(onClick = {
                val intent = Intent(context, ProductsView::class.java)
                context.startActivity(intent)
            }) {
                Text("Admin", fontSize = 18.sp)
            }

            // Button for navigating to RestaurantLayout
            Button(onClick = {
                val intent = Intent(context, RestaurantLayout::class.java)
                context.startActivity(intent)
            }) {
                Text("Ober", fontSize = 18.sp)
            }
        }
    }
}
