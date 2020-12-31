package com.kernel.finch.core.list.cells

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.kernel.finch.common.contracts.component.Cell
import com.kernel.finch.common.contracts.component.ViewHolder
import com.kernel.finch.common.models.Text
import com.kernel.finch.core.R
import com.kernel.finch.core.util.extension.append
import com.kernel.finch.core.util.extension.text
import com.kernel.finch.utils.extensions.tintedDrawable

internal data class ExpandedItemKeyValueCell(
    override val id: String,
    private val key: Text,
    private val value: Text
) : Cell<ExpandedItemKeyValueCell> {

    constructor(
        id: String,
        key: CharSequence,
        value: CharSequence
    ) : this(
        id = id,
        key = Text.CharSequence(key),
        value = Text.CharSequence(value)
    )

    override fun createViewHolderDelegate() =
        object : ViewHolder.Delegate<ExpandedItemKeyValueCell>() {

            override fun createViewHolder(parent: ViewGroup) = KeyValueViewHolder(parent)
        }

    private class KeyValueViewHolder(parent: ViewGroup) :
        ViewHolder<ExpandedItemKeyValueCell>(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.finch_cell_expanded_item_text, parent, false)
        ) {

        private val textView = itemView.findViewById<TextView>(R.id.finch_text_view)
        private val bulletPointDrawable by lazy {
            itemView.context.tintedDrawable(
                R.drawable.finch_ic_bullet_point,
                textView.textColors.defaultColor
            )
        }

        override fun bind(model: ExpandedItemKeyValueCell) = textView.run {
            setCompoundDrawablesWithIntrinsicBounds(bulletPointDrawable, null, null, null)
            val key = context.text(model.key)
            val value = context.text(model.value)
            text = SpannableString(key.append(": ").append(value)).apply {
                setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    key.length + 1,
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
            }
        }
    }
}
