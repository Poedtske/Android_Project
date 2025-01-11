package com.example.android_project

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.android_project.ui.theme.Android_ProjectTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android_ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Row {
                            // Button for navigating to ProductsListActivity
                            Button(onClick = {
                                val intent = Intent(this@MainActivity, ProductsView::class.java)
                                startActivity(intent)
                            }) {
                                Text("Admin")
                            }

                            // Button for navigating to RestaurantActivity
                            Button(onClick = {
                                val intent = Intent(this@MainActivity, RestaurantLayout::class.java)
                                startActivity(intent)
                            }) {
                                Text("Ober")
                            }
                        }
                    }
                }
            }
        }
    }
}

