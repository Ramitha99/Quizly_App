package com.example.quizly

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // to see about author, call a function when press the about button
        val aboutBtn = findViewById<Button>(R.id.aboutBtn)
        aboutBtn.setOnClickListener {
            about()
        }

        val newGame = findViewById<Button>(R.id.newGameBtn)
        newGame.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }


    }

    // displaying pop up widow with author details
    // StackOverflow :)
    @SuppressLint("InflateParams")
    private fun about() {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popup: View = inflater.inflate(R.layout.about_popup, null)
        val focusable = true
        Toast.makeText(this, "tap outside to dismiss", Toast.LENGTH_SHORT).show()
        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        val popUpWindow = PopupWindow(popup, width, height, focusable)
        popUpWindow.showAtLocation(popup, Gravity.CENTER, 0, 0)
    }


}

