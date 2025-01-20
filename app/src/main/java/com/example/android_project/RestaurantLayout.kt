package com.example.android_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_project.utils.Screen

class RestaurantLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_restaurant_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Set click listeners for all table buttons
        val tableButtons = listOf(
            R.id.table1, R.id.table2, R.id.table3, R.id.table4,
            R.id.table5, R.id.table6, R.id.table7, R.id.table8,
            R.id.table9, R.id.table10, R.id.table11, R.id.table12,
            R.id.table13, R.id.table14, R.id.table15, R.id.table16,
            R.id.table17, R.id.table18, R.id.table19, R.id.table20,
            R.id.table21, R.id.table22, R.id.table23, R.id.table24,
            R.id.table25, R.id.table26, R.id.table27, R.id.table28,
            R.id.table29, R.id.table30, R.id.table31
        )
        for (tableButtonId in tableButtons) {
            findViewById<Button>(tableButtonId).setOnClickListener { button ->
                val tableId = resources.getResourceEntryName(button.id) // Get the button ID
                navigateToTableDetail(tableId)
            }
        }
    }
    private fun navigateToTableDetail(tableId: String) {
        val cleanedTableId = tableId.replace("table", "") // Removes "table" from the string
        val intent = Intent(this, OberTableView::class.java)
        intent.putExtra("TABLE_ID", cleanedTableId.toIntOrNull())
        startActivity(intent)
    }

}