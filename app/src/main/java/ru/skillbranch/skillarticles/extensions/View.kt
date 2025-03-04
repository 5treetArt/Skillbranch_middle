package ru.skillbranch.skillarticles.extensions

import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

/**
 * Добавляет внешний оступ к View
 */
fun View.setMarginOptionally(left: Int = marginLeft, top: Int = marginTop, right: Int = marginRight, bottom: Int = marginBottom) {
    val layoutParams = layoutParams as? ViewGroup.MarginLayoutParams
    layoutParams?.let {
        it.setMargins(left, top, right, bottom)
        this.layoutParams = it
    }
}