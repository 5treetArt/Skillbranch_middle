package ru.skillbranch.skillarticles.ui.custom

import android.text.TextPaint
import android.text.style.BackgroundColorSpan
import androidx.core.graphics.ColorUtils

open class SearchSpan(bgColor: Int, private val fgColor: Int) : BackgroundColorSpan(bgColor) {
    private val alpha by lazy {
        ColorUtils.setAlphaComponent(bgColor, 160)
    }

    override fun updateDrawState(textPaint: TextPaint) {
        textPaint.bgColor = alpha
        textPaint.color = fgColor
    }
}