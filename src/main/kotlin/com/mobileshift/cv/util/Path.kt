package com.mobileshift.cv.util

class Path {
    companion object {
        private const val BASE_PATH = "/api"
        internal const val DOCUMENT = "$BASE_PATH/document"
        internal const val GET_DOCUMENT_TOKEN = "/{token}"
        internal const val POST_MAKE_DOCUMENT = "/make"
    }
}