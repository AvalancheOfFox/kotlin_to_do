package com.example.kotlintodolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_complete_to_do.*

class CompleteToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_to_do)


        val todo = intent.extras?.getString("title")

        toDoTextView.text = todo

        completeButton.setOnClickListener(){
            var prefs = getSharedPreferences("com.example.kotlintodolist.prefs", Context.MODE_PRIVATE )
            var todos = prefs.getStringSet("todostrings", setOf())?.toMutableSet()
            todos?.remove(todo)
            prefs.edit().putStringSet(getString(R.string.TODO_STRINGS), todos).apply()
            finish()
        }
    }
}
