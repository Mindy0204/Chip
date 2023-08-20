package com.mindyhsu.chip.widget

import android.graphics.Canvas
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.mindyhsu.chip.R

/**
 * Divider decoration at the bottom of each item (excluding the last item)
 * */
class DividerItemDecoration : RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView) {
        val paint = Paint()
        paint.color = parent.resources.getColor(R.color.gray)

        for (i in 0 until parent.childCount) {
            if (i == parent.childCount - 1) {
                continue
            }

            parent.getChildAt(i).apply {
                c.drawLine(
                    left.toFloat(),
                    bottom.toFloat(),
                    right.toFloat(),
                    bottom.toFloat(),
                    paint
                )
            }
        }
    }
}