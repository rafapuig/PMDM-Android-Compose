package es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.data

import es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.model.ImageInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

object LoremPicsumApi {

    suspend fun fetchImageInfo(id: Int): ImageInfo {
        return withContext(Dispatchers.IO) {
            //delay(5000)
            // Obtener JSON de información
            val spec = "https://picsum.photos/id/$id/info"
            val json = URL(spec).readText()

            parseImageInfo(json)
        }
    }

    private fun parseImageInfo(json: String): ImageInfo {
        val obj = JSONObject(json)
        return obj.parseImageInfo()
    }

    private fun JSONObject.parseImageInfo(): ImageInfo {
        val id =  getInt("id") // parseField<Int>("id")
        val width = getInt("width")
        val height = getInt("height")
        val author = getString("author")
        val url = getString("url")

        return ImageInfo(id, width, height, url, author)
    }


    suspend fun fetchImageListInfo(page: Int = 1, limit: Int = 100) =
        withContext(Dispatchers.IO) {

            val spec = "https://picsum.photos/v2/list?page=$page&limit=$limit"
            val json = URL(spec).readText()

            JSONArray(json).parseList { it.parseImageInfo() }
        }

}



