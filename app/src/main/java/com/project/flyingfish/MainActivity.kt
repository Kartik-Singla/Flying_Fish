package com.project.flyingfish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var gameView:FlyingFishView
    private var handler = Handler()

    val interval:Long = 30
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameView= FlyingFishView(this)
        setContentView(gameView)

        val timer = Timer()

        timer.schedule(object:TimerTask() {
            override fun run() {
                handler.post(object:Runnable{
                    override fun run() {
                        gameView.invalidate()
                    }
                })
            }
        },0,interval)
    }
    //first parameter is required task
    //second parameter is delay which means after how much time the task will start
    //third parameter is period that is after how much time the task repeats
}