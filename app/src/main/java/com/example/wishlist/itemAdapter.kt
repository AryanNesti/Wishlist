package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.ClipData.Item
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class itemAdapter(private var items: MutableList<item>) :
    RecyclerView.Adapter<itemAdapter.ViewHolder>() {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {
        // TODO: Create member variables for any view that will be set
        // as you render a row.
        val nameTextView: TextView
        val priceTextView: TextView
        val linkTextView: TextView


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // TODO: Store each of the layout's views into
            // the public final member variables created above
            nameTextView = itemView.findViewById(R.id.nameTextView)
            priceTextView = itemView.findViewById(R.id.priceTextView)
            linkTextView = itemView.findViewById(R.id.linkTextView)
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(view: View) {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(items[layoutPosition].link))
                ContextCompat.startActivity(view.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(view.context, "Invalid URL for " + items[layoutPosition].name, Toast.LENGTH_LONG).show()
            }
        }

        override fun onLongClick(view: View): Boolean {
            Toast.makeText(view.context, "Removed ${items[layoutPosition].name}", Toast.LENGTH_SHORT).show()
            items.removeAt(layoutPosition)
            notifyDataSetChanged()
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val item = items.get(position)
        // Set item views based on views and data model
        holder.nameTextView.text = item.name
        holder.priceTextView.text = item.price
        holder.linkTextView.text = item.link
    }
    fun remove(position: Int) {
        items.drop(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
    }
}
