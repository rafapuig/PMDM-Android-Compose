package es.rafapuig.pmdm.client.youtube.lowlevel.data.remote

import es.rafapuig.pmdm.client.youtube.lowlevel.data.remote.dto.VideoInfoDto
import es.rafapuig.pmdm.client.youtube.lowlevel.domain.model.VideoInfo

fun VideoInfoDto.toVideoInfo(): VideoInfo =
    VideoInfo(
        id = id,
        title = title,
        authorName = author_name,
        authorUrl = author_url,
        thumbnailUrl = thumbnail_url
    )
