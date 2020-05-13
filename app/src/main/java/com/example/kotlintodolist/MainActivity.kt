package com.example.kotlintodolist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this, CreateToDoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume(){
        super.onResume()
        updateRecycler()
    }

    fun updateRecycler(){
        var prefs = getSharedPreferences("com.example.kotlintodolist.prefs", Context.MODE_PRIVATE )
        var todos = prefs.getStringSet("todostrings", setOf())?.toMutableSet()
        println(todos)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        if (todos != null) {
            adapter = ToDoAdapter(todos.toList())
        }
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(item.itemId == R.id.delete_all){
            var prefs = getSharedPreferences("com.example.kotlintodolist.prefs", Context.MODE_PRIVATE )
            prefs.edit().putStringSet(getString(R.string.TODO_STRINGS), null).apply()
            updateRecycler()
            return true
        }
        return super.onOptionsItemSelected(item)
        }
}

