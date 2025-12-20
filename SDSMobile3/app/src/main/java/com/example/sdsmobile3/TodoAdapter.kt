package com.example.sdsmobile3

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView

class TodoAdapter(context: Context, private val items: ArrayList<Todo>) :
    ArrayAdapter<Todo>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.todo_item, parent, false)
        val item = items[position]
        val checkbox = view.findViewById<CheckBox>(R.id.checkDone)
        val textView = view.findViewById<TextView>(R.id.textTodo)

        textView.text = item.text
        checkbox.isChecked = item.isDone

        //strikethrough
        if (item.isDone) {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        // Updating after tapping
        checkbox.setOnCheckedChangeListener { _, isChecked ->
            item.isDone = isChecked
            notifyDataSetChanged()
        }
        return view
    }
}
