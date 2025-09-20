package com.example.aisleguessr.data

import androidx.annotation.StringRes

data class ButtonData (
    @StringRes val labelResId: Int,
    val isLocked: Boolean
)