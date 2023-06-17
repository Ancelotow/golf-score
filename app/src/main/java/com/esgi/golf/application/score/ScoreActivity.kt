package com.esgi.golf.application.score

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        supportActionBar?.hide()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val items = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
        val adapter = MyAdapter(items) // Remplacez MyAdapter par votre propre adaptateur
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

       /*val scaleTransformer = ScaleTransformer(this)
        recyclerView.addItemDecoration(scaleTransformer)*/
    }
}

class ScaleTransformer(context: Context) : RecyclerView.ItemDecoration() {
    private val scaleDownFactor = 0.8f // Facteur de mise à l'échelle des éléments

    private val screenWidth: Int = context.resources.displayMetrics.widthPixels
    private val screenHeight: Int = context.resources.displayMetrics.heightPixels

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val itemWidth = view.width
        val itemHeight = view.height

        val widthOffset = (screenWidth - itemWidth) / 2
        val heightOffset = (screenHeight - itemHeight) / 2

        outRect.set(widthOffset, heightOffset, widthOffset, heightOffset)
    }

    override fun onDraw(
        canvas: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.onDraw(canvas, parent, state)
        val itemCount = parent.childCount

        for (i in 0 until itemCount) {
            val child = parent.getChildAt(i)

            val centerX = (child.left + child.right) / 2f
            val centerY = (child.top + child.bottom) / 2f

            val distanceFromCenterX = screenWidth / 2f - centerX
            val scaleFactor = 1 - 0.3f * (Math.abs(distanceFromCenterX) / screenWidth)

            child.scaleX = scaleFactor
            child.scaleY = scaleFactor
        }
    }
}

class MyAdapter(private val items: List<String>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hole_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)

        fun bind(item: String) {
            textView.text = item
        }
    }
}