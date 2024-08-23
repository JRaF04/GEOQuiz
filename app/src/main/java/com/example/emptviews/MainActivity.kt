package com.example.emptviews

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var questionText : TextView


    private var cheated : Boolean = false

    private val controller : MainActivityController by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        if (intent.getBooleanExtra("result", false)) {
            controller.setCheat()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.TrueButton).setOnClickListener {
            inputAnswer(true)
        }

        findViewById<Button>(R.id.FalseButton).setOnClickListener {
            inputAnswer(false)
        }

        findViewById<Button>(R.id.PrevButton).setOnClickListener {
            controller.prev()
            setQuestion()

        }

        findViewById<Button>(R.id.SkipButton).setOnClickListener {
            controller.skip()
            setQuestion()
        }
        findViewById<Button>(R.id.CheatButton).setOnClickListener {
            val cheatIntent = Intent(this, CheatActivity::class.java)
            cheatIntent.putExtra("answer", controller.getAnswer())
            this.startActivity(cheatIntent)
        }


        questionText= findViewById(R.id.Question)


        if (cheated) {
            controller.setCheat()
        }



        setQuestion()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("result", cheated)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getBoolean("result", false)) {
            controller.setCheat()
        }
    }

    fun inputAnswer(answer : Boolean) {
        setResult(controller.check(answer))
        controller.skip()
        setQuestion()
    }

    fun setQuestion() {
        questionText.text = controller.getQuestion()
    }

    fun setResult(result :String) {
        val text : String
        if (controller.getCheat()) {
            cheated = false
            text = getString(R.string.Cheater)

        }
        else {
            text = result
        }
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }



}