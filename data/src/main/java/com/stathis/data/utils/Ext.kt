package com.stathis.data.utils

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun Bitmap.compressBitmap(): ByteArray {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    return baos.toByteArray()
}