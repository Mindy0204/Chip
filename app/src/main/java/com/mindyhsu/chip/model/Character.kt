package com.mindyhsu.chip.model

import android.os.Parcelable
import com.mindyhsu.chip.ext.emptyString
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: String = emptyString(),
    val name: String = emptyString(),
    val gender: String = emptyString(),
    val species: String = emptyString(),
    val image: String = emptyString()
) : Parcelable {

    fun getGenderEnumType(): GenderEnum {
        GenderEnum.values().forEach {
            if (it.genderName == gender) return it
        }
        return GenderEnum.GENDER_UNKNOWN
    }
}