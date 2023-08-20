package com.mindyhsu.chip.model

import com.mindyhsu.chip.R

enum class GenderEnum(val genderName: String, val tagColor: Int, val tagBackground: Int) {
    GENDER_MALE(genderName = "Male", tagColor = R.color.blue, tagBackground = R.drawable.bg_tag_male),
    GENDER_FEMALE(genderName = "Female", tagColor = R.color.orange, tagBackground = R.drawable.bg_tag_female),
    GENDER_UNKNOWN(genderName = "unknown", tagColor = R.color.green, tagBackground = R.drawable.bg_tag_unknown)
}