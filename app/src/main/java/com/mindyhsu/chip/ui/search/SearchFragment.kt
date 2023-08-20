package com.mindyhsu.chip.ui.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.mindyhsu.chip.R
import com.mindyhsu.chip.databinding.FragmentSearchBinding
import com.mindyhsu.chip.ext.hide
import com.mindyhsu.chip.ext.show
import com.mindyhsu.chip.widget.DividerItemDecoration
import timber.log.Timber

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel = SearchViewModel()
    private lateinit var adapter: SearchResultAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRecyclerView()
        initObserver()
    }

    private fun initView() {
        binding.apply {
            tvNoResult.hide()
            recyclerViewResult.hide()

            editTextSearch.apply {
                setOnEditorActionListener { tv, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        Timber.d("[setOnEditorActionListener] searchKeyword = ${tv.text}")
                        viewModel.getEpisodes(searchKeyword = tv.text.toString())

                        this.clearFocus()
                        val iMM = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        iMM.hideSoftInputFromWindow(editTextSearch.windowToken, 0)
                        return@setOnEditorActionListener true
                    }
                    return@setOnEditorActionListener false
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = SearchResultAdapter(viewModel.uiState)

        binding.recyclerViewResult.apply {
            adapter = this@SearchFragment.adapter
            addItemDecoration(DividerItemDecoration())
        }
    }

    private fun initObserver() {
        viewModel.apply {
            searchResult.observe(viewLifecycleOwner) {
                binding.apply {
                    it?.apply {
                        if (isEmpty()) {
                            tvNoResult.show()
                            tvNoResult.text = getString(R.string.text_no_result, viewModel.keyword)
                            recyclerViewResult.hide()
                        } else {
                            tvNoResult.hide()
                            recyclerViewResult.show()
                            adapter.submitList(it)
                        }
                    } ?: run {
                        Timber.d("[initObserver] searchResult is null")
                    }
                }
            }

            navigateToCharacterDetail.observe(viewLifecycleOwner) {
                it?.let {
                    findNavController().navigate(SearchFragmentDirections.navigateToCharacterDetail(it))
                    completeNavigateToCharacterDetail()
                } ?: run {
                    Timber.d("[initObserver] navigateToCharacterDetail is null")
                }
            }
        }
    }
}