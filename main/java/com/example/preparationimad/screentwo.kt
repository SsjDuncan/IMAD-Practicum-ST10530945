package com.example.preparationimad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.collections.get
import kotlin.text.get

class screentwo : AppCompatActivity() {
//these are the variables that will be used in the activity_screentwo.xml
    private lateinit var txtout: TextView
    private lateinit var btnback: Button
    private lateinit var btndis: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screentwo)

        //these are to insure the IDs are correct as it will find the IDs according to what was set
        txtout = findViewById(R.id.txtout)
        btnback = findViewById(R.id.btnback)
        btndis = findViewById(R.id.btndis)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //this is for when the button(back to base) is clicked it will go back to the main activity
        btnback.setOnClickListener {
            finish()
        }
//this is for when the button(display) is clicked it will display the packing list
        btndis.setOnClickListener {
            displayPackingList()
        }
    }
    private fun displayPackingList() {
        //this is to display the packing list
        var output = ""
        for (i in 0 until MainActivity.itemArray.size) {
            output += "Item: ${MainActivity.itemArray[i]}\n"
            output += "Category: ${MainActivity.categoryArray[i]}\n"
            output += "Quantity: ${MainActivity.quantityArray[i]}\n"
            output += "Comments: ${MainActivity.commentsArray[i]}\n\n"
        }
        //this is for it to tell the user that the list is empty using the textview
        txtout.text = if (output.isEmpty()) "The list is empty." else output
    }

    private fun displayItemsWithQuantityOneOrMore() {
        //
        var output = ""
        for (i in MainActivity.quantityArray.indices) {
            if (MainActivity.quantityArray[i] >= 1) {
                output += "Item: ${MainActivity.itemArray[i]}\n"
                output += "Quantity: ${MainActivity.quantityArray[i]}\n\n"
            }
        }
        txtout.text = if (output.isEmpty()) "No items with quantity 2 or more." else output
    }
}
