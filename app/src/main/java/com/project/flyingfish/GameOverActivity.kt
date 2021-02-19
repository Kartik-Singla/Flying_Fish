package com.project.flyingfish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val play_again = findViewById<Button>(R.id.play_again)
        val exit = findViewById<Button>(R.id.exit)
        val scor = findViewById<TextView>(R.id.score)

        val score:Int = intent.extras!!.getInt("SCORE")
        scor.text = score.toString()
        play_again.setOnClickListener {
            val i = Intent(this@GameOverActivity, MainActivity::class.java)
            startActivity(i)
        }
        exit.setOnClickListener { System.exit(0) }
    }
}