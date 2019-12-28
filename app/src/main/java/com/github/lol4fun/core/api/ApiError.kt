package com.github.lol4fun.core.api

data class ApiError(
    var statusCode: Int? = null,
    var message: String? = null
)