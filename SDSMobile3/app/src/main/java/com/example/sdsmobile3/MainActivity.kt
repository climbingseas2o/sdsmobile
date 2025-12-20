package com.example.sdsmobile3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var addButton: Button
    private lateinit var items: ArrayList<Todo>
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.todoList)
        addButton = findViewById(R.id.addButton)

        items = ArrayList()
        adapter = TodoAdapter(this, items)
        listView.adapter = adapter

        addButton.setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val newText = data?.getStringExtra("todoText")
            if (!newText.isNullOrEmpty()) {
                items.add(Todo(newText, false))
                adapter.notifyDataSetChanged()
            }
        }
    }
}
