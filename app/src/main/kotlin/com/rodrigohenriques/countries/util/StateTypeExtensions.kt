package com.rodrigohenriques.countries.util

import io.fluent.StateType

data class ErrorWithMessage(
    val message: String
) : StateType()