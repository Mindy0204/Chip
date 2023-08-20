package com.mindyhsu.chip.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mindyhsu.chip.R
import com.mindyhsu.chip.databinding.FragmentCharacterDetailDialogBinding
import com.mindyhsu.chip.ext.glide
import com.mindyhsu.chip.model.Character
import timber.log.Timber

class CharacterDetailDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentCharacterDetailDialogBinding
    private lateinit var argCharacter: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        argCharacter = arguments?.let {
            CharacterDetailDialogFragmentArgs.fromBundle(it).character
        } ?: run {
            Timber.d("[onCreate] navigation argument is null")
            Character()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            argCharacter.apply {
                tvCharacterName.text = name
                tvCharacterGender.text = getString(R.string.character_gender, gender)
                tvCharacterSpecies.text = getString(R.string.character_species, species)
                ivCharacter.glide(image)
            }
        }
    }
}