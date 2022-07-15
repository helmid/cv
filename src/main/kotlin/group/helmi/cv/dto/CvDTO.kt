package group.helmi.cv.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import group.helmi.cv.util.extension.localized
import kotlin.math.roundToInt

data class CvDTO (
    val firstName: String,
    val lastName: String,
    val jobTitle: String,
    val contact: List<ContactItemDTO>,
    val sections: List<SectionElementDTO>
)

data class ContactItemDTO(
    /**
     * Icons are fontawesome icons; do not provide the "fa"-prefix in your source
     */
    val icon: String,
    val text: String,
    val href: String?,
    val disclose: Boolean
)

class SectionElementDTO(
    val title: String,
    val entries: List<EntryDTO>
)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(AboutEntryDTO::class, name = "about"),
    JsonSubTypes.Type(HistoryEntryDTO::class, name = "history"),
    JsonSubTypes.Type(EducationEntryDTO::class, name = "education"),
    JsonSubTypes.Type(ColsEntryDTO::class, name = "cols")
)
abstract class EntryDTO

data class AboutEntryDTO(
    val text: String,
    val barchart: List<ChartItemDTO>,
    val bubbles: List<ChartItemDTO>?,
    val country: String,
    val operatingRadius: String,
    val workingMode: String,
    /**
     * Website only attributes
     */
    val bubbleMaxSkill: Double = 10.0,
    val bubblesTitle: String = "",
    val barTitle: String = ""
): EntryDTO()

data class HistoryEntryDTO(
    val start: String,
    val end: String?,
    val jobTitle: String,
    val customer: String,
    val description: String,
    val skills: List<String>
) : EntryDTO() {
    fun getDate(separator: String): String {
        return if (end == null) {
            "${"since".localized()} $start"
        } else {
            "$start $separator $end"
        }
    }
}

data class EducationEntryDTO(
    val start: String,
    val end: String,
    val title: String,
    val facility: String,
    val content: LinkableTextDTO
): EntryDTO()

data class ColsEntryDTO (
    val title: String,
    val barchart: List<ChartItemDTO>?
): EntryDTO()

data class LinkableTextDTO(
    val url: String?,
    val text: String
)

data class ChartItemDTO(
    val title: String,
    val value: Double
) {
    fun bubbleToLineChart(maxValue: Double): ChartItemDTO {
        val newValue = (value / maxValue * 100).roundToInt().toDouble()
        return ChartItemDTO(title = title, value = newValue)
    }
}