package com.mobileshift.cv.model.templates.tex.cv1

import com.mobileshift.cv.model.ChartItemDTO

class Bubbles {
    companion object {
        fun make(items: List<ChartItemDTO>?): String {
            if (items.isNullOrEmpty()) {
                return ""
            }
            val bubbles = items.map { makeBubble(it) }.joinToString(", ")
            return """
                \vspace{0.5cm}
                \bubbles{$bubbles}
            """.trimIndent()
        }

        private fun makeBubble(item: ChartItemDTO): String {
            return "${item.value}/${item.title}"
        }
    }
}