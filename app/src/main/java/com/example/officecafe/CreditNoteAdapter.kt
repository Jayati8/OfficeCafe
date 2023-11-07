package com.example.officecafe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CreditNoteAdapter(private val creditNotes: List<CreditNote>) : RecyclerView.Adapter<CreditNoteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val amountTextView: TextView = itemView.findViewById(R.id.amountTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val sequenceTextView : TextView = itemView.findViewById(R.id.sequence)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_credit_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val creditNote = creditNotes[position]
        holder.amountTextView.text = creditNote.creditNoteAmount.toString()
        holder.dateTextView.text = creditNote.creditNoteDate
        holder.timeTextView.text = creditNote.creditNoteTime
    }

    override fun getItemCount() = creditNotes.size
}

