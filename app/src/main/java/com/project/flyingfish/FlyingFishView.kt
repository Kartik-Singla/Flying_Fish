package com.project.flyingfish

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.Color.*
import android.os.Handler
import android.view.MotionEvent
import android.view.View


class FlyingFishView(context: Context?) : View(context)
{
    val str:String = "SCORE"
    private val TAG = "flying"
    private var fish1:Bitmap
    private var fish2:Bitmap
    private var background:Bitmap
    val scorePaint = Paint()

    var fishX:Float = 10F
    var fishY:Float = 0F
    var fishSpeed:Int = 0
    var yellowX = 0F
    var yellowY = 0F
    var greenX = 0F
    var greenY = 0F
    var yellowSpeed = 15
    var score:Int = 0
    var yellowPaint = Paint()
    var greenPaint = Paint()
    var greenSpeed = 20
    var touch:Boolean = false
    var redX = 0F
    var redY = 0F
    var redSpeed = 25
    var redPaint = Paint()
    var lives = 3

    private var life:Bitmap
    private var dead:Bitmap
    init {
        fish1 = BitmapFactory.decodeResource(resources, R.drawable.fish1)
        fish2 = BitmapFactory.decodeResource(resources, R.drawable.fish2)
        background = BitmapFactory.decodeResource(resources,R.drawable.background)

        scorePaint.color = WHITE
        scorePaint.textSize = 70F
        scorePaint.typeface = Typeface.DEFAULT_BOLD
        scorePaint.isAntiAlias = true
        yellowPaint.color = YELLOW
        yellowPaint.isAntiAlias=false
        redPaint.color = RED
        redPaint.isAntiAlias=false
        greenPaint.color = GREEN
        greenPaint.isAntiAlias=false
        life = BitmapFactory.decodeResource(resources,R.drawable.hearts)
        dead = BitmapFactory.decodeResource(resources,R.drawable.heart_grey)


        fishY = 550F
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var canvasHeight:Float= height.toFloat()
        var canvasWidth:Float= width.toFloat()
        canvas.drawBitmap(background,0.0F,0.0F,null)

        var minFishY:Float = fish1.height.toFloat()
        var maxFishY:Float = canvasHeight-fish1.height.toFloat()
        fishY = fishY + fishSpeed

        if(fishY < minFishY)
            fishY = minFishY
        if(fishY > maxFishY)
            fishY = maxFishY
        if(lives!=0){
        fishSpeed+=2}
        if(touch)
        {
            canvas.drawBitmap(fish2, fishX, fishY, null)
            touch = false

        }
        else
        {
            canvas.drawBitmap(fish1, fishX, fishY, null)
        }

        yellowX -= yellowSpeed
        greenX -= greenSpeed
        redX -= redSpeed
        if(hitBallChecker(greenX, greenY))
        {
            score+=20
            greenX=-100F
        }

        if(hitBallChecker(redX, redY))
        {
            lives--

            redX=-100F

            if(lives==0)
            {

                yellowSpeed=0
                greenSpeed=0
                fishSpeed= 0
                redSpeed=0
                Handler().postDelayed({
                    val i = Intent(context, GameOverActivity::class.java)

                    i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    i.putExtra(str,score)
                    context.startActivity(i)
                }, 3 * 1000)




            }
        }

        if(hitBallChecker(yellowX, yellowY))
        {
            score+=10
            yellowX=-100F
        }


        if(yellowX < 0)
        {
            yellowX = canvasWidth + 21
            yellowY = (minFishY.toInt()..canvasHeight.toInt()).random().toFloat()/*(maxFishY - minFishY) + minFishY*/
        }
        if(redX < 0)
        {
            redX = canvasWidth + 21
            redY = (minFishY.toInt()..canvasHeight.toInt()).random().toFloat()/*(maxFishY - minFishY) + minFishY*/
        }

        if(greenX < 0)
        {
            greenX = canvasWidth + 21
            greenY = (minFishY.toInt()..canvasHeight.toInt()).random().toFloat()/*(maxFishY - minFishY) + minFishY*/
        }

        for(i in 0..2){
            var x:Float = 580F + (life.width*1.5*i).toFloat()
            var y = 10F
            if(i<lives){
                canvas.drawBitmap(life,x,y,null)
            }
            else
                canvas.drawBitmap(dead,x,y,null)


        }

        canvas.drawCircle(yellowX,yellowY,20F, yellowPaint)
        canvas.drawCircle(greenX,greenY,20F, greenPaint)
        canvas.drawCircle(redX,redY,25F, redPaint)
        canvas.drawText("Score : $score", 50F,70F,scorePaint )


    }




    fun hitBallChecker(x:Float, y:Float):Boolean{
        if(x >= fishX && x <= fishX + fish1.width && y >= fishY && y <= fishY + fish1.height)
            return true
        return false
    }

    fun hit(x:Float, y:Float,canvas:Canvas):Boolean{
        if(x >= fishX && x <= fishX + fish1.width && y >= fishY && y <= fishY + fish1.height)
        {
            return true
        }
        return false

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_DOWN)
            touch = true
        fishSpeed = -20
        return true
    }
}