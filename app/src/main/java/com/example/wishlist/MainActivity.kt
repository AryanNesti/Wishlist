package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var items: MutableList<item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lookup the RecyclerView in activity layout
        val itemsRv = findViewById<RecyclerView>(R.id.itemsRecycleView)
        // Fetch the list of emails
        items = mutableListOf()
        // Create adapter passing in the list of emails
        val adapter = itemAdapter(items)
        // Attach the adapter to the RecyclerView to populate items
        itemsRv.adapter = adapter
        // Set layout manager to position the items
        itemsRv.layoutManager = LinearLayoutManager(this)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            var newItems : MutableList<item> = ArrayList()
            val item = item(findViewById<EditText>(R.id.nameEditText).text.toString(), findViewById<EditText>(R.id.priceEditText).text.toString(), findViewById<EditText>(R.id.linkEditText).text.toString())
            newItems.add(item)
            items.addAll(newItems)
            findViewById<EditText>(R.id.nameEditText).setText("")
            findViewById<EditText>(R.id.priceEditText).setText("")
            findViewById<EditText>(R.id.linkEditText).setText("")
            adapter.notifyDataSetChanged()
        }
    }
}