package com.mindyhsu.chip.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.mindyhsu.chip.model.EpisodeResult
import com.mindyhsu.chip.databinding.ItemSearchResultBinding
import com.mindyhsu.chip.ext.dp2px
import com.mindyhsu.chip.model.Character

class SearchResultAdapter(private val uiState: SearchUiState) :
    ListAdapter<EpisodeResult, SearchResultAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemSearchResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), uiState)
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<EpisodeResult>() {
        override fun areItemsTheSame(oldItem: EpisodeResult, newItem: EpisodeResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EpisodeResult, newItem: EpisodeResult): Boolean {
            return oldItem == newItem
        }
    }

    class ItemViewHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var adapter: TagAdapter

        fun bind(item: EpisodeResult, uiState: SearchUiState) {
            binding.tvTitle.text = item.name
            binding.tvSubTitle.text = item.episode

            // Original Tag
//            binding.chipGroupTag.show()
//            for (character in item.characters) {
//                setupChip(binding.chipGroupTag, character, uiState)
//            }

            // Customized Tag
            initTagRecyclerView(uiState)
            adapter.submitList(item.characters)
        }

        private fun initTagRecyclerView(uiState: SearchUiState) {
            adapter = TagAdapter(uiState)
            binding.recyclerViewTag.apply {
                val manager = FlexboxLayoutManager(context)
                manager.flexDirection = FlexDirection.ROW // Horizontal orientation
                manager.justifyContent = JustifyContent.FLEX_START // Alignment
                layoutManager = manager
                adapter = this@ItemViewHolder.adapter
            }
        }

        private fun setupChip(chipGroup: ChipGroup, character: Character, uiState: SearchUiState) {
            val tag = Chip(chipGroup.context)

            // Tag text
            tag.text = character.name

            // Tag background color
            tag.setChipBackgroundColorResource(character.getGenderEnumType().tagColor)

            // Tag padding
            // There's no textTopPadding & textBottomPadding
            val paddingHorizontal = 12f.dp2px(itemView.context).toInt()
            tag.textStartPadding = paddingHorizontal.toFloat()
            tag.textEndPadding = paddingHorizontal.toFloat()

            // Tag margin
            // Failed
//            val layoutParameter = LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//            val margin = 8f.dp2px(itemView.context).toInt()
//            layoutParameter.setMargins(0, 0, margin, margin)

            chipGroup.addView(tag)

            tag.setOnClickListener { uiState.onTagClick(character) }
        }
    }
}