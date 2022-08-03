package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class EditItemAdapter(val context: Context,private val items: ArrayList<DataModelClass>) :
    RecyclerView.Adapter<EditItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.edit_items_row,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]


        holder.tvTitle.text = item.title
        holder.tvNote.text = item.notes

        // Updating the background color according to the odd/even positions in list.
        if (position % 2 == 0) {
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorLightGray
                )
            )
        } else {
            holder.llMain.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite))
        }

        holder.ivEdit.setOnClickListener(View.OnClickListener {
            if(context is EditNotesActivity){
                context.updateRecordDialog(item)
            }
        })

    }


    override fun getItemCount(): Int {
        return items.size
    }


    // A ViewHolder describes an item view and metadata about its place within the RecyclerView.

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        // Holds the TextView that will add each item to
        val llMain : LinearLayout= ItemView.findViewById(R.id.editllMain)
        val tvTitle : TextView= ItemView.findViewById(R.id.edittvTitle)
        val tvNote :TextView = ItemView.findViewById(R.id.edittvNote)
        val ivEdit : ImageView =ItemView.findViewById(R.id.ivEdit)

    }
}