package com.example.egci428_scalegesture14

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View

class ImageViewWithZoom(context: Context): View(context) {
    private var image: Drawable? = null
    private var scaleFactor = 1.0F
    private var scaleGestureDetector: ScaleGestureDetector? = null

    init {
        image = context.getDrawable(R.drawable.ic_launcher_background)
        image!!.setBounds(0, 0, image!!.intrinsicWidth, image!!.intrinsicHeight)
        isFocusable = true
        scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector!!.scaleFactor
            scaleFactor = Math.max(0.1F, Math.min(scaleFactor, 40.0F))
            invalidate()
            return true
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        canvas.scale(scaleFactor, scaleFactor)
        image!!.draw(canvas)
        canvas.restore()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector!!.onTouchEvent(event)
        invalidate()
        return true
    }
}