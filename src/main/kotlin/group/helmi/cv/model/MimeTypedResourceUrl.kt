package group.helmi.cv.model

data class MimeTypedResource(val mimeType: String?, val filename: String, val resource: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MimeTypedResource

        if (mimeType != other.mimeType) return false
        if (filename != other.filename) return false
        if (!resource.contentEquals(other.resource)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mimeType?.hashCode() ?: 0
        result = 31 * result + filename.hashCode()
        result = 31 * result + resource.contentHashCode()
        return result
    }

}