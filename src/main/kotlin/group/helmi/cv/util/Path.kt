package group.helmi.cv.util

object Path {
    private const val API_PATH = "/api"

    object Document {
        const val DOCUMENT_BASE = "$API_PATH/document"
        const val GET_DOCUMENT_BY_ID = "/{id}/{filename}"
    }

    object CvWebsite {
        /**
         *  Must match with "target" in contact.json and must be mapped in controller
         */
        const val formAction = "/"
        const val errorPath = "/error"
    }
}