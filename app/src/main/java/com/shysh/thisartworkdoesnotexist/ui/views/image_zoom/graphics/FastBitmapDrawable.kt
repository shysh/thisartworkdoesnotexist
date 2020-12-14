package com.shysh.thisartworkdoesnotexist.ui.views.image_zoom.graphics

import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.Drawable
import java.io.InputStream

/**
 * Fast bitmap drawable. Does not support states. it only
 * support alpha and colormatrix
 *
 * @author alessandro
 */
class FastBitmapDrawable(override var bitmap: Bitmap?) : Drawable(), IBitmapDrawable {
    private val paint: Paint

    private var mIntrinsicWidth = 0
    private var mIntrinsicHeight = 0

    constructor(res: Resources?, inputStream: InputStream?) : this(BitmapFactory.decodeStream(inputStream))

    init {
        bitmap?.let {
            mIntrinsicWidth = it.width
            mIntrinsicHeight = it.height
        }?: run {
            mIntrinsicWidth = 0
            mIntrinsicHeight = 0
        }
        paint = Paint()
        paint.isDither = true
        paint.isFilterBitmap = true
    }

    override fun draw(canvas: Canvas) {
        bitmap?.let { _bitmap ->
            if (_bitmap.isRecycled) {
                val bounds = bounds
                if (!bounds.isEmpty) {
                    canvas.drawBitmap(_bitmap, null, bounds, paint)
                } else {
                    canvas.drawBitmap(_bitmap, 0f, 0f, paint)
                }
            }
        }

    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        paint.colorFilter = cf
    }

    override fun getIntrinsicWidth(): Int {
        return mIntrinsicWidth
    }

    override fun getIntrinsicHeight(): Int {
        return mIntrinsicHeight
    }

    override fun getMinimumWidth(): Int {
        return mIntrinsicWidth
    }

    override fun getMinimumHeight(): Int {
        return mIntrinsicHeight
    }

    fun setAntiAlias(value: Boolean) {
        paint.isAntiAlias = value
        invalidateSelf()
    }


}