package com.project.flyingfish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception
import kotlin.concurrent.thread

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val thread:Thread = object :Thread(){
            override fun run() {
                try{
                    sleep(2000)
                }
                catch(e:Exception)
                {
                    e.stackTrace
                }

                finally {
                    val i =  Intent(this@SplashActivity,MainActivity::class.java)
                    startActivity(i)
                }
            }
        }

        thread.start()


    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}