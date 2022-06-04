package com.mobileshift.cv.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

data class CvDTO (
    val firstName: String,
    val lastName: String,
    val jobTitle: String,
    val contact: List<ContactItemDTO>,
    val sections: List<SectionElementDTO>
)

data class ContactItemDTO(
    val icon: String,
    val text: String,
    val href: String?
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
    val bubbles: List<ChartItemDTO>?
): EntryDTO()

data class HistoryEntryDTO(
    val start: String,
    val end: String?,
    val jobTitle: String,
    val customer: String,
    val description: String,
    val skills: List<String>
): EntryDTO()

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
)