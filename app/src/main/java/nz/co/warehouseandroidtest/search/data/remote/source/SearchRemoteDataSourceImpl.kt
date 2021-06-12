package nz.co.warehouseandroidtest.search.data.remote.source

import kotlinx.coroutines.withContext
import nz.co.warehouseandroidtest.di.qualifiers.IO
import nz.co.warehouseandroidtest.search.data.remote.services.SearchService
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class SearchRemoteDataSourceImpl @Inject constructor(
    private val service: SearchService,
    @IO private val context: CoroutineContext
) : SearchRemoteDataSource {

    override suspend fun getSearchResults(map: Map<String, Any>) = withContext(context) {

        val response = service.getSearchResultAsync(map).await()
        if (response.isSuccessful)
            response.body()?: throw Exception("no response")
        else
            throw Exception("invalid request with code ${response.code()}")
    }



}
