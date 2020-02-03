package com.example.usearch

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.inputmethod.InputMethodManager
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

    private var searchable: Boolean = false
    private var keyboardShown: Boolean = false

    private fun openSearch() {
        //search_input_text.setText("")
        search_open_view.visibility = View.VISIBLE
        search_closed_view.visibility = View.INVISIBLE
        searchable = true
        et_open_search.requestFocus()
        showSoftKeyboard(et_open_search)

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
        if (event?.keyCode == KeyEvent.KEYCODE_BACK && searchable && keyboardShown) {
            closeSoftKeyboard(search_open_view)
            return true
        } else if (event?.keyCode == KeyEvent.KEYCODE_BACK && searchable) {
            closeSearch()
            return true
        } else {
            return super.dispatchKeyEventPreIme(event)
        }
    }

    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?

            // here is one more tricky issue
            // imm.showSoftInputMethod doesn't work well
            // and imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0) doesn't work well for all cases too
            imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
            keyboardShown = true
        }
    }

    private fun closeSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?

            // here is one more tricky issue
            // imm.showSoftInputMethod doesn't work well
            // and imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0) doesn't work well for all cases too
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
            keyboardShown = false
        }
    }
}