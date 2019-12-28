package com.github.lol4fun.core.model

data class ApiError(
    var statusCode: Int? = null,
    var message: String? = null
)