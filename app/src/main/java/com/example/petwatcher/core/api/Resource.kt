package com.example.petwatcher.core.api

import com.example.petwatcher.core.presentation.UiText

typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data: T? = null, val uiText: UiText? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(uiText: UiText, data: T? = null): Resource<T>(data, uiText)
}
