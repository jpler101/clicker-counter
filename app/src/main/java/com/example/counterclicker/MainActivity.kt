
package com.example.counterclicker

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    private var count = 0
    private lateinit var countText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        count = getPreferences(MODE_PRIVATE).getInt("count", 0)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            setBackgroundColor(Color.rgb(24, 24, 32))
        }

        countText = TextView(this).apply {
            textSize = 64f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }
        layout.addView(countText)

        val addButton = Button(this).apply {
            text = "+1"
            textSize = 28f
            setOnClickListener { updateCount(count + 1) }
        }
        layout.addView(addButton)

        val subtractButton = Button(this).apply {
            text = "-1"
            setOnClickListener { updateCount((count - 1).coerceAtLeast(0)) }
        }
        layout.addView(subtractButton)

        val resetButton = Button(this).apply {
            text = "Reset"
            setOnClickListener { updateCount(0) }
        }
        layout.addView(resetButton)

        setContentView(layout)
        refreshCount()
    }

    private fun updateCount(newCount: Int) {
        count = newCount
        getPreferences(MODE_PRIVATE).edit().putInt("count", count).apply()
        refreshCount()
    }

    private fun refreshCount() {
        countText.text = count.toString()
    }
}
