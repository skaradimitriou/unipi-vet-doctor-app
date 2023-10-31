package com.stathis.unipidoctor.utils

import androidx.recyclerview.widget.RecyclerView

fun <T : RecyclerView> T.removeItemDecorations() {
    while (itemDecorationCount > 0) {
        removeItemDecorationAt(0)
    }
}