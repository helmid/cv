package group.helmi.cv.util

object Path {
    private const val API_PATH = "/api"

    object Document {
        const val DOCUMENT_BASE = "$API_PATH/document"
        const val GET_DOCUMENT_BY_TOKEN = "/{token}"
        const val POST_MAKE_DOCUMENT = "/make"
    }

    object Maintenance {
        const val MAINTENANCE_BASE = "$API_PATH/maintenance"
        const val DELETE_TEX_SOURCES = "/deleteTexSources"
    }
}