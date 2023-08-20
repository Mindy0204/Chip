package com.mindyhsu.chip.ui.search

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mindyhsu.chip.model.Character
import com.mindyhsu.chip.ext.dp2px

class TagAdapter(private val uiState: SearchUiState) :
    ListAdapter<Character, TagAdapter.TagViewHolder>(TagDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val itemView = LinearLayout(parent.context)
        return TagViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(getItem(position), uiState)
    }

    class TagDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }

    class TagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Character, uiState: SearchUiState) {
            setupTag(itemView, item.name, item.getGenderEnumType().tagBackground)
            itemView.setOnClickListener {
                uiState.onTagClick(item)
            }
        }

        private fun setupTag(itemView: View, name: String, background: Int) {
            val tag = TextView(itemView.context)

            // Tag text
            tag.text = name

            // Tag background drawable
            tag.background = ContextCompat.getDrawable(itemView.context, background)

            // Tag padding
            val paddingHorizontal = 12f.dp2px(itemView.context).toInt()
            val paddingVertical = 2f.dp2px(itemView.context).toInt()
            tag.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

            // Tag margin
            val layoutParameters = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            val margin = 8f.dp2px(itemView.context).toInt()
            layoutParameters.setMargins(0, 0, margin, margin)

            (itemView as LinearLayout).addView(tag, layoutParameters)
        }
    }
}