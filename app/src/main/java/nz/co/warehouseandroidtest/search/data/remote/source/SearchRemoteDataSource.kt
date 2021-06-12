package nz.co.warehouseandroidtest.search.data.remote.source

import nz.co.warehouseandroidtest.search.data.remote.models.SearchResponse


interface SearchRemoteDataSource {

    suspend fun getSearchResults(map :Map<String, Any>): SearchResponse

}