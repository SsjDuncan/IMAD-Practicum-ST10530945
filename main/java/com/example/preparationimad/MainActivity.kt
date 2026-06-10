package com.example.preparationimad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

     companion object{
//these are the arraylists that will store the values entered by the user
         val itemArray = arrayListOf<String>()

         val categoryArray = arrayListOf<String>()

         val quantityArray = arrayListOf<Int>()

         val commentsArray = arrayListOf<String>()

     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //this is so that all the values are correct and go according to their corresponding IDs

        val edtname = findViewById<EditText>(R.id.edtname)
        val edtcat = findViewById<EditText>(R.id.edtcat)
        val edtquan = findViewById<EditText>(R.id.edtquan)
        val edtcomm = findViewById<EditText>(R.id.edtcomm)
        val txttotal = findViewById<TextView>(R.id.txttotal)

        val btngear = findViewById<Button>(R.id.btngear)
        val btnnext = findViewById<Button>(R.id.btnnext)
        val btntotal = findViewById<Button>(R.id.btntotal)

        //this is for when the button(next screen) is clicked it will go to the next screen
        btnnext.setOnClickListener {
            val intent = Intent(this, screentwo::class.java)
            startActivity(intent)
        }
        //this is for when the button(total items packed) is clicked it will display the total items packed
        btntotal.setOnClickListener {
            var total = 0
            //this is the for loop that will add all the values in the quantity array
            for (i in 0 until quantityArray.size) {
                total += quantityArray[i]
            }
            //this is to display the total items packed
            txttotal.text = total.toString()
            //this is to display a toast message with the total items packed
            Toast.makeText(this, "Total is $total", Toast.LENGTH_LONG).show()

        }
//this is for when the button(add gear) is clicked it will add the values to the arraylist
        btngear.setOnClickListener {
            val item = edtname.text.toString()
            val category = edtcat.text.toString()
            val quantity = edtquan.text.toString()
            val comments = edtcomm.text.toString()
//this is to check if all the fields are filled
            if (item.isNotEmpty() && category.isNotEmpty() && quantity.isNotEmpty() && comments.isNotEmpty()) {

                itemArray.add(item)
                categoryArray.add(category)
                quantityArray.add(quantity.toInt())
                commentsArray.add(comments)
                Toast.makeText(this, "Item added to packing list", Toast.LENGTH_SHORT).show()
                edtname.text.clear()
                edtcat.text.clear()
                edtquan.text.clear()
                edtcomm.text.clear()
            }
            //this is to inform the user that all the fields must be filled
            else {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_LONG).show()
            }
        }
    }
}
