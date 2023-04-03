package com.example.wormgameplus.renderables

import android.graphics.Canvas

interface Renderable {
    fun render(canvas: Canvas)
    fun tick()
}