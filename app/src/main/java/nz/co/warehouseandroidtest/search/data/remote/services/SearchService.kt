package nz.co.warehouseandroidtest.search.data.remote.services

import kotlinx.coroutines.Deferred
import nz.co.warehouseandroidtest.search.data.remote.models.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchService {
    @JvmSuppressWildcards
    @GET("bolt/search.json")
    fun getSearchResultAsync(@QueryMap paramMap: Map<String, Any>): Deferred<Response<SearchResponse>>

}