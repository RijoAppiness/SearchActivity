package com.example.usearch

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.usearch_layout.view.*

class USearch(
        context: Context,
        attrs: AttributeSet
) : FrameLayout(context, attrs) {

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.usearch_layout, this, true)

        enable_search.setOnClickListener { openSearch() }
//            close_search.setOnClickListener { closeSearch() }

    }

    private var searchable:Boolean = false

    private fun openSearch() {
        //search_input_text.setText("")
        search_open_view.visibility = View.VISIBLE
        search_closed_view.visibility = View.INVISIBLE
        searchable = true
//        val circularReveal = ViewAnimationUtils.(
//                search_open_view,
//                (open_search_button.right + open_search_button.left) / 2,
//                (open_search_button.top + open_search_button.bottom) / 2,
//                0f, width.toFloat()
//        )
//        circularReveal.duration = 300
//        circularReveal.start()

    }

    private fun closeSearch() {
        search_closed_view.visibility = View.VISIBLE
        search_open_view.visibility = View.INVISIBLE
        searchable = false
    }

    override fun dispatchKeyEventPreIme(event: KeyEvent?): Boolean {
        if (event?.keyCode == KeyEvent.KEYCODE_BACK&&searchable) {
            closeSearch()
            return true
        } else {
            return super.dispatchKeyEventPreIme(event)
        }
    }
}