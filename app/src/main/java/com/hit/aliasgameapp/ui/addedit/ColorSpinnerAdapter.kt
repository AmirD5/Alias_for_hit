package com.hit.aliasgameapp.ui.addedit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hit.aliasgameapp.R

class ColorSpinnerAdapter(
    context: Context,
    private val colors: Array<String>,
    private val colorResources: IntArray
) : ArrayAdapter<String>(context, R.layout.spinner_color_item, colors) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.spinner_color_item, parent, false)

        val colorPreview = view.findViewById<View>(R.id.color_preview)
        val colorName = view.findViewById<TextView>(R.id.color_name)

        colorName.text = colors[position]

        if (position < colorResources.size) {
            val color = ContextCompat.getColor(context, colorResources[position])
            colorPreview.setBackgroundColor(color)
        }

        return view
    }
}

