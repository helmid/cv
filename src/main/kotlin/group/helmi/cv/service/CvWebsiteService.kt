package group.helmi.cv.service

import group.helmi.cv.dto.CvWebsiteDTO

interface CvWebsiteService {
    fun loadData(): CvWebsiteDTO
}