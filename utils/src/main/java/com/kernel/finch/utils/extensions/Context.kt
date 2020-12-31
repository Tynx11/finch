package com.kernel.finch.utils.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

fun Context.color(@ColorRes colorResourceId: Int) = ContextCompat.getColor(this, colorResourceId)

@SuppressLint("ResourceAsColor")
@ColorInt
fun Context.colorResource(@AttrRes id: Int): Int {
    val resolvedAttr = TypedValue()
    theme.resolveAttribute(id, resolvedAttr, true)
    return color(resolvedAttr.run { if (resourceId != 0) resourceId else data })
}

fun Context.dimension(@DimenRes dimensionResourceId: Int) =
    resources.getDimensionPixelSize(dimensionResourceId)

fun Context.drawable(@DrawableRes drawableResourceId: Int) =
    AppCompatResources.getDrawable(this, drawableResourceId)

fun Context.tintedDrawable(@DrawableRes drawableResourceId: Int, @ColorInt tint: Int): Drawable? =
    drawable(drawableResourceId)?.let { drawable ->
        DrawableCompat.wrap(drawable.mutate()).apply {
            DrawableCompat.setTint(this, tint)
            DrawableCompat.setTintMode(this, PorterDuff.Mode.SRC_IN)
        }
    }
