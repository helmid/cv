package group.helmi.cv.util

object Path {
    private const val API_PATH = "/api"

    object Document {
        const val DOCUMENT_BASE = "$API_PATH/document"
        const val GET_DOCUMENT_BY_ID = "/{id}/{filename}"
    }
}