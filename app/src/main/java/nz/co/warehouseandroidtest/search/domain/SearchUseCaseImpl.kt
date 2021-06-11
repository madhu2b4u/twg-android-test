package nz.co.warehouseandroidtest.search.domain

import nz.co.warehouseandroidtest.search.data.repository.SearchRepository
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(private val mSearchRepository: SearchRepository) :
    SearchUseCase {

    override suspend fun getSearchResults(map: Map<String, Any>) =  mSearchRepository.getSearchResults(map)

}
