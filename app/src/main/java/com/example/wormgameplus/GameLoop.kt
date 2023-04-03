package com.example.wormgameplus

import android.graphics.Canvas
import android.view.SurfaceHolder

class GameLoop(val holder: SurfaceHolder) {
    lateinit var thread:Thread;
    val renderTime = 1000L / 60L

    fun startLoop(){
        var realRenderTime = renderTime
        thread = Thread(Runnable{
            while (true) {
                val prerender = System.currentTimeMillis()
                val canvas = calculateRender()
                val postrender = System.currentTimeMillis()
                val elapsedTime = postrender - prerender
                if (elapsedTime < realRenderTime) {
                    // we can draw right away
                    render(canvas, realRenderTime - elapsedTime)
                    realRenderTime = renderTime
                } else if (elapsedTime > realRenderTime) {
                    realRenderTime = renderTime - (elapsedTime % renderTime)
                    render(canvas, 0)
                }
            }
        })

        thread.start()
    }

    private fun calculateRender(): Canvas {
        // where you move things before drawing
        return holder.lockCanvas()
    }

    fun render(canvas: Canvas, timeToSleep: Long) {
        // draw the moved things to the canvas
        try {
            if (timeToSleep > 0)
                Thread.sleep(timeToSleep)
        } catch (e: InterruptedException) {

        } finally {
            holder.unlockCanvasAndPost(canvas)
        }
    }

}