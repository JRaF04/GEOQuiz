package com.example.emptviews

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CheatActivity : AppCompatActivity() {

    private var answer : Boolean = true

    private var cheated : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cheat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        answer = intent.getBooleanExtra("answer", true)

        findViewById<Button>(R.id.yesCheat).setOnClickListener {
            showResult()
        }
        findViewById<Button>(R.id.noCheat).setOnClickListener {
            returnResult()
        }
    }
    private fun returnResult() {
        val mainIntent = Intent(this, MainActivity::class.java)
        mainIntent.putExtra("result", cheated)
        this.startActivity(mainIntent)
    }


    private fun showResult() {
        findViewById<TextView>(R.id.answer).text = if (answer) "True" else "False"
        cheated = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("Result", cheated)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        cheated = savedInstanceState.getBoolean("Result", false)
        if (cheated) {
            showResult()
        }
    }
}