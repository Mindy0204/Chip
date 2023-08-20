package com.mindyhsu.chip.ext

import android.content.Context
import android.util.TypedValue

fun Float.dp2px(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this,
    context.resources.displayMetrics
)