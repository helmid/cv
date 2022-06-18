package group.helmi.cv.util

object Path {
    private const val API_PATH = "/api"
    const val API_PATH_ALL = "$API_PATH/**"
    const val DOCUMENT = "$API_PATH/document"
    const val GET_DOCUMENT_TOKEN = "/{token}"
    const val POST_MAKE_DOCUMENT = "/make"
}