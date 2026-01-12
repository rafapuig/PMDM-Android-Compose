package es.rafapuig.pmdm.client.youtube.lowlevel.data.remote

import es.rafapuig.pmdm.client.youtube.lowlevel.data.remote.dto.VideoInfoDto
import org.json.JSONObject

object VideoJsonSerializer {

    fun fromJson(json: String) =
        JSONObject(json).let { jsonObject ->
            VideoInfoDto(
                id = "",
                title = jsonObject.getString("title"),
                author_name = jsonObject.getString("author_name"),
                author_url = jsonObject.getString("author_url"),
                thumbnail_url = jsonObject.getString("thumbnail_url"),
                thumbnail_width = jsonObject.getInt("thumbnail_width"),
                thumbnail_height = jsonObject.getInt("thumbnail_height")
            )
        }
}
