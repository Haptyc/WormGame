package com.example.wormgameplus

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceHolder
import androidx.core.content.ContextCompat
import com.example.wormgameplus.databinding.ActivityMainBinding
import java.util.Random
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.scheduleAtFixedRate
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private var movingDirection:String = "right"
    private lateinit var snake: MutableList<SnakePoints>
    private var score:Int = 0
    private var startingSnakeSize: Int = 3
    private var pointSize:Int = 28
    private val snakeColor = Color.RED
    private val snakeSpeed = 800


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(mainBinding.root)

        movingDirection = "right"

        mainBinding.apply {
            leftArrowButton.setOnClickListener {
                if(movingDirection != "right")
                    movingDirection = "left"
            }
            rightArrowButton.setOnClickListener {
                if (movingDirection != "left")
                    movingDirection = "right"
            }
            downArrowButton.setOnClickListener{
                if(movingDirection != "up")
                    movingDirection = "down"
            }
            upArrowButton.setOnClickListener {
                if(movingDirection != "down")
                    movingDirection = "up"
            }
        }

        mainBinding.surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                Log.i("Surface View","Surface View has been created")
                val lockedCanvas = holder.lockCanvas()
                lockedCanvas.drawText("Hello world", 100F, 100F,Paint().apply {
                    this.color = ContextCompat.getColor(this@MainActivity, R.color.white)
                })
                holder.unlockCanvasAndPost(lockedCanvas)



                val lockedCanvas2 = holder.lockCanvas()
                lockedCanvas.drawText("Hld2", 100F, 100F,Paint().apply {
                    this.color = ContextCompat.getColor(this@MainActivity, R.color.white)
                })
                holder.unlockCanvasAndPost(lockedCanvas2)
            }

            override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
            }

            override fun surfaceDestroyed(p0: SurfaceHolder) {
            }
        })

        initiate()

    }

    private fun initiate() {
        snake.clear()
        mainBinding.scoreBanner.text = "0"
        score = 0

    }

    private fun spawnFood(){
        var surfaceWidth: Int
        var surfaceHeight:Int

        mainBinding.apply {
            surfaceWidth = surfaceView.width - (pointSize * 2)
            surfaceHeight = surfaceView.height - (pointSize * 2)
        }

        var randomXSpawn = Random().nextInt(surfaceWidth / pointSize)
        var randomYSpawn = Random().nextInt(surfaceHeight/ pointSize)

        if(randomXSpawn % 2 != 0)
            randomXSpawn+=1
        if(randomYSpawn % 2 != 0)
            randomYSpawn+=1

    }
    private fun moveSnake(){
        var delay = 1000-snakeSpeed
        val timer = Timer().scheduleAtFixedRate(object: TimerTask(){
            override fun run() {

            }
        },200,200)

    }


}