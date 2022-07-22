package group.helmi.cv.service

import group.helmi.cv.dto.GdprDTO

interface GdprService {
    fun loadData(): GdprDTO
}