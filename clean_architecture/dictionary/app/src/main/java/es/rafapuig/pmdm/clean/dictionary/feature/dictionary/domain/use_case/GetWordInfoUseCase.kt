package es.rafapuig.pmdm.clean.dictionary.feature.dictionary.domain.use_case

import es.rafapuig.pmdm.clean.dictionary.core.data.util.Resource
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.WordInfo
import es.rafapuig.pmdm.clean.dictionary.core.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetWordInfoUseCase @Inject constructor(
    private val repository: WordInfoRepository
) {
    /** La validación va aquí en el caso de uso */
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {

        if (word.isBlank()) return emptyFlow()

        return repository.getWordInfo(word)
    }
}
