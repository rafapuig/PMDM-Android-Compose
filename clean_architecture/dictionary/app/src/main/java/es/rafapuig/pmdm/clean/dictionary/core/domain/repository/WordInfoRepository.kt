package es.rafapuig.pmdm.clean.dictionary.core.domain.repository

import es.rafapuig.pmdm.clean.dictionary.core.data.util.Resource
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>

}