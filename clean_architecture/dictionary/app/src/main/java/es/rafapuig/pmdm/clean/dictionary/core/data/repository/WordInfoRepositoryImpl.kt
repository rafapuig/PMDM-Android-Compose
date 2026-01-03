package es.rafapuig.pmdm.clean.dictionary.core.data.repository

import es.rafapuig.pmdm.clean.dictionary.core.data.local.WordInfoDao
import es.rafapuig.pmdm.clean.dictionary.core.data.local.toDomain
import es.rafapuig.pmdm.clean.dictionary.core.data.remote.DictionaryApi
import es.rafapuig.pmdm.clean.dictionary.core.data.remote.toDatabase
import es.rafapuig.pmdm.clean.dictionary.core.data.util.Resource
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.WordInfo
import es.rafapuig.pmdm.clean.dictionary.core.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map { it.toDomain() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toDatabase() })

        } catch (e : HttpException) {
            emit(Resource.Error(
                message = "Algo ha ido mal",
                data = wordInfos
            ))
        } catch (e : IOException)  {
            emit(Resource.Error(
                message = "No se ha podido conectar",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toDomain() }
        emit(Resource.Success(newWordInfos))
    }
}