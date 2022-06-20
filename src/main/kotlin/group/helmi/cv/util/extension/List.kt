package group.helmi.cv.util.extension

fun <T> List<T>.makeLines(sublistSize: Int): List<List<T>> {
    return List(this.size) { index -> index }.groupBy { (it) / sublistSize }.values //split the indices array into groups of "sublistSize" size
        .map { it.map { groupedIndex -> this[groupedIndex] } } //map it back to the original item, since we don't need the index
}